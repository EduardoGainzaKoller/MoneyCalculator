package software.ulpgc.moneycalculator.architecture.io;

import com.google.gson.Gson;
import software.ulpgc.moneycalculator.architecture.io.interfaces.ExchangeRateDeserializer;
import software.ulpgc.moneycalculator.architecture.io.pojos.OpenExchangeRate;

public class JsonExchangeRateDeserializer implements ExchangeRateDeserializer {

    @Override
    public OpenExchangeRate deserialize(String json) {
        return new Gson().fromJson(json, OpenExchangeRate.class);
    }
}
