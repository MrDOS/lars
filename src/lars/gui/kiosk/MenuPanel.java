package lars.gui.kiosk;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import lars.gui.ImagePanel;

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
    private ImagePanel images;

    public MenuPanel()
    {
        this.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.ipadx = 40;
        c.ipady = 40;
        
        images = new ImagePanel("res/SwayingPalms.png");
        c.gridy = 0;
        this.add(images, c);
        
        checkoutItems = new JButton("Begin Checkout");
        c.gridy = 1;
        this.add(checkoutItems, c);
        
        images = new ImagePanel("res/Scorcher.png");
        c.gridy = 2;
        c.insets = new Insets(50, 0, 0, 0);
        this.add(images, c);

        checkoutItems.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(checkoutItems))
            KioskFrame.getInstance().showLogin();
    }
}
