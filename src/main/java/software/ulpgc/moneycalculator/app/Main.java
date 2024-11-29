package software.ulpgc.moneycalculator.app;

import software.ulpgc.moneycalculator.io.*;
import software.ulpgc.moneycalculator.io.interfaces.CurrencyLookup;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("C:/Users/edani/MoneyCalculatorEntregable/src/main/resources/world_currencies.tsv");
        List<Currency> currencies = new FileCurrencyLoader(file, new TsvCurrencyDeserializer()).load();
        CurrencyLookup lookup = new MapCurrencyLookup(currencies);
        JsonExchangeRateDeserializer deserializer = new JsonExchangeRateDeserializer();
        ExchangeRate exchangeRate = new EraioExchangeRateLoader(deserializer).load(lookup.get("BDT"), lookup.get("BBD"));
        System.out.println(exchangeRate);
    }


}
