package lars.gui.kiosk;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import lars.Account;
import lars.LARS;
import lars.Rental;

/**
 * Main kiosk interface.
 * 
 * @author Samuel Coleman, 100105709
 * @version 2012-04-02
 */
public class KioskFrame extends JFrame
{
    private static final long serialVersionUID = 1L;

    private static KioskFrame _instance = null;

    private KioskFrame()
    {
        super(LARS.name + " " + LARS.version);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setUndecorated(true);
        Dimension screenBounds = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(0, 0, screenBounds.width, screenBounds.height);
        this.setResizable(false);

        this.showMenu();
    }

    /**
     * Get the frame instance.
     * 
     * @return the frame instance
     */
    public static KioskFrame getInstance()
    {
        if (_instance == null)
            _instance = new KioskFrame();

        return _instance;
    }

    /**
     * Show the main menu.
     */
    public void showMenu()
    {
        this.setContentPane(new MenuPanel());
        this.validate();
    }

    /**
     * Show the account login screen.
     */
    public void showLogin(int destination)
    {
        this.setContentPane(new LoginPanel(destination));
        this.validate();
    }

    /**
     * Show a new rental to be associated with the given account.
     * 
     * @param account
     *            the account
     */
    public void showRental(Account account)
    {
        this.setContentPane(new RentalPanel(account));
        this.validate();
    }

    /**
     * Show the rental history associated with the given account.
     * 
     * @param account
     *            the account
     */
    public void showHistory(Account account)
    {
        this.setContentPane(new HistoryPanel(account));
        this.validate();
    }

    /**
     * Show the checkout for a rental.
     * 
     * @param account
     *            the account the rental is associated with
     * @param rental
     *            the rental to be checked out
     */
    public void showCheckout(Account account, Rental rental)
    {
        this.setContentPane(new CheckoutPanel(account, rental));
        this.validate();
    }

    /**
     * Show the thankk you message for a rental.
     * 
     * @param account
     *            the account the rental is associated with
     */
    public void showThankYou(Account account)
    {
        this.setContentPane(new ThankYouPanel(account));
        this.validate();
    }
}
