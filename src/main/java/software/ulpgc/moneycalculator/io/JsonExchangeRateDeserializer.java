package software.ulpgc.moneycalculator.io;

import com.google.gson.Gson;
import software.ulpgc.moneycalculator.io.interfaces.ExchangeRateDeserializer;
import software.ulpgc.moneycalculator.io.pojos.OpenExchangeRate;

public class JsonExchangeRateDeserializer implements ExchangeRateDeserializer {

    @Override
    public OpenExchangeRate deserialize(String json) {
        return new Gson().fromJson(json, OpenExchangeRate.class);
    }
}
