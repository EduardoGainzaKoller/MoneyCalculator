package software.ulpgc.moneycalculator.app;

import software.ulpgc.moneycalculator.architecture.io.*;
import software.ulpgc.moneycalculator.architecture.io.interfaces.CurrencyLookup;
import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.model.ExchangeRate;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("C:/Users/edani/MoneyCalculatorEntregable/src/main/resources/world_currencies.tsv");
        List<Currency> currencies = new FileCurrencyLoader(file, new TsvCurrencyDeserializer()).load();
        CurrencyLookup lookup = new MapCurrencyLookup(currencies);
        SwingMainFrame swingMainFrame = new SwingMainFrame(currencies, lookup);
        swingMainFrame.setVisible(true);
    }


}
