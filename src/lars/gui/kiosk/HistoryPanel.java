package lars.gui.kiosk;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableModel;

import lars.Account;
import lars.Rental;
import lars.db.RentalDatabase;
import lars.gui.RentalModel;

/**
 * Rental history for a customer.
 * 
 * @author Samuel Coleman, 100105709
 * @version 2012-04-05
 */
public class HistoryPanel extends JPanel implements ActionListener
{
    private static final long serialVersionUID = 1L;

    private Account account;

    private JTable table;
    private JButton toMenu;

    /**
     * Instantiate the panel.
     * 
     * @param account
     *            the account to associate the rental with
     */
    public HistoryPanel(Account account)
    {
        this.account = account;

        this.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        this.add(new JLabel("Welcome back, " + this.account.getName() + ".",
                SwingConstants.CENTER));

        List<Rental> rentals = new ArrayList<Rental>();
        try
        {
            rentals = RentalDatabase.getRentalsByAccount(this.account);
        }
        catch (SQLException e)
        {
        }
        TableModel model = new RentalModel(rentals);
        table = new JTable(model);
        c.gridx = 0;
        c.gridy = 1;
        this.add(new JScrollPane(table), c);

        this.toMenu = new JButton("Return to Main Menu");
        c.gridx = 0;
        c.gridy = 2;
        this.add(toMenu, c);

        this.toMenu.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(this.toMenu))
        {
            KioskFrame.getInstance().showMenu();
        }

        this.table.revalidate();
    }
}
