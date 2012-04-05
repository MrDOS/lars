package lars.gui.admin;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import lars.ItemType;
import lars.db.ItemDatabase;

/**
 * Item type updating interface.
 * 
 * @author Jeremy Wheaton, 100105823
 * @version 2012-04-04
 */
public class UpdateItemTypeDialog extends JDialog implements ActionListener
{
    private static final long serialVersionUID = 1L;

    private ItemsFrame parent;

    private ItemType itemType;

    private JTextField nameField;
    private JTextField purchasePriceField;
    private JTextField rentalPriceField;
    private JTextField rentalDurationField;
    private JTextArea descriptionField;
    private JCheckBox rentable;

    private JButton save;
    private JButton cancel;

    public UpdateItemTypeDialog(ItemsFrame parent, ItemType itemType)
    {
        super(AdminFrame.getInstance(), "Update Item Type");
        this.setLocationByPlatform(true);

        this.parent = parent;
        this.itemType = itemType;

        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0;
        c.gridy = 0;
        this.add(new JLabel("Name:"), c);

        nameField = new JTextField(itemType.getName(), ItemType.NAME_SIZE);
        c.gridx = 1;
        c.gridy = 0;
        this.add(nameField, c);

        c.gridx = 0;
        c.gridy = 1;
        this.add(new JLabel("Description:"), c);

        descriptionField = new JTextArea(itemType.getDescription(), 3, 20);
        c.gridx = 1;
        c.gridy = 1;
        this.add(new JScrollPane(descriptionField), c);

        c.gridx = 0;
        c.gridy = 2;
        this.add(new JLabel("Purchase price:"), c);

        purchasePriceField = new JTextField(String.valueOf(itemType
                .getPurchasePrice()), ItemType.NAME_SIZE);
        c.gridx = 1;
        c.gridy = 2;
        this.add(purchasePriceField, c);

        c.gridx = 0;
        c.gridy = 3;
        this.add(new JLabel("Is rentable:"), c);

        rentable = new JCheckBox();
        if (itemType.isRentable())
            rentable.setSelected(true);
        c.gridx = 1;
        c.gridy = 3;
        this.add(rentable, c);

        c.gridx = 0;
        c.gridy = 4;
        this.add(new JLabel("Rental price:"), c);

        rentalPriceField = new JTextField(String.valueOf(itemType
                .getRentalPrice()), ItemType.NAME_SIZE);
        c.gridx = 1;
        c.gridy = 4;
        this.add(rentalPriceField, c);

        c.gridx = 0;
        c.gridy = 5;
        this.add(new JLabel("Rental duration:"), c);

        rentalDurationField = new JTextField(String.valueOf(itemType
                .getRentalDuration()), ItemType.NAME_SIZE);
        c.gridx = 1;
        c.gridy = 5;
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
            String name = "";
            String description = "";
            int purchasePrice = 0;
            int rentalPrice = 0;
            int rentalDuration = 0;
            boolean isRentable = rentable.isSelected();

            try
            {
                name = nameField.getText();
                description = descriptionField.getText();
                purchasePrice = Integer.valueOf(purchasePriceField.getText());
                rentalPrice = Integer.valueOf(rentalPriceField.getText());
                rentalDuration = Integer.valueOf(rentalDurationField.getText());
            }
            catch (NumberFormatException ex)
            {
            }

            if (name.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Invalid name!",
                        "Error updating item type", JOptionPane.ERROR_MESSAGE);
            }
            else if (description.equals(""))
            {
                JOptionPane
                        .showMessageDialog(
                                null,
                                "Invalid description! The description may not be blank.",
                                "Error updating item type",
                                JOptionPane.ERROR_MESSAGE);
            }
            else if (purchasePrice < 0)
            {
                JOptionPane.showMessageDialog(null, "Invalid purchase price!",
                        "Error updating item type", JOptionPane.ERROR_MESSAGE);
            }
            else if (isRentable && rentalPrice <= 0)
            {
                JOptionPane.showMessageDialog(null, "Invalid rental price!",
                        "Error updating item type", JOptionPane.ERROR_MESSAGE);
            }
            else if (isRentable && rentalDuration <= 0)
            {
                JOptionPane.showMessageDialog(null, "Invalid rental duration!",
                        "Error updating item type", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                try
                {
                    ItemDatabase.updateItemType(new ItemType(itemType
                            .getTypeId(), name, description, purchasePrice,
                            isRentable, rentalPrice, rentalDuration));
                    parent.refresh();
                    this.dispose();
                }
                catch (SQLException ex)
                {
                    JOptionPane.showMessageDialog(null,
                            "Unable to update item type!",
                            "Error updating item type",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
