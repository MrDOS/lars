package lars.gui.kiosk;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import lars.Account;
import lars.Rental;
import lars.TaxCalculator;
import lars.db.RentalDatabase;
import lars.gui.RentalItemModel;

/**
 * Item checkout interface.
 * 
 * @author Samuel Coleman, 100105709
 * @author Jeremy Wheaton, 100105823
 * @version 2012-04-01
 */
public class CheckoutPanel extends JPanel implements ActionListener
{
    private static final long serialVersionUID = 1L;

    private TableModel model;
    private JButton confirm;
    private JButton toMenu;
    private JLabel subtotal;
    private JLabel tax;
    private JLabel total;

    private Rental rental;
    private Account account;

    /**
     * Instantiate the panel.
     * 
     * @param account
     *            the account of the user interacting with the kiosk
     * @param rental
     *            the rental to be checked out
     */
    public CheckoutPanel(Account account, Rental rental)
    {
        this.setLayout(new GridBagLayout());

        this.rental = rental;
        this.account = account;

        GridBagConstraints c = new GridBagConstraints();
        model = new RentalItemModel(this.rental.getRentalItems());
        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        c.gridx = 0;
        c.gridy = 0;
        this.add(scroll, c);

        JPanel subtotalPanel = new JPanel();

        subtotalPanel.add(new JLabel("Subtotal:"), c);

        this.subtotal = new JLabel("$0.00");
        subtotalPanel.add(subtotal, c);

        c.gridx = 0;
        c.gridy = 1;
        this.add(subtotalPanel, c);

        JPanel taxPanel = new JPanel();

        taxPanel.add(new JLabel("Tax:"), c);

        this.tax = new JLabel("$0.00");
        taxPanel.add(tax, c);

        c.gridx = 0;
        c.gridy = 2;
        this.add(taxPanel, c);

        JPanel totalPanel = new JPanel();

        totalPanel.add(new JLabel("Total:"), c);

        this.total = new JLabel("$0.00");
        totalPanel.add(total, c);

        c.gridx = 0;
        c.gridy = 3;
        this.add(totalPanel, c);

        confirm = new JButton("Confirm");
        c.gridx = 0;
        c.gridy = 4;
        this.add(confirm, c);

        toMenu = new JButton("Return to Main Menu");
        c.gridx = 0;
        c.gridy = 6;
        this.add(toMenu, c);

        updateSubtotal();
        updateTax();
        updateTotal();

        confirm.addActionListener(this);
        toMenu.addActionListener(this);
    }

    private void updateSubtotal()
    {
        this.subtotal.setText(new DecimalFormat("$#0.00")
                .format((double) this.rental.getTotalPrice() / 100));
    }

    private void updateTax()
    {
        this.tax.setText(new DecimalFormat("$#0.00")
                .format((double) TaxCalculator.getTax(this.rental
                        .getTotalPrice()) / 100));
    }

    private void updateTotal()
    {
        this.total.setText(new DecimalFormat("$#0.00")
                .format((double) TaxCalculator.getTotalWithTax(this.rental
                        .getTotalPrice()) / 100));
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(confirm))
        {
            try
            {
                this.rental.setAccount(this.account);
                RentalDatabase.insertRental(this.rental);
            }
            catch (SQLException e1)
            {
                e1.printStackTrace();
            }
            KioskFrame.getInstance().showThankYou(this.account);
        }
        else if (e.getSource().equals(toMenu))
        {
            KioskFrame.getInstance().showMenu();
        }
    }
}
