package software.ulpgc.moneycalculator.architecture.view;

import software.ulpgc.moneycalculator.architecture.model.Currency;
import software.ulpgc.moneycalculator.architecture.model.Money;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingMoneyDialog extends JPanel {

    private JTextField amountField;

    public SwingMoneyDialog() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Cantidad:");
        amountField = new JTextField(10);
        add(label, BorderLayout.WEST);
        add(amountField, BorderLayout.CENTER);
    }

    public double getAmount() throws NumberFormatException {
        return Double.parseDouble(amountField.getText());
    }
}
