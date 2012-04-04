package lars.gui.admin;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import lars.Item;
import lars.ItemModifier;
import lars.ItemType;
import lars.gui.ItemModel;
import lars.gui.ItemModifierModel;
import lars.gui.ItemTypeModel;

/**
 * Frame for items and associated data management.
 * 
 * @author Samuel Coleman, 100105709
 * @version 2012-04-03
 */
public class ItemsFrame extends AdminInternalFrame
{
    private static final long serialVersionUID = 1L;

    private JTable itemTable;
    private JButton addItem;
    private JButton updateItem;
    private JButton deleteItem;

    private JTable itemModifierTable;
    private JButton addItemModifier;
    private JButton updateItemModifier;
    private JButton deleteItemModifier;

    private JTable itemTypeTable;
    private JButton addItemType;
    private JButton updateItemType;
    private JButton deleteItemType;

    public ItemsFrame()
    {
        super("Items");

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Items", getItemsTab());
        tabs.addTab("Item Types", getItemTypesTab());
        tabs.addTab("Item Modifiers", getItemModifiersTab());
        this.add(tabs);
    }

    private JPanel getItemsTab()
    {
        JPanel tab = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        TableModel model = new ItemModel(new ArrayList<Item>());
        itemTable = new JTable(model);
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        tab.add(new JScrollPane(itemTable), c);

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
        tab.add(buttonPanel, c);

        return tab;
    }

    private JPanel getItemModifiersTab()
    {
        JPanel tab = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        TableModel model = new ItemModifierModel(new ArrayList<ItemModifier>());
        itemModifierTable = new JTable(model);
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        tab.add(new JScrollPane(itemModifierTable), c);

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
        tab.add(buttonPanel, c);

        return tab;
    }

    private JPanel getItemTypesTab()
    {
        JPanel tab = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        TableModel model = new ItemTypeModel(new ArrayList<ItemType>());
        itemTypeTable = new JTable(model);
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        tab.add(new JScrollPane(itemTypeTable), c);

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
        tab.add(buttonPanel, c);

        return tab;
    }
}
