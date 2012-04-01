package lars.gui.kiosk;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


/**
 * Main menu presented to a customer approaching the kiosk.
 * 
 * @author Samuel Coleman, 100105709
 * @version 2012-04-01
 */
public class MenuPanel extends JPanel implements ActionListener
{
    private static final long serialVersionUID = 1L;

    private JButton checkoutItems;

    public MenuPanel()
    {
        this.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.ipadx = 40;
        c.ipady = 40;

        checkoutItems = new JButton("Begin Checkout");
        c.gridy = 0;
        this.add(checkoutItems, c);

        checkoutItems.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(checkoutItems))
            KioskFrame.getInstance().showCheckout();
    }
}
