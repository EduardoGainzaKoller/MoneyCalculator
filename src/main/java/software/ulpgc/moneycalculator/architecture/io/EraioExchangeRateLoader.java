package software.ulpgc.moneycalculator.architecture.io;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import software.ulpgc.moneycalculator.architecture.io.interfaces.ExchangeRateDeserializer;
import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.model.ExchangeRate;
import software.ulpgc.moneycalculator.architecture.io.interfaces.ExchangeRateLoader;
import software.ulpgc.moneycalculator.architecture.io.pojos.OpenExchangeRate;

import java.io.IOException;
import java.time.LocalDate;

import static org.jsoup.Connection.Method.GET;

public class EraioExchangeRateLoader implements ExchangeRateLoader {
    private final ExchangeRateDeserializer deserializer;
    private static final String BASE_URL = "https://openexchangerates.org/api/";
    private static final String APP_ID = "a1217a1c45dc417884b481b7b791480c";

    public EraioExchangeRateLoader(ExchangeRateDeserializer deserializer) {
        this.deserializer = deserializer;
    }
    @Override
    public ExchangeRate load(Currency from, Currency to) {
        return load(from, to, LocalDate.now());
    }

    @Override
    public ExchangeRate load(Currency from, Currency to, LocalDate date) {
        String endpoint = date.equals(LocalDate.now()) ? "latest.json" : "historical/" + date + ".json";
        String urlStr = BASE_URL + endpoint + "?app_id=" + APP_ID;

        try {
            String json = read(urlStr);
            Object exchangeRate = deserializer.deserialize(json);
            OpenExchangeRate openExchangeRate = (OpenExchangeRate) exchangeRate;
            double crossRate = getCrossRate(from, to, openExchangeRate);
            return new ExchangeRate(date, crossRate, from, to);
        } catch (Exception e) {
            throw new RuntimeException("Error loading exchange rates: " + e.getMessage(), e);
        }
    }

    private static double getCrossRate(Currency from, Currency to, OpenExchangeRate openExchangeRate) {
        Double fromRate = openExchangeRate.rates().get(from.getCode());
        Double toRate = openExchangeRate.rates().get(to.getCode());
        return toRate / fromRate;
    }

    private static String read(String url) throws IOException {
        Connection.Response response = Jsoup.connect(url)
                .ignoreContentType(true)
                .header("accept", "text/*")
                .method(GET)
                .execute();
        if(response.statusCode() != 200) throw new RuntimeException("Something went wrong");
        return response.body();
    }

}
