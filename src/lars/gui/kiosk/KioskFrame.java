package lars.gui.kiosk;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import lars.LARS;

/**
 * Main kiosk interface.
 * 
 * @author Samuel Coleman, 100105709
 * @version 2012-04-01
 */
public class KioskFrame extends JFrame
{
    private static final long serialVersionUID = 1L;

    private static KioskFrame _instance = null;

    private KioskFrame()
    {
        super(LARS.name + " " + LARS.version);

        this.setUndecorated(true);
        Dimension screenBounds = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(0, 0, screenBounds.width, screenBounds.height);
        this.setResizable(false);

        this.showMenu();
    }

    public static KioskFrame getInstance()
    {
        if (_instance == null)
            _instance = new KioskFrame();

        return _instance;
    }

    public void showMenu()
    {
        this.setContentPane(new MenuPanel());
        this.validate();
    }

    public void showCheckout()
    {
        this.setContentPane(new CheckoutPanel());
        this.validate();
    }
}
