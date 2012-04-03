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
import lars.gui.ItemModel;

/**
 * Frame for items and associated data management.
 * 
 * @author Samuel Coleman, 100105709
 * @version 2012-04-03
 */
public class ItemsFrame extends AdminInternalFrame
{
    private static final long serialVersionUID = 1L;

    private JTable itemsTable;
    private JButton addItem;
    private JButton deleteItem;

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
        itemsTable = new JTable(model);
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        tab.add(new JScrollPane(itemsTable), c);

        JPanel buttonPanel = new JPanel(new GridBagLayout());

        c.fill = GridBagConstraints.NONE;
        c.weightx = 0;
        c.weighty = 0;

        addItem = new JButton("Add Item");
        c.gridx = 0;
        c.gridy = 0;
        buttonPanel.add(addItem, c);

        addItem = new JButton("Delete Item");
        c.gridx = 1;
        c.gridy = 0;
        buttonPanel.add(addItem, c);

        c.gridx = 0;
        c.gridy = 1;
        tab.add(buttonPanel, c);

        return tab;
    }

    private JPanel getItemTypesTab()
    {
        JPanel tab = new JPanel(new GridBagLayout());

        return tab;
    }

    private JPanel getItemModifiersTab()
    {
        JPanel tab = new JPanel(new GridBagLayout());

        return tab;
    }
}
