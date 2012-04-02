package lars.gui;

import java.awt.Color;

import javax.swing.JTextField;

/**
 * A JTextField intended for messages in interactive UIs.
 * 
 * @author Samuel Coleman, 100105709
 * @version 2012-04-02
 * 
 */
public class MessageLabel extends JTextField
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
        this.setText(message);
        this.setForeground(ERROR);
    }

    public void setError(String error)
    {
        this.setText(error);
        this.setForeground(defaultColor);
    }
}
