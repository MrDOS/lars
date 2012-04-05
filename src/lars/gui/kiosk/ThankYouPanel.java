package lars.gui.kiosk;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import lars.Account;

/**
 * Thank you panel for a finished checkout.
 * 
 * @author Jeremy Wheaton, 100105823
 * @version 2012-04-04
 */
public class ThankYouPanel extends JPanel implements ActionListener
{
    private static final long serialVersionUID = 1L;

    private static final int MESSAGE_DURATION = 2000;

    private Account account;
    private Timer timer;

    /**
     * Instantiates the panel, prints message
     * 
     * @param account
     *            The account to thank
     */
    public ThankYouPanel(Account account)
    {
        this.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        this.account = account;

        c.gridx = 0;
        c.gridy = 0;
        this.add(new JLabel("Thank you, " + this.account.getName()
                + ", for your buisness."), c);

        c.gridx = 0;
        c.gridy = 1;
        this.add(new JLabel("We hope to see you again!"), c);

        timer = new Timer(MESSAGE_DURATION, this);
        timer.setRepeats(false);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(timer))
        {
            KioskFrame.getInstance().showMenu();
        }
    }
}
