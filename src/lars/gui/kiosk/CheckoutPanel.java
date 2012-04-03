package lars.gui.kiosk;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import lars.Transaction;
import lars.gui.TransactionTableModel;

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

    public CheckoutPanel(Transaction transaction)
    {
        this.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        model = new TransactionTableModel(transaction);
        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        c.gridx = 0;
        c.gridy = 0;
        this.add(scroll, c);
        
        confirm = new JButton("Confirm");
        c.gridx = 0;
        c.gridy = 1;
        this.add(confirm, c);
        
        confirm.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(confirm))
        {
            System.out.println("Yippee");
            KioskFrame.getInstance().showMenu();
        }
    }
}
