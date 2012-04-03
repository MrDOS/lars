package lars.gui.admin;

import javax.swing.JFrame;

/**
 * Top-level administative interface.
 * 
 * @author Samuel Coleman, 100105709
 * @version 2012-04-02
 */
public class AdminFrame extends JFrame
{
    private static final long serialVersionUID = 1L;

    public AdminFrame()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
