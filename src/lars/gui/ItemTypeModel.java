package lars.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import lars.ItemType;

/**
 * Table model for {@link ItemType}s.
 * 
 * @author Samuel Coleman, 100105709
 * @version 2012-04-03
 */
public class ItemTypeModel extends AbstractTableModel
{
    private static final long serialVersionUID = 1L;

    private static final int COLUMN_COUNT = 6;
    private static final int NAME_COLUMN = 0;
    private static final int DESCRIPTION_COLUMN = 1;
    private static final int PURCHASE_PRICE_COLUMN = 2;
    private static final int RENTABLE_COLUMN = 3;
    private static final int RENTAL_PRICE_COLUMN = 4;
    private static final int RENTAL_DURATION_COLUMN = 5;

    private List<ItemType> types;

    public ItemTypeModel(List<ItemType> types)
    {
        this.types = types;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        switch (columnIndex)
        {
        case NAME_COLUMN:
            return String.class;
        case DESCRIPTION_COLUMN:
            return String.class;
        case PURCHASE_PRICE_COLUMN:
            return Integer.class;
        case RENTABLE_COLUMN:
            return Boolean.class;
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
        case DESCRIPTION_COLUMN:
            return "Description";
        case PURCHASE_PRICE_COLUMN:
            return "Purchase Price";
        case RENTABLE_COLUMN:
            return "Rentable";
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
        return types.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        switch (columnIndex)
        {
        case NAME_COLUMN:
            return types.get(rowIndex).getName();
        case DESCRIPTION_COLUMN:
            return types.get(rowIndex).getDescription();
        case PURCHASE_PRICE_COLUMN:
            return types.get(rowIndex).getPurchasePrice();
        case RENTABLE_COLUMN:
            return types.get(rowIndex).isRentable();
        case RENTAL_PRICE_COLUMN:
            return types.get(rowIndex).getRentalPrice();
        case RENTAL_DURATION_COLUMN:
            return types.get(rowIndex).getRentalDuration();
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
            types.get(rowIndex).setName((String) aValue);
        case DESCRIPTION_COLUMN:
            types.get(rowIndex).setDescription((String) aValue);
        case PURCHASE_PRICE_COLUMN:
            types.get(rowIndex).setPurchasePrice((Integer) aValue);
        case RENTABLE_COLUMN:
            types.get(rowIndex).setRentable((Boolean) aValue);
        case RENTAL_PRICE_COLUMN:
            types.get(rowIndex).setRentalPrice((Integer) aValue);
        case RENTAL_DURATION_COLUMN:
            types.get(rowIndex).setRentalDuration((Integer) aValue);
        }
    }
}
