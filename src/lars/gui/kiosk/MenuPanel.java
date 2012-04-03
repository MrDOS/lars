package lars.gui.kiosk;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

import lars.gui.ImagePanel;

/**
 * Main menu presented to a customer approaching the kiosk.
 * 
 * @author Jeremy Wheaton, 100105823
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

        try
        {
            c.gridy = 0;
            this.add(new ImagePanel("res/SwayingPalms.png"), c);
        }
        catch (IOException e)
        {
            System.err.println("Unable to load logo!");
        }

        checkoutItems = new JButton("Begin Checkout");
        c.gridy = 1;
        this.add(checkoutItems, c);

        try
        {
            c.gridy = 2;
            c.insets = new Insets(50, 0, 0, 0);
            this.add(new ImagePanel("res/Scorcher.jpg"), c);
        }
        catch (IOException e)
        {
            System.err.println("Unable to load ad banner!");
        }

        checkoutItems.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(checkoutItems))
            KioskFrame.getInstance().showLogin();
    }
}
