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

import lars.RentalItem;
import lars.db.RentalDatabase;
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

    private JTable rentalItemsTable;
    private JButton updateDue;
    private JButton processReturn;

    private List<RentalItem> rentalItems;

    /**
     * Instantiate the frame.
     */
    public RentalsFrame()
    {
        super("Rentals");

        this.add(getRentalsPanel());

        this.refresh();

        updateDue.addActionListener(this);
        processReturn.addActionListener(this);
    }

    private JPanel getRentalsPanel()
    {
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        TableModel model = new RentalItemModel(new ArrayList<RentalItem>());
        rentalItemsTable = new JTable(model);
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        panel.add(new JScrollPane(rentalItemsTable), c);

        JPanel buttonPanel = new JPanel(new GridBagLayout());

        c.fill = GridBagConstraints.NONE;
        c.weightx = 0;
        c.weighty = 0;

        updateDue = new JButton("Update Due Date");
        c.gridx = 0;
        c.gridy = 0;
        buttonPanel.add(updateDue, c);

        processReturn = new JButton("Mark as Returned");
        c.gridx = 1;
        c.gridy = 0;
        buttonPanel.add(processReturn, c);

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
            this.rentalItems = RentalDatabase.getUnreturnedRentalItems();

            this.rentalItemsTable.setModel(new RentalItemModel(this.rentalItems));
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error loading data!",
                    "Rental error", JOptionPane.ERROR_MESSAGE);
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(this.processReturn))
        {
            try
            {
                RentalItem rentalItem = this.rentalItems
                        .get(this.rentalItemsTable.getSelectedRow());
                rentalItem.setReturned(true);
                RentalDatabase.updateRentalItem(rentalItem);
            }
            catch (SQLException ex)
            {
                JOptionPane.showMessageDialog(null, "Error updating rental!",
                        "Rental error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (e.getSource().equals(updateDue))
        {
            int row = this.rentalItemsTable.getSelectedRow();
            if (row >= 0)
                new UpdateRentalDialog(this, this.rentalItems.get(row))
                        .setVisible(true);
        }

        this.refresh();
    }
}
