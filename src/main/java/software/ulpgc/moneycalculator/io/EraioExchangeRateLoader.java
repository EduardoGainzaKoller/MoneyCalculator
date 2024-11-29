package software.ulpgc.moneycalculator.io;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import software.ulpgc.moneycalculator.io.interfaces.ExchangeRateDeserializer;
import software.ulpgc.moneycalculator.io.interfaces.ExchangeRateLoader;
import software.ulpgc.moneycalculator.io.pojos.OpenExchangeRate;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
            throw new RuntimeException("Error al cargar las tasas de cambio: " + e.getMessage(), e);
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
