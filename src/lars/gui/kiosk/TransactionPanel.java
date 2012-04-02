package lars.gui.kiosk;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lars.Item;
import lars.Transaction;
import lars.TransactionItem;
import lars.db.ItemDatabase;

/**
 * Transaction menu presented to a customer upon beginning a transaction.
 * 
 * @author Jeremy Wheaton, 100105823
 * @version 2012-04-01
 */
public class TransactionPanel extends JPanel implements ActionListener
{
    private static final long serialVersionUID = 1L;

    private static final int SKU_LENGTH = 8;

    private Transaction transaction;
    private Item item;

    private JTextField skuField;
    private JButton enter;

    public TransactionPanel()
    {
        transaction = new Transaction();

        this.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        
        c.gridx = 0;
        c.gridy = 0;

        skuField = new JTextField(SKU_LENGTH);
        this.add(skuField, c);
        
        c.gridx = 0;
        c.gridy = 1;

        enter = new JButton("Enter item ID");
        this.add(enter, c);

        enter.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(enter))
        {
            int sku = Integer.valueOf(skuField.getText());
            try
            {
                item = ItemDatabase.getIdBySku(sku);
            }
            catch (SQLException e1)
            {
                e1.printStackTrace();
            }
            TransactionItem newItem = new TransactionItem(item, 1, false);
            transaction.addTransactionItem(newItem);
        }
    }
}
