package lars.gui.kiosk;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginPanel extends JPanel implements ActionListener
{
    private static final long serialVersionUID = 1L;
    private static final String ACCOUNT_LENGTH = null;
    private JTextField account;

    public LoginPanel()
    {
        this.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        
        account = new JTextField(ACCOUNT_LENGTH);
        c.gridx = 0;
        c.gridy = 0;
        
        
    }

    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        // TODO Auto-generated method stub
        
    }

}
