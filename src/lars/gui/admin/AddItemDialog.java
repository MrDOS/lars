package lars.gui.admin;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import lars.Item;
import lars.ItemType;
import lars.db.ItemDatabase;

/**
 * Item creation interface.
 * 
 * @author Samuel Coleman, 100105709
 * @version 2012-04-04
 */
public class AddItemDialog extends JDialog
{
    private static final long serialVersionUID = 1L;

    private static final int QUANTITY_LENTH = 2;

    JTextField skuField;
    JTextField quantityField;
    JTextArea descriptionField;
    JComboBox<ItemType> type;

    public AddItemDialog()
    {
        super(AdminFrame.getInstance(), "Add Item");
        this.setLocationByPlatform(true);

        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.NORTHWEST;

        c.gridx = 0;
        c.gridy = 0;
        this.add(new JLabel("SKU:"), c);

        skuField = new JTextField(Item.SKU_LENGTH);
        c.gridx = 1;
        c.gridy = 0;
        this.add(skuField, c);

        c.gridx = 0;
        c.gridy = 1;
        this.add(new JLabel("Quantity:"), c);

        quantityField = new JTextField(QUANTITY_LENTH);
        c.gridx = 1;
        c.gridy = 1;
        this.add(quantityField, c);

        c.gridx = 2;
        c.gridy = 0;
        this.add(new JLabel("Description:"), c);

        descriptionField = new JTextArea(null, 3, 20);
        c.gridx = 3;
        c.gridy = 0;
        this.add(new JScrollPane(descriptionField), c);

        c.gridx = 2;
        c.gridy = 1;
        this.add(new JLabel("Type:"), c);

        ItemType types[] = {};
        try
        {
            types = (ItemType[]) ItemDatabase.getItemTypes().toArray();
        }
        catch (SQLException e)
        {
        }

        type = new JComboBox<ItemType>(types);
        c.gridx = 3;
        c.gridy = 1;
        this.add(type, c);

        this.pack();
    }
}
