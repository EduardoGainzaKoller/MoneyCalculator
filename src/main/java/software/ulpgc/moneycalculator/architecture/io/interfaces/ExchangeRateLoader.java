package software.ulpgc.moneycalculator.architecture.io.interfaces;

import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.model.ExchangeRate;

import java.time.LocalDate;

public interface ExchangeRateLoader {
    ExchangeRate load(Currency from, Currency to);
    ExchangeRate load(Currency from, Currency to, LocalDate date);
}
