package software.ulpgc.moneycalculator.architecture.view;

import software.ulpgc.moneycalculator.architecture.model.Currency;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingCurrencyDialog extends JPanel {
    private final JComboBox<String> fromCurrencyCombo;
    private final JComboBox<String> toCurrencyCombo;
    private final List<Currency> currencies;

    public SwingCurrencyDialog(List<Currency> currencies) {
        this.currencies = currencies;
        setLayout(new GridLayout(1, 4, 10, 10));
        JLabel fromLabel = new JLabel("From:");
        String[] currencyArray = getCodes();
        fromCurrencyCombo = new JComboBox<>(currencyArray);
        JLabel toLabel = new JLabel("To:");
        toCurrencyCombo = new JComboBox<>(currencyArray);

        add(fromLabel);
        add(fromCurrencyCombo);
        add(toLabel);
        add(toCurrencyCombo);
    }

    private String[] getCodes() {
        String[] currencyArray = this.currencies.stream()
                .map(Currency::getCode)
                .toArray(String[]::new);
        return currencyArray;
    }

    public String getFromCurrency() {
        return (String) fromCurrencyCombo.getSelectedItem();
    }

    public String getToCurrency() {
        return (String) toCurrencyCombo.getSelectedItem();
    }
}
