package lars.gui.kiosk;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lars.Account;
import lars.db.AccountDatabase;
import lars.gui.MessageLabel;

/**
 * Login GUI panel to log into customer account.
 * 
 * @author Jeremy Wheaton
 * @version April 2, 2012
 */
public class LoginPanel extends JPanel implements ActionListener, FocusListener
{
    private static final long serialVersionUID = 1L;
    private static final int ACCOUNT_LENGTH = 8;
    private JTextField accountField;
    private JButton confirm;
    private JButton toMenu;
    private MessageLabel message;

    public LoginPanel()
    {
        this.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        this.add(new JLabel("Please input your account ID"), c);

        message = new MessageLabel();
        c.gridx = 0;
        c.gridy = 1;
        this.add(message, c);

        accountField = new JTextField(ACCOUNT_LENGTH);
        c.gridx = 0;
        c.gridy = 2;
        this.add(accountField, c);
        accountField.requestFocus();

        confirm = new JButton("Confirm");
        c.gridx = 0;
        c.gridy = 3;
        this.add(confirm, c);
        
        toMenu = new JButton("Exit To Main Menu");
        c.gridx = 0;
        c.gridy = 4;
        this.add(toMenu, c);
        
        KioskFrame.getInstance().addFocusListener(this);

        accountField.addActionListener(this);
        confirm.addActionListener(this);
        toMenu.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(accountField))
        {
            confirm.doClick();
        }
        else if (e.getSource().equals(confirm))
        {
            try
            {
                int id = Integer.valueOf(accountField.getText());
                Account account = AccountDatabase.getAccountById(id);
                KioskFrame.getInstance().showTransaction(account);
            }
            catch (NumberFormatException ex)
            {
                this.message.setError("Invalid account ID!");
            }
            catch (SQLException ex)
            {
                this.message.setError("No such account!");
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException ex2)
                {
                }
                KioskFrame.getInstance().showTransaction(null);
            }
        }
        else if (e.getSource().equals(toMenu))
        {
            KioskFrame.getInstance().showMenu();
        }

        accountField.requestFocus();
    }

    @Override
    public void focusGained(FocusEvent e)
    {
        accountField.requestFocus();
    }

    @Override
    public void focusLost(FocusEvent e)
    {
    }
}
