package lars.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import lars.ItemModifier;

/**
 * Table model for {@link ItemModifier}s.
 * 
 * @author Samuel Coleman, 100105709
 * @version 2012-04-03
 */
public class ItemModifierModel extends AbstractTableModel
{
    private static final long serialVersionUID = 1L;

    private static final int COLUMN_COUNT = 4;
    private static final int NAME_COLUMN = 0;
    private static final int PURCHASE_PRICE_COLUMN = 1;
    private static final int RENTAL_PRICE_COLUMN = 2;
    private static final int RENTAL_DURATION_COLUMN = 3;

    private List<ItemModifier> modifiers;

    /**
     * Create the model.
     * 
     * @param modifiers
     *            the item modifier data to model
     */
    public ItemModifierModel(List<ItemModifier> modifiers)
    {
        this.modifiers = modifiers;
    }

    /**
     * Create the model.
     */
    public ItemModifierModel()
    {
        this.modifiers = new ArrayList<ItemModifier>();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        switch (columnIndex)
        {
        case NAME_COLUMN:
            return String.class;
        case PURCHASE_PRICE_COLUMN:
            return Integer.class;
        case RENTAL_PRICE_COLUMN:
            return Integer.class;
        case RENTAL_DURATION_COLUMN:
            return Integer.class;
        default:
            return null;
        }
    }

    @Override
    public int getColumnCount()
    {
        return COLUMN_COUNT;
    }

    @Override
    public String getColumnName(int columnIndex)
    {
        switch (columnIndex)
        {
        case NAME_COLUMN:
            return "Name";
        case PURCHASE_PRICE_COLUMN:
            return "Purchase Price";
        case RENTAL_PRICE_COLUMN:
            return "Rental Price";
        case RENTAL_DURATION_COLUMN:
            return "Rental Duration";
        default:
            return null;
        }
    }

    @Override
    public int getRowCount()
    {
        return modifiers.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        switch (columnIndex)
        {
        case NAME_COLUMN:
            return modifiers.get(rowIndex).getName();
        case PURCHASE_PRICE_COLUMN:
            return modifiers.get(rowIndex).getPurchasePrice();
        case RENTAL_PRICE_COLUMN:
            return modifiers.get(rowIndex).getRentalPrice();
        case RENTAL_DURATION_COLUMN:
            return modifiers.get(rowIndex).getRentalDuration();
        default:
            return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        switch (columnIndex)
        {
        case NAME_COLUMN:
            modifiers.get(rowIndex).setName((String) aValue);
        case PURCHASE_PRICE_COLUMN:
            modifiers.get(rowIndex).setPurchasePrice((Integer) aValue);
        case RENTAL_PRICE_COLUMN:
            modifiers.get(rowIndex).setRentalPrice((Integer) aValue);
        case RENTAL_DURATION_COLUMN:
            modifiers.get(rowIndex).setRentalDuration((Integer) aValue);
        }
    }
}
