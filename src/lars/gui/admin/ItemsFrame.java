package lars.gui.admin;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import lars.Item;
import lars.ItemModifier;
import lars.ItemType;
import lars.db.ItemDatabase;
import lars.gui.ItemModel;
import lars.gui.ItemModifierModel;
import lars.gui.ItemTypeModel;

/**
 * Frame for items and associated data management.
 * 
 * @author Samuel Coleman, 100105709
 * @version 2012-04-04
 */
public class ItemsFrame extends AdminInternalFrame implements ActionListener
{
    private static final long serialVersionUID = 1L;

    private List<Item> items;
    private JTable itemTable;
    private JButton addItem;
    private JButton updateItem;
    private JButton deleteItem;

    private List<ItemModifier> modifiers;
    private JTable itemModifierTable;
    private JButton addItemModifier;
    private JButton updateItemModifier;
    private JButton deleteItemModifier;

    private List<ItemType> types;
    private JTable itemTypeTable;
    private JButton addItemType;
    private JButton updateItemType;
    private JButton deleteItemType;

    public ItemsFrame()
    {
        super("Items");

        this.items = new ArrayList<Item>();
        this.modifiers = new ArrayList<ItemModifier>();
        this.types = new ArrayList<ItemType>();

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Items", getItemsPanel());
        tabs.addTab("Item Types", getItemTypesPanel());
        tabs.addTab("Item Modifiers", getItemModifiersPanel());
        this.add(tabs);

        this.refresh();

        this.addItem.addActionListener(this);
        this.updateItem.addActionListener(this);
        this.deleteItem.addActionListener(this);
        this.addItemModifier.addActionListener(this);
        this.updateItemModifier.addActionListener(this);
        this.deleteItemModifier.addActionListener(this);
        this.addItemType.addActionListener(this);
        this.updateItemType.addActionListener(this);
        this.deleteItemType.addActionListener(this);
    }

    private JPanel getItemsPanel()
    {
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        TableModel model = new ItemModel(this.items);
        itemTable = new JTable(model);
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        panel.add(new JScrollPane(itemTable), c);

        JPanel buttonPanel = new JPanel(new GridBagLayout());

        c.fill = GridBagConstraints.NONE;
        c.weightx = 0;
        c.weighty = 0;

        addItem = new JButton("Add Item");
        c.gridx = 0;
        c.gridy = 0;
        buttonPanel.add(addItem, c);

        updateItem = new JButton("Update Item");
        c.gridx = 1;
        c.gridy = 0;
        buttonPanel.add(updateItem, c);

        deleteItem = new JButton("Delete Item");
        c.gridx = 2;
        c.gridy = 0;
        buttonPanel.add(deleteItem, c);

        c.gridx = 0;
        c.gridy = 1;
        panel.add(buttonPanel, c);

        return panel;
    }

    private JPanel getItemModifiersPanel()
    {
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        TableModel model = new ItemModifierModel(this.modifiers);
        itemModifierTable = new JTable(model);
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        panel.add(new JScrollPane(itemModifierTable), c);

        JPanel buttonPanel = new JPanel(new GridBagLayout());

        c.fill = GridBagConstraints.NONE;
        c.weightx = 0;
        c.weighty = 0;

        addItemModifier = new JButton("Add Item Modifier");
        c.gridx = 0;
        c.gridy = 0;
        buttonPanel.add(addItemModifier, c);

        updateItemModifier = new JButton("Update Item Modifier");
        c.gridx = 1;
        c.gridy = 0;
        buttonPanel.add(updateItemModifier, c);

        deleteItemModifier = new JButton("Delete Item Modifier");
        c.gridx = 2;
        c.gridy = 0;
        buttonPanel.add(deleteItemModifier, c);

        c.gridx = 0;
        c.gridy = 1;
        panel.add(buttonPanel, c);

        return panel;
    }

    private JPanel getItemTypesPanel()
    {
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        TableModel model = new ItemTypeModel(this.types);
        itemTypeTable = new JTable(model);
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        panel.add(new JScrollPane(itemTypeTable), c);

        JPanel buttonPanel = new JPanel(new GridBagLayout());

        c.fill = GridBagConstraints.NONE;
        c.weightx = 0;
        c.weighty = 0;

        addItemType = new JButton("Add Item Type");
        c.gridx = 0;
        c.gridy = 0;
        buttonPanel.add(addItemType, c);

        updateItemType = new JButton("Update Item Type");
        c.gridx = 1;
        c.gridy = 0;
        buttonPanel.add(updateItemType, c);

        deleteItemType = new JButton("Delete Item Type");
        c.gridx = 2;
        c.gridy = 0;
        buttonPanel.add(deleteItemType, c);

        c.gridx = 0;
        c.gridy = 1;
        panel.add(buttonPanel, c);

        return panel;
    }

    public void refresh()
    {
        try
        {
            this.items = ItemDatabase.getItems();
            this.modifiers = ItemDatabase.getItemModifiers();
            this.types = ItemDatabase.getItemTypes();
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error loading data!",
                    "Item error", JOptionPane.ERROR_MESSAGE);
            System.err.println(e.getMessage());
        }

        this.itemTable.setModel(new ItemModel(this.items));
        this.itemModifierTable.setModel(new ItemModifierModel(this.modifiers));
        this.itemTypeTable.setModel(new ItemTypeModel(this.types));
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(addItem))
        {
            new AddItemDialog(this).setVisible(true);
        }
        else if (e.getSource().equals(updateItem))
        {
            int row = this.itemTable.getSelectedRow();
            if (row >= 0)
                new UpdateItemDialog(this, this.items.get(row)).setVisible(true);
        }
        else if (e.getSource().equals(deleteItem))
        {
            int row = this.itemTable.getSelectedRow();
            if (row >= 0)
            {
                if (JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete this item?", "Item",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                {
                    try
                    {
                        ItemDatabase.deleteItem(this.items.get(row));
                    }
                    catch (SQLException ex)
                    {
                        JOptionPane.showMessageDialog(null,
                                "Error deleting item!", "Item error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        else if (e.getSource().equals(updateItemModifier))
        {
            int row = this.itemModifierTable.getSelectedRow();
            if (row >= 0)
                this.modifiers.get(row);
        }
        else if (e.getSource().equals(deleteItemModifier))
        {
            int row = this.itemModifierTable.getSelectedRow();
            if (row >= 0)
            {
                if (JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete this item modifier?",
                        "Item Modifier", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                {
                    try
                    {
                        ItemDatabase
                                .deleteItemModifier(this.modifiers.get(row));
                    }
                    catch (SQLException ex)
                    {
                        JOptionPane.showMessageDialog(null,
                                "Error deleting item modifier!", "Item error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        else if (e.getSource().equals(updateItemType))
        {
            int row = this.itemTypeTable.getSelectedRow();
            if (row >= 0)
                this.types.get(row);
        }
        else if (e.getSource().equals(deleteItemType))
        {
            int row = this.itemTypeTable.getSelectedRow();
            if (row >= 0)
            {
                if (JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete this item type?",
                        "Item Type", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                {
                    try
                    {
                        ItemDatabase.deleteItemType(this.types.get(row));
                    }
                    catch (SQLException ex)
                    {
                        JOptionPane.showMessageDialog(null,
                                "Error deleting item type!", "Item error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }

        this.refresh();
    }
}
