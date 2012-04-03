package lars.gui;

import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import lars.Item;
import lars.ItemType;

/**
 * Table model for @{Item}s.
 * 
 * @author Samuel Coleman, 100105709
 * @version 2012-04-03
 */
public class ItemModel implements TableModel
{
    private static final int COLUMNS = 5;
    private static final int TYPE_COLUMN = 1;
    private static final int SKU_COLUMN = 0;
    private static final int DESCRIPTION_COLUMN = 2;
    private static final int QUANTITY_COLUMN = 3;
    private static final int MODIFIERS_COLUMN = 4;

    private List<Item> items;
    private boolean editable = false;

    public ItemModel(List<Item> items, boolean editable)
    {
        this.items = items;
        this.editable = editable;
    }

    public ItemModel(List<Item> items)
    {
        this.items = items;
    }

    @Override
    public int getRowCount()
    {
        return items.size();
    }

    @Override
    public int getColumnCount()
    {
        return COLUMNS;
    }

    @Override
    public String getColumnName(int columnIndex)
    {
        switch (columnIndex)
        {
        case TYPE_COLUMN:
            return "Type";
        case SKU_COLUMN:
            return "SKU";
        case DESCRIPTION_COLUMN:
            return "Description";
        case QUANTITY_COLUMN:
            return "Quantity";
        case MODIFIERS_COLUMN:
            return "Modifiers";
        default:
            return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        switch (columnIndex)
        {
        case TYPE_COLUMN:
            return ItemType.class;
        case SKU_COLUMN:
            return Integer.class;
        case DESCRIPTION_COLUMN:
            return String.class;
        case QUANTITY_COLUMN:
            return Integer.class;
        case MODIFIERS_COLUMN:
            return Integer.class;
        default:
            return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        switch (columnIndex)
        {
        case 1:
        case 2:
        case 3:
        case 4:
            if (editable)
                return true;
        }

        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        switch (columnIndex)
        {
        case TYPE_COLUMN:
            return items.get(rowIndex).getType();
        case SKU_COLUMN:
            return items.get(rowIndex).getSku();
        case DESCRIPTION_COLUMN:
            return items.get(rowIndex).getDescription();
        case QUANTITY_COLUMN:
            return items.get(rowIndex).getQuantity();
        case MODIFIERS_COLUMN:
            return items.get(rowIndex).getModifiers().size();
        default:
            return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        switch (columnIndex)
        {
        case TYPE_COLUMN:
            items.get(rowIndex).setType((ItemType) aValue);
        case SKU_COLUMN:
            items.get(rowIndex).setSku((Integer) aValue);
        case DESCRIPTION_COLUMN:
            items.get(rowIndex).setDescription((String) aValue);
        case QUANTITY_COLUMN:
            items.get(rowIndex).setQuantity((Integer) aValue);
        default:
            return;
        }
    }

    @Override
    public void addTableModelListener(TableModelListener l)
    {
    }

    @Override
    public void removeTableModelListener(TableModelListener l)
    {
    }
}
