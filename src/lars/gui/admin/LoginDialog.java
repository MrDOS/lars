package lars.gui.admin;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import lars.Account;
import lars.db.AccountDatabase;

/**
 * Modal administative login dialog.
 * 
 * @author Samuel Coleman, 100105709
 * @version 2012-04-03
 */
public class LoginDialog extends JDialog implements ActionListener
{
    private static final long serialVersionUID = 1L;

    private JTextField accountField;
    private JButton login;

    /**
     * Instantiate the dialog.
     */
    public LoginDialog()
    {
        super(AdminFrame.getInstance(), "Login", true);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        this.getContentPane().setLayout(
                new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));

        this.add(new JLabel("Account ID:"));

        accountField = new JTextField(Account.ACCOUNT_ID_LENGTH);
        this.add(accountField);

        login = new JButton("Login");
        this.add(login);

        this.pack();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width / 2) - (this.getWidth() / 2),
                (screenSize.height / 2) - (this.getHeight() / 2));

        accountField.addActionListener(this);
        login.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(this.accountField))
        {
            this.login.doClick();
        }
        else if (e.getSource().equals(this.login))
        {
            try
            {
                int id = Integer.valueOf(this.accountField.getText());
                Account account = AccountDatabase.getAccountById(id);

                if (account.isManager())
                {
                    AdminFrame.getInstance().setAccount(account);
                    this.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Non-privileged ID!",
                            "Login Failure", JOptionPane.ERROR_MESSAGE);
                }
            }
            catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(null, "Invalid account ID!",
                        "Login Failure", JOptionPane.ERROR_MESSAGE);
            }
            catch (SQLException ex)
            {
                JOptionPane.showMessageDialog(null, "No such account!",
                        "Login Failure", JOptionPane.ERROR_MESSAGE);
            }
        }

        this.accountField.setText("");
    }
}
