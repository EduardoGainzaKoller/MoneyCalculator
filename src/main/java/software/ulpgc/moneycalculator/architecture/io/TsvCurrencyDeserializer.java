package software.ulpgc.moneycalculator.architecture.io;

import software.ulpgc.moneycalculator.architecture.io.interfaces.CurrencyDeserializer;
import software.ulpgc.moneycalculator.architecture.model.Currency;

public class TsvCurrencyDeserializer implements CurrencyDeserializer {

    private static final int CODE = 0;
    private static final int NAME = 1;
    private static final int SYMBOL = 2;

    @Override
    public Currency deserialize(String line) {
        return deserialize(line.split("\t"));
    }

    private Currency deserialize(String[] fields) {
        return new Currency(fields[CODE], fields[NAME], fields[SYMBOL]);
    }
}
