package software.ulpgc.moneycalculator.io.interfaces;

import software.ulpgc.moneycalculator.io.pojos.OpenExchangeRate;

public interface ExchangeRateDeserializer {
    Object deserialize(String line);
}
