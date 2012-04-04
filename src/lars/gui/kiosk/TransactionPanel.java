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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
        FocusListener
{
    private static final long serialVersionUID = 1L;

    private Account account;
    private Transaction transaction;

    private MessageLabel messageLabel;

    private JTextField skuField;
    private JButton enter;
    private JButton checkout;
    private JButton toMenu;
    private JTable table;

    public TransactionPanel(Account account)
    {
        transaction = new Transaction();

        this.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        skuField = new JTextField(Item.SKU_LENGTH);
        c.gridx = 0;
        c.gridy = 0;
        this.add(skuField, c);
        skuField.requestFocus();

        enter = new JButton("Enter item ID");
        c.gridx = 0;
        c.gridy = 1;
        this.add(enter, c);

        messageLabel = new MessageLabel();
        c.gridx = 0;
        c.gridy = 2;
        this.add(messageLabel, c);

        TableModel model = new TransactionModel(this.transaction);
        table = new JTable(model);
        c.gridx = 0;
        c.gridy = 4;
        this.add(new JScrollPane(table), c);

        checkout = new JButton("Checkout");
        c.gridx = 0;
        c.gridy = 5;
        this.add(checkout, c);

        toMenu = new JButton("Exit To Main Menu");
        c.gridx = 0;
        c.gridy = 6;
        this.add(toMenu, c);

        KioskFrame.getInstance().addFocusListener(this);

        skuField.addActionListener(this);
        checkout.addActionListener(this);
        enter.addActionListener(this);
        toMenu.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(skuField))
        {
            enter.doClick();
        }
        else if (e.getSource().equals(enter))
        {
            int sku = 0;
            try
            {
                sku = Integer.valueOf(skuField.getText());
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
            }
            catch (SQLException e1)
            {
                this.messageLabel.setError("No such SKU!");
                System.err.println(e1.getMessage());
            }

            table.revalidate();
        }
        else if (e.getSource().equals(checkout))
        {
            KioskFrame.getInstance().showCheckout(account, transaction);
        }
        else if (e.getSource().equals(toMenu))
        {
            KioskFrame.getInstance().showMenu();
        }

        skuField.setText("");
        skuField.requestFocus();
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
}
