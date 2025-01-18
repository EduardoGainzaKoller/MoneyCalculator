package software.ulpgc.moneycalculator.architecture.io.interfaces;

import software.ulpgc.moneycalculator.architecture.model.Currency;

import java.io.IOException;
import java.util.List;

public interface CurrencyLoader {
    List<Currency> load() throws IOException;
}
