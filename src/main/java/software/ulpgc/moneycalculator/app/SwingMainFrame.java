package software.ulpgc.moneycalculator.app;

import software.ulpgc.moneycalculator.architecture.io.EraioExchangeRateLoader;
import software.ulpgc.moneycalculator.architecture.io.JsonExchangeRateDeserializer;
import software.ulpgc.moneycalculator.architecture.io.interfaces.CurrencyLookup;
import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.model.ExchangeRate;
import software.ulpgc.moneycalculator.architecture.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SwingMainFrame extends JFrame {
    private final SwingMoneyDialog moneyDialog;
    private final SwingCurrencyDialog currencyDialog;
    private final SwingMoneyDisplay moneyDisplay;
    private final CurrencyLookup lookup;

    public SwingMainFrame(List<Currency> currencies, CurrencyLookup lookup) {
        setTitle("Currency Exchange");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        this.lookup = lookup;

        moneyDialog = new SwingMoneyDialog();
        currencyDialog = new SwingCurrencyDialog(currencies);
        moneyDisplay = new SwingMoneyDisplay();
        JButton exchangeButton = new JButton("Exchange");

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add((JPanel) moneyDialog, BorderLayout.NORTH);
        inputPanel.add((JPanel) currencyDialog, BorderLayout.SOUTH);

        JPanel resultPanel = new JPanel();
        resultPanel.add((JPanel) moneyDisplay);

        exchangeButton.addActionListener(new ExchangeButtonListener());

        add(inputPanel, BorderLayout.NORTH);
        add(exchangeButton, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class ExchangeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double amount = moneyDialog.getAmount();
                String fromCurrency = currencyDialog.getFromCurrency();
                String toCurrency = currencyDialog.getToCurrency();

                double convertedAmount = convertCurrency(amount, fromCurrency, toCurrency);

                moneyDisplay.displayResult(convertedAmount, toCurrency);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(SwingMainFrame.this, "Invalid amount", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        JsonExchangeRateDeserializer deserializer = new JsonExchangeRateDeserializer();

        ExchangeRate exchangeRate = new EraioExchangeRateLoader(deserializer)
                                    .load(lookup.get(fromCurrency), lookup.get(toCurrency));
        return exchangeRate.getRate() * amount;
    }


}