package lars.gui.admin;

import java.awt.Dimension;

import javax.swing.JInternalFrame;

/**
 * A generic internal frame predefined with a size for convenience.
 * 
 * @author Samuel Coleman, 100105709
 * @version 2012-04-03
 */
public abstract class AdminInternalFrame extends JInternalFrame
{
    private static final long serialVersionUID = 1L;

    private static final Dimension SIZE = new Dimension(600, 400);

    /**
     * Instantiate the frame.
     * 
     * @param title
     *            the title of the frame
     */
    public AdminInternalFrame(String title)
    {
        super(title, true, true, true);

        this.setMinimumSize(SIZE);
        this.setSize(SIZE);
    }

    /**
     * Refresh any dynamic data within the frame.
     */
    public abstract void refresh();
}
