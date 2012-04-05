package lars.gui.admin;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import lars.Rental;
import lars.db.AccountDatabase;
import lars.gui.AccountModel;
import lars.gui.RentalItemModel;

/**
 * Interface for rental management.
 * 
 * @author Jeremy Wheaton, 100105823
 * @version 2012-04-05
 */
public class RentalsFrame extends AdminInternalFrame implements ActionListener
{
    private static final long serialVersionUID = 1L;

    private JTable rentalTable;
    private JButton updateReturn;
    private JButton processReturn;

    private List<Rental> rentals;

    /**
     * Instantiate the frame.
     */
    public RentalsFrame()
    {
        super("Returns");

        this.add(getReturnsPanel());

        this.refresh();

        updateReturn.addActionListener(this);
        processReturn.addActionListener(this);
    }

    private JPanel getReturnsPanel()
    {
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        //TableModel model = new RentalModel(new ArrayList<Rental>());
        //rentalTable = new JTable(model);
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        panel.add(new JScrollPane(rentalTable), c);

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

    /**
     * Refresh the accounts data table.
     */
    public void refresh()
    {
        try
        {
            this.accounts = AccountDatabase.getAccounts();
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error loading data!",
                    "Account error", JOptionPane.ERROR_MESSAGE);
        }

        this.accountTable.setModel(new AccountModel(this.accounts));
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(addAccount))
        {
            new AddAccountDialog(this).setVisible(true);
        }
        else if (e.getSource().equals(updateAccount))
        {
            int row = this.accountTable.getSelectedRow();
            if (row >= 0)
                new UpdateAccountDialog(this, this.accounts.get(row))
                        .setVisible(true);
        }
        else if (e.getSource().equals(deleteAccount))
        {
            int row = this.accountTable.getSelectedRow();
            if (row >= 0)
            {
                if (JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete this account?",
                        "Account", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                {
                    try
                    {
                        AccountDatabase.deleteAccount(this.accounts.get(row));
                    }
                    catch (SQLException ex)
                    {
                        JOptionPane.showMessageDialog(null,
                                "Error deleting account!", "Account error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }

        this.refresh();
    }
}
