package lars.gui.admin;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import lars.Account;
import lars.gui.AccountModel;

/**
 * Frame for account management.
 * 
 * @author Samuel Coleman, 100105709
 * @version 2012-04-03
 */
public class AccountsFrame extends AdminInternalFrame
{
    private static final long serialVersionUID = 1L;

    private JTable accountTable;
    private JButton addAccount;
    private JButton updateAccount;
    private JButton deleteAccount;

    public AccountsFrame()
    {
        super("Accounts");

        this.add(getAccountsPanel());
    }

    private JPanel getAccountsPanel()
    {
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        TableModel model = new AccountModel(new ArrayList<Account>());
        accountTable = new JTable(model);
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        panel.add(new JScrollPane(accountTable), c);

        JPanel buttonPanel = new JPanel(new GridBagLayout());

        c.fill = GridBagConstraints.NONE;
        c.weightx = 0;
        c.weighty = 0;

        addAccount = new JButton("Add Account");
        c.gridx = 0;
        c.gridy = 0;
        buttonPanel.add(addAccount, c);

        updateAccount = new JButton("Update Account");
        c.gridx = 1;
        c.gridy = 0;
        buttonPanel.add(updateAccount, c);

        deleteAccount = new JButton("Delete Account");
        c.gridx = 2;
        c.gridy = 0;
        buttonPanel.add(deleteAccount, c);

        c.gridx = 0;
        c.gridy = 1;
        panel.add(buttonPanel, c);

        return panel;
    }
}
