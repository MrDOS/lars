package lars.gui;

import java.awt.Color;

import javax.swing.JLabel;

/**
 * A JLabel intended for response messages in interactive UIs.
 * 
 * @author Samuel Coleman, 100105709
 * @version 2012-04-02
 * 
 */
public class MessageLabel extends JLabel
{
    private static final long serialVersionUID = 1L;

    private static final Color ERROR = Color.RED;
    private static Color defaultColor = null;

    public MessageLabel()
    {
        super();

        if (defaultColor == null)
            defaultColor = this.getForeground();
    }

    public MessageLabel(String label)
    {
        super(label);

        if (defaultColor == null)
            defaultColor = this.getForeground();
    }

    public void setMessage(String message)
    {
        this.setForeground(ERROR);
        this.setText(message);
    }

    public void setError(String error)
    {
        this.setForeground(defaultColor);
        this.setText(error);
    }
}
