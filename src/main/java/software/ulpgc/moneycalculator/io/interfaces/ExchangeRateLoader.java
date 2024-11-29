package software.ulpgc.moneycalculator.io.interfaces;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;

import java.time.LocalDate;

public interface ExchangeRateLoader {
    ExchangeRate load(Currency from, Currency to);
    ExchangeRate load(Currency from, Currency to, LocalDate date);
}
