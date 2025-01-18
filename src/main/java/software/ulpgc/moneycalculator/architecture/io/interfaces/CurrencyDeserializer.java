package software.ulpgc.moneycalculator.architecture.io.interfaces;

import software.ulpgc.moneycalculator.architecture.model.Currency;

public interface CurrencyDeserializer {
    Currency deserialize(String line);
}
