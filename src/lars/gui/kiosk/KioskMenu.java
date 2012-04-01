package lars.gui.kiosk;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class KioskMenu extends JPanel
{
    private static final long serialVersionUID = 1L;

    private JButton checkoutItems;

    public KioskMenu()
    {
        this.setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.ipadx = 40;
        c.ipady = 40;
        
        checkoutItems = new JButton("Begin Checkout");
        c.gridy = 0;
        this.add(checkoutItems, c);
    }
}
