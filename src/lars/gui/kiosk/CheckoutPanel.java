package lars.gui.kiosk;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Item checkout interface.
 * 
 * @author Samuel Coleman, 100105709
 */
public class CheckoutPanel extends JPanel
{
    private static final long serialVersionUID = 1L;

    public CheckoutPanel()
    {
        this.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        
        c.gridx = 0;
        c.gridy = 0;
        this.add(new JLabel("Checkout plz"), c);
    }
}
