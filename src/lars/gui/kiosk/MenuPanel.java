package lars.gui.kiosk;

import java.awt.Dimension;
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
    private JButton history;

    /**
     * Instantiate the panel.
     */
    public MenuPanel()
    {
        this.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;

        try
        {
            c.gridy = 0;
            this.add(new ImagePanel("res/logo.png"), c);
        }
        catch (IOException e)
        {
            System.err.println("Unable to load logo!");
        }

        checkoutItems = new JButton("Begin Checkout");
        checkoutItems.setPreferredSize(new Dimension(128, (int) checkoutItems
                .getPreferredSize().getHeight()));
        c.gridy = 1;
        c.ipadx = 40;
        c.ipady = 30;
        this.add(checkoutItems, c);

        history = new JButton("Account History");
        history.setPreferredSize(new Dimension(128, (int) history
                .getPreferredSize().getHeight()));
        c.gridy = 2;
        c.ipadx = 40;
        c.ipady = 15;
        this.add(history, c);

        try
        {
            c.gridy = 3;
            c.insets = new Insets(50, 0, 0, 0);
            this.add(new ImagePanel("res/ad.jpg"), c);
        }
        catch (IOException e)
        {
            System.err.println("Unable to load ad banner!");
        }

        checkoutItems.addActionListener(this);
        history.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(checkoutItems))
            KioskFrame.getInstance().showLogin(LoginPanel.DESTINATION_RENTAL);
        else if (e.getSource().equals(history))
            KioskFrame.getInstance().showLogin(LoginPanel.DESTINATION_HISTORY);
    }
}
