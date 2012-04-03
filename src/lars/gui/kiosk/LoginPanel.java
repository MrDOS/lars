package lars.gui.kiosk;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lars.Account;
import lars.db.AccountDatabase;
import lars.gui.MessageLabel;

public class LoginPanel extends JPanel implements ActionListener, FocusListener
{
    private static final long serialVersionUID = 1L;
    private static final int ACCOUNT_LENGTH = 8;
    private JTextField account;
    private JButton confirm;
    private MessageLabel message;
    private MessageLabel greeting;
    private int ID;

    public LoginPanel()
    {
        this.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        
        greeting = new MessageLabel();
        greeting.setText("Please Input Your Account ID");
        c.gridx = 0;
        c.gridy = 0;
        this.add(greeting, c);
        
        message = new MessageLabel();
        c.gridx = 0;
        c.gridy = 1;
        this.add(message, c);
        
        account = new JTextField(ACCOUNT_LENGTH);
        c.gridx = 0;
        c.gridy = 2;
        this.add(account, c);
        account.requestFocus();
        
        confirm = new JButton("Confirm");
        c.gridx = 0;
        c.gridy = 3;
        this.add(confirm, c);
        
        confirm.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(confirm))
        {
            try
            {
                ID = Integer.valueOf(account.getText());
            }
            catch (NumberFormatException ex)
            {
                this.message.setError("Invalid Account ID!");
            }

            try
            {
                Account thisAccount = AccountDatabase.getAccountById(ID);
                //TODO: Go to transaction panel
            }
            catch (SQLException e1)
            {
                this.message.setError("No such Account!");
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e)
    {
        account.requestFocus();
    }

    @Override
    public void focusLost(FocusEvent e)
    {
    }
}
