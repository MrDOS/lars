package lars.gui.kiosk;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import lars.Transaction;

/**
 * Item checkout interface.
 * 
 * @author Samuel Coleman, 100105709
 * @version 2012-04-01
 */
public class CheckoutPanel extends JPanel
{
    private static final long serialVersionUID = 1L;

    public CheckoutPanel(Transaction transaction)
    {
        this.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        this.add(new JLabel("Checkout plz"), c);
    }
}
