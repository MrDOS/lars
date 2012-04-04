package lars.gui.admin;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import lars.Account;
import lars.db.AccountDatabase;

/**
 * Account update interface.
 * 
 * @author Jeremy Wheaton, 100105823
 * @version 2012-04-04
 */
public class UpdateAccountDialog extends JDialog implements ActionListener
{
    private static final long serialVersionUID = 1L;

    private AccountsFrame parent;

    private Account account;

    private JTextField nameField;
    private JTextArea addressField;
    private JCheckBox manager;

    private JButton save;
    private JButton cancel;

    public UpdateAccountDialog(AccountsFrame parent, Account account)
    {
        super(AdminFrame.getInstance(), "Add Account");
        this.setLocationByPlatform(true);

        this.parent = parent;
        this.account = account;

        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0;
        c.gridy = 0;
        this.add(new JLabel("Account ID: " + account.getAccountId()), c);

        c.gridx = 0;
        c.gridy = 1;
        this.add(new JLabel("Name:"), c);

        nameField = new JTextField(account.getName(), Account.ACCOUNT_ID_LENGTH);
        c.gridx = 1;
        c.gridy = 1;
        this.add(nameField, c);

        c.gridx = 0;
        c.gridy = 2;
        this.add(new JLabel("Address:"), c);

        addressField = new JTextArea(account.getAddress(), 4, 20);
        c.gridx = 1;
        c.gridy = 2;
        this.add(new JScrollPane(addressField), c);

        c.gridx = 0;
        c.gridy = 3;
        this.add(new JLabel("Manager:"), c);

        manager = new JCheckBox();
        if (account.isManager())
            manager.setSelected(true);
        c.gridx = 1;
        c.gridy = 3;
        this.add(manager, c);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        save = new JButton("Save");
        buttonPanel.add(save);

        cancel = new JButton("Cancel");
        buttonPanel.add(cancel);

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.EAST;
        this.add(buttonPanel, c);

        this.pack();

        this.cancel.addActionListener(this);
        this.save.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(cancel))
            this.dispose();
        else if (e.getSource().equals(save))
        {
            String name = "";
            String address = "";

            name = nameField.getText();
            address = addressField.getText();

            if (name.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Invalid name!",
                        "Error adding account", JOptionPane.ERROR_MESSAGE);
            }
            else if (address.equals(""))
            {
                JOptionPane.showMessageDialog(null,
                        "Invalid address! The address may not be blank.",
                        "Error adding account", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                try
                {
                    this.account = new Account(this.account.getAccountId(),
                            name, address, manager.isSelected());
                    AccountDatabase.updateAccount(this.account);

                    this.parent.refresh();
                    this.dispose();
                }
                catch (SQLException ex)
                {
                    JOptionPane.showMessageDialog(null,
                            "Unable to add account!", "Error adding account",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
