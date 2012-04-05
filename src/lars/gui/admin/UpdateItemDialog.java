package lars.gui.admin;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import lars.Item;
import lars.ItemModifier;
import lars.ItemType;
import lars.db.ItemDatabase;
import lars.gui.ItemModifierModel;

/**
 * Item modification interface.
 * 
 * @author Samuel Coleman, 100105709
 * @version 2012-04-04
 */
public class UpdateItemDialog extends JDialog implements ActionListener
{
    private static final long serialVersionUID = 1L;

    private AdminInternalFrame parent;

    private Item item;

    private JTextField skuField;
    private JTextField quantityField;
    private JTextArea descriptionField;
    private JComboBox type;

    private JTable modifiers;
    private JComboBox modifier;
    private JButton addModifier;
    private JButton removeModifier;

    private JButton save;
    private JButton cancel;

    /**
     * Instantiate the dialog.
     * 
     * @param parent
     *            the parent frame
     * @param item
     *            the account to be updated
     */
    public UpdateItemDialog(AdminInternalFrame parent, Item item)
    {
        super(AdminFrame.getInstance(), "Update Item");
        this.setLocationByPlatform(true);

        this.parent = parent;
        this.item = item;

        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0;
        c.gridy = 0;
        this.add(new JLabel("SKU:"), c);

        skuField = new JTextField(String.valueOf(this.item.getSku()),
                Item.SKU_LENGTH);
        c.gridx = 1;
        c.gridy = 0;
        this.add(skuField, c);

        c.gridx = 0;
        c.gridy = 1;
        this.add(new JLabel("Quantity:"), c);

        quantityField = new JTextField(String.valueOf(this.item.getQuantity()),
                Item.QUANTITY_LENTH);
        c.gridx = 1;
        c.gridy = 1;
        this.add(quantityField, c);

        c.gridx = 0;
        c.gridy = 2;
        this.add(new JLabel("Description:"), c);

        descriptionField = new JTextArea(this.item.getDescription(), 3, 20);
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
        type.setSelectedItem(this.item.getType());
        c.gridx = 1;
        c.gridy = 3;
        this.add(type, c);

        c.gridx = 0;
        c.gridy = 4;
        this.add(new JLabel("Modifiers:"), c);

        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2;
        this.add(getModifierPanel(), c);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        this.save = new JButton("Save");
        buttonPanel.add(this.save);

        this.cancel = new JButton("Cancel");
        buttonPanel.add(this.cancel);

        c.gridx = 0;
        c.gridy = 6;
        c.anchor = GridBagConstraints.EAST;
        this.add(buttonPanel, c);

        this.pack();

        this.addModifier.addActionListener(this);
        this.removeModifier.addActionListener(this);
        this.cancel.addActionListener(this);
        this.save.addActionListener(this);
    }

    private JPanel getModifierPanel()
    {
        JPanel modifierPanel = new JPanel();
        modifierPanel.setLayout(new BoxLayout(modifierPanel,
                BoxLayout.PAGE_AXIS));

        this.modifiers = new JTable(new ItemModifierModel(
                this.item.getModifiers()));
        JScrollPane scroll = new JScrollPane(this.modifiers);
        scroll.setPreferredSize(new Dimension(400, 100));
        modifierPanel.add(scroll);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        Object types[] = {};
        try
        {
            types = ItemDatabase.getItemModifiers().toArray();
        }
        catch (SQLException e)
        {
        }

        modifier = new JComboBox(types);
        buttonPanel.add(modifier);

        this.addModifier = new JButton("Add Modifier");
        buttonPanel.add(this.addModifier);

        this.removeModifier = new JButton("Remove Modifier");
        buttonPanel.add(this.removeModifier);

        modifierPanel.add(buttonPanel);

        return modifierPanel;
    }

    private void refresh()
    {
        this.modifiers
                .setModel(new ItemModifierModel(this.item.getModifiers()));
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(this.cancel))
            this.dispose();
        else if (e.getSource().equals(this.addModifier))
        {
            this.item.addModifier((ItemModifier) this.modifier
                    .getSelectedItem());
            this.refresh();
        }
        else if (e.getSource().equals(this.removeModifier))
        {
            this.item.removeModifier((ItemModifier) this.modifier
                    .getSelectedItem());
            this.refresh();
        }
        else if (e.getSource().equals(this.save))
        {
            int oldSku = this.item.getSku();
            this.item.setType((ItemType) this.type.getSelectedItem());

            try
            {
                this.item.setSku(Integer.valueOf(skuField.getText()));
                this.item.setDescription(descriptionField.getText());
                this.item.setQuantity(Integer.valueOf(quantityField.getText()));
            }
            catch (NumberFormatException ex)
            {
            }

            if (this.item.getSku() == 0)
            {
                JOptionPane.showMessageDialog(null, "Invalid SKU!",
                        "Error updating item", JOptionPane.ERROR_MESSAGE);
            }
            else if (this.item.getDescription().equals(""))
            {
                JOptionPane
                        .showMessageDialog(
                                null,
                                "Invalid description! The description may not be blank.",
                                "Error updating item",
                                JOptionPane.ERROR_MESSAGE);
            }
            else if (this.item.getType() == null)
            {
                JOptionPane
                        .showMessageDialog(
                                null,
                                "Invalid item type! If no types are available, you must define one.",
                                "Error updating item",
                                JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                try
                {
                    ItemDatabase.updateItem(this.item, oldSku);

                    parent.refresh();
                    this.dispose();
                }
                catch (SQLException ex)
                {
                    JOptionPane.showMessageDialog(null,
                            "Unable to update item!", "Error updating item",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
