package lars.gui.admin;

import java.awt.Dimension;

import javax.swing.JInternalFrame;

public class AdminInternalFrame extends JInternalFrame
{
    private static final long serialVersionUID = 1L;

    private static final Dimension SIZE = new Dimension(600, 400);

    public AdminInternalFrame(String title)
    {
        super(title, true, true, true);

        this.setMinimumSize(SIZE);
        this.setSize(SIZE);
    }

    public boolean isOpen()
    {
        return this.isVisible();
    }

    public void close()
    {
        this.setVisible(false);
    }
    
    public boolean isInternalFrame()
    {
        return true;
    }
}
