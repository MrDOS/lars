package lars.gui.admin;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lars.ItemModifier;
import lars.ItemType;
import lars.db.ItemDatabase;

/**
 * Item type updating interface.
 * 
 * @author Jeremy Wheaton, 100105823
 * @version 2012-04-04
 */
public class UpdateItemModifierDialog extends JDialog implements ActionListener
{
    private static final long serialVersionUID = 1L;

    private ItemsFrame parent;

    private ItemModifier itemModifier;

    private JTextField nameField;
    private JTextField purchasePriceField;
    private JTextField rentalPriceField;
    private JTextField rentalDurationField;

    private JButton save;
    private JButton cancel;

    public UpdateItemModifierDialog(ItemsFrame parent, ItemModifier itemModifier)
    {
        super(AdminFrame.getInstance(), "Update Item Type");
        this.setLocationByPlatform(true);

        this.parent = parent;
        this.itemModifier = itemModifier;

        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0;
        c.gridy = 0;
        this.add(new JLabel("Name:"), c);

        nameField = new JTextField(itemModifier.getName(), ItemType.NAME_SIZE);
        c.gridx = 1;
        c.gridy = 0;
        this.add(nameField, c);

        c.gridx = 0;
        c.gridy = 1;
        this.add(new JLabel("Purchase price:"), c);

        purchasePriceField = new JTextField(String.valueOf(itemModifier
                .getPurchasePrice()), ItemType.NAME_SIZE);
        c.gridx = 1;
        c.gridy = 1;
        this.add(purchasePriceField, c);

        c.gridx = 0;
        c.gridy = 2;
        this.add(new JLabel("Rental price:"), c);

        rentalPriceField = new JTextField(String.valueOf(itemModifier
                .getRentalPrice()), ItemType.NAME_SIZE);
        c.gridx = 1;
        c.gridy = 2;
        this.add(rentalPriceField, c);

        c.gridx = 0;
        c.gridy = 3;
        this.add(new JLabel("Rental duration:"), c);

        rentalDurationField = new JTextField(String.valueOf(itemModifier
                .getRentalDuration()), ItemType.NAME_SIZE);
        c.gridx = 1;
        c.gridy = 3;
        this.add(rentalDurationField, c);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        save = new JButton("Save");
        buttonPanel.add(save);

        cancel = new JButton("Cancel");
        buttonPanel.add(cancel);

        c.gridx = 0;
        c.gridy = 6;
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
            String name = nameField.getText();
            int purchasePrice = 0;
            int rentalPrice = 0;
            int rentalDuration = 0;

            try
            {
                purchasePrice = Integer.valueOf(purchasePriceField.getText());
            }
            catch (NumberFormatException ex)
            {
            }
            try
            {
                rentalPrice = Integer.valueOf(rentalPriceField.getText());
            }
            catch (NumberFormatException ex)
            {
            }
            try
            {
                rentalDuration = Integer.valueOf(rentalDurationField.getText());
            }
            catch (NumberFormatException ex)
            {
            }

            if (name.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Invalid name!",
                        "Error updating item modifier",
                        JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                try
                {
                    ItemModifier newMod = new ItemModifier(
                            itemModifier.getModifierId(), name, purchasePrice,
                            rentalPrice, rentalDuration);
                    ItemDatabase.updateItemModifier(newMod);

                    parent.refresh();
                    this.dispose();
                }
                catch (SQLException ex)
                {
                    JOptionPane.showMessageDialog(null,
                            "Unable to update item modifier!",
                            "Error updating item modifier",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
