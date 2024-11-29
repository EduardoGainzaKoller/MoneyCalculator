package software.ulpgc.moneycalculator.io.interfaces;

import software.ulpgc.moneycalculator.model.Currency;

public interface CurrencyDeserializer {
    Currency deserialize(String line);
}
