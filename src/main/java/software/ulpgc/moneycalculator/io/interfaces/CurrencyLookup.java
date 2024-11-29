package software.ulpgc.moneycalculator.io.interfaces;

import software.ulpgc.moneycalculator.model.Currency;

public interface CurrencyLookup {
    Currency get(String code);
}
