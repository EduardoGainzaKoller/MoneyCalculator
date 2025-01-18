package software.ulpgc.moneycalculator.architecture.view;

import javax.swing.*;
import java.awt.*;

public class SwingMoneyDisplay extends JPanel {
    private JLabel resultLabel;

    public SwingMoneyDisplay() {
        resultLabel = new JLabel("Resultado: ");
        add(resultLabel);
    }


    public void displayResult(double amount, String currency) {
        resultLabel.setText("Resultado: " + amount + " " + currency);
    }
}
