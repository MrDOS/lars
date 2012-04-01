package lars.gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import lars.LARS;
import lars.gui.kiosk.KioskMenu;

/**
 * Main checkout kiosk interface.
 * 
 * @author Samuel Coleman, 100105709
 * @version 2012-04-01
 */
public class KioskFrame extends JFrame
{
    private static final long serialVersionUID = 1L;

    public KioskFrame()
    {
        super();

        this.setUndecorated(true);
        Dimension screenBounds = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(0, 0, screenBounds.width, screenBounds.height);
        this.setResizable(false);

        this.setTitle(LARS.name + " " + LARS.version);
        
        this.setContentPane(new KioskMenu());
    }
}
