package software.ulpgc.moneycalculator.architecture.view;

import software.ulpgc.moneycalculator.architecture.model.Currency;

import java.util.List;

public interface CurrencyDialog {
    CurrencyDialog define(List<Currency> currencies);
    Currency get();
}
