package lars.gui.kiosk;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import lars.Account;
import lars.Item;
import lars.Transaction;
import lars.TransactionItem;
import lars.db.ItemDatabase;
import lars.gui.MessageLabel;
import lars.gui.TransactionModel;

/**
 * Transaction menu presented to a customer upon beginning a transaction.
 * 
 * @author Jeremy Wheaton, 100105823
 * @author Samuel Coleman, 100105709
 * @version 2012-04-02
 */
public class TransactionPanel extends JPanel implements ActionListener,
        FocusListener, TableModelListener
{
    private static final long serialVersionUID = 1L;

    private Account account;
    private Transaction transaction;

    private MessageLabel messageLabel;

    private JTextField skuField;
    private JButton enter;
    private JTable table;
    private JLabel subtotal;
    private JButton delete;
    private JButton checkout;
    private JButton toMenu;

    public TransactionPanel(Account account)
    {
        this.transaction = new Transaction();

        this.account = account;

        this.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        this.add(new JLabel("Welcome back, " + this.account.getName() + ".",
                SwingConstants.CENTER));

        c.gridx = 0;
        c.gridy = 1;
        this.add(new JLabel("Enter item ID:"), c);

        this.skuField = new JTextField(Item.SKU_LENGTH);
        c.gridx = 0;
        c.gridy = 2;
        this.add(skuField, c);
        skuField.requestFocus();

        this.enter = new JButton("Enter");
        c.gridx = 0;
        c.gridy = 3;
        this.add(enter, c);

        this.messageLabel = new MessageLabel();
        c.gridx = 0;
        c.gridy = 4;
        this.add(messageLabel, c);

        TableModel model = new TransactionModel(this.transaction, true);
        table = new JTable(model);
        c.gridx = 0;
        c.gridy = 5;
        this.add(new JScrollPane(table), c);

        JPanel subtotalPanel = new JPanel();

        subtotalPanel.add(new JLabel("Subtotal:"), c);

        this.subtotal = new JLabel("$0.00");
        subtotalPanel.add(subtotal, c);

        c.gridx = 0;
        c.gridy = 6;
        this.add(subtotalPanel, c);

        this.delete = new JButton("Remove selected");
        c.gridx = 0;
        c.gridy = 7;
        this.add(delete, c);

        this.checkout = new JButton("Checkout");
        c.gridx = 0;
        c.gridy = 8;
        c.ipadx = 20;
        c.ipady = 10;
        this.add(checkout, c);

        this.toMenu = new JButton("Return to Main Menu");
        c.gridx = 0;
        c.gridy = 9;
        c.ipadx = 0;
        c.ipady = 0;
        this.add(toMenu, c);

        KioskFrame.getInstance().addFocusListener(this);

        this.skuField.addActionListener(this);
        this.delete.addActionListener(this);
        this.checkout.addActionListener(this);
        this.enter.addActionListener(this);
        this.toMenu.addActionListener(this);
        this.table.getModel().addTableModelListener(this);
    }

    private void updateSubtotal()
    {
        this.subtotal.setText(new DecimalFormat("$#.00")
                .format(this.transaction.getTotalPrice() / 100));
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(this.skuField))
        {
            this.enter.doClick();
        }
        else if (e.getSource().equals(this.enter))
        {
            int sku = 0;
            try
            {
                sku = Integer.valueOf(this.skuField.getText());
            }
            catch (NumberFormatException ex)
            {
                this.messageLabel.setError("Invalid SKU!");
            }

            try
            {
                Item item = ItemDatabase.getItemBySku(sku);
                TransactionItem transItem = new TransactionItem(item, false);
                transaction.addTransactionItem(transItem);
                this.messageLabel.setMessage("");
            }
            catch (SQLException ex)
            {
                this.messageLabel.setError("No such SKU!");
            }
        }
        else if (e.getSource().equals(this.delete))
        {
            int rows[] = this.table.getSelectedRows();
            for (int i = rows.length - 1; i >= 0; i--)
                this.transaction.getTransactionItems().remove(rows[i]);
            this.table.clearSelection();
        }
        else if (e.getSource().equals(this.checkout))
        {
            KioskFrame.getInstance().showCheckout(this.account,
                    this.transaction);
        }
        else if (e.getSource().equals(this.toMenu))
        {
            KioskFrame.getInstance().showMenu();
        }

        this.table.revalidate();
        this.skuField.setText("");
        this.updateSubtotal();
        this.skuField.requestFocus();
    }

    @Override
    public void focusGained(FocusEvent e)
    {
        skuField.requestFocus();
    }

    @Override
    public void focusLost(FocusEvent e)
    {
    }

    @Override
    public void tableChanged(TableModelEvent e)
    {
        this.updateSubtotal();
    }
}
