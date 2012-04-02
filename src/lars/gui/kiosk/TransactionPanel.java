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
import javax.swing.JTextField;

import lars.Item;
import lars.Transaction;
import lars.TransactionItem;
import lars.db.ItemDatabase;
import lars.gui.MessageLabel;

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

    private static final int SKU_LENGTH = 8;

    private Transaction transaction;

    private MessageLabel messageLabel;

    private JTextField skuField;
    private JButton enter;

    public TransactionPanel()
    {
        transaction = new Transaction();

        this.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        skuField = new JTextField(SKU_LENGTH);
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

        KioskFrame.getInstance().addFocusListener(this);

        enter.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(enter))
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
                Item item = ItemDatabase.getIdBySku(sku);
                transaction.addTransactionItem(new TransactionItem(item, 1,
                        false));
            }
            catch (SQLException e1)
            {
                this.messageLabel.setError("No such SKU!");
            }
        }

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
