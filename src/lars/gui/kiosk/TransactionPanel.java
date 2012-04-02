package lars.gui.kiosk;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import lars.Transaction;

/**
 * Transaction builder interface.
 * 
 * @author Samuel Coleman, 100105709
 */
public class TransactionPanel extends JPanel
{
    private static final long serialVersionUID = 1L;

    private Transaction transaction;

    public TransactionPanel()
    {
        transaction = new Transaction();

        this.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        this.add(new JLabel("Transterptions"), c);
    }
}
