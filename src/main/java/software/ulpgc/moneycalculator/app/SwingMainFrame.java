package software.ulpgc.moneycalculator.app;

import javax.swing.*;
import java.awt.*;

public class SwingMainFrame extends JFrame {

    public SwingMainFrame() throws HeadlessException {
        this.setTitle("MineSweeper");
        this.setSize(new Dimension(750, 750));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
