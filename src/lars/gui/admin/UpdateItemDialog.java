package lars.gui.admin;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
public class UpdateItemDialog extends JDialog implements ActionListener
{
    private static final long serialVersionUID = 1L;

    private static final int QUANTITY_LENTH = 2;

    private AdminInternalFrame parent;

    private Item item;

    private JTextField skuField;
    private JTextField quantityField;
    private JTextArea descriptionField;
    private JComboBox type;

    private JButton save;
    private JButton cancel;

    public UpdateItemDialog(AdminInternalFrame parent, Item item)
    {
        super(AdminFrame.getInstance(), "Add Item");
        this.setLocationByPlatform(true);

        this.parent = parent;
        this.item = item;

        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0;
        c.gridy = 0;
        this.add(new JLabel("SKU:"), c);

        skuField = new JTextField(String.valueOf(item.getSku()),
                Item.SKU_LENGTH);
        c.gridx = 1;
        c.gridy = 0;
        this.add(skuField, c);

        c.gridx = 0;
        c.gridy = 1;
        this.add(new JLabel("Quantity:"), c);

        quantityField = new JTextField(String.valueOf(item.getQuantity()),
                QUANTITY_LENTH);
        c.gridx = 1;
        c.gridy = 1;
        this.add(quantityField, c);

        c.gridx = 0;
        c.gridy = 2;
        this.add(new JLabel("Description:"), c);

        descriptionField = new JTextArea(item.getDescription(), 3, 20);
        c.gridx = 1;
        c.gridy = 2;
        this.add(new JScrollPane(descriptionField), c);

        c.gridx = 0;
        c.gridy = 3;
        this.add(new JLabel("Type:"), c);

        Object types[] = {};
        try
        {
            types = ItemDatabase.getItemTypes().toArray();
        }
        catch (SQLException e)
        {
        }

        type = new JComboBox(types);
        c.gridx = 1;
        c.gridy = 3;
        this.add(type, c);
        type.setSelectedItem(item.getType());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        save = new JButton("Save");
        buttonPanel.add(save);

        cancel = new JButton("Cancel");
        buttonPanel.add(cancel);

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.EAST;
        this.add(buttonPanel, c);

        this.pack();

        this.cancel.addActionListener(this);
        this.save.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(cancel))
            this.dispose();
        else if (e.getSource().equals(save))
        {
            ItemType type = (ItemType) this.type.getSelectedItem();
            int sku = 0;
            String description = "";
            int quantity = 0;

            try
            {
                sku = Integer.valueOf(skuField.getText());
                description = descriptionField.getText();
                quantity = Integer.valueOf(quantityField.getText());
            }
            catch (NumberFormatException ex)
            {
            }

            if (sku == 0)
            {
                JOptionPane.showMessageDialog(null, "Invalid SKU!",
                        "Error adding item", JOptionPane.ERROR_MESSAGE);
            }
            else if (description.equals(""))
            {
                JOptionPane
                        .showMessageDialog(
                                null,
                                "Invalid description! The description may not be blank.",
                                "Error adding item", JOptionPane.ERROR_MESSAGE);
            }
            else if (type == null)
            {
                JOptionPane
                        .showMessageDialog(
                                null,
                                "Invalid item type! If no types are available, you must define one.",
                                "Error adding item", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                try
                {
                    ItemDatabase.updateItem(new Item(this.item.getModifiers(),
                            type, sku, description, quantity), this.item
                            .getSku());

                    parent.refresh();
                    this.dispose();
                }
                catch (SQLException ex)
                {
                    JOptionPane.showMessageDialog(null, "Unable to add item!",
                            "Error adding item", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
