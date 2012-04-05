package lars.gui;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import lars.RentalItem;

/**
 * Table model for {@link RentalItem}s.
 * 
 * @author Jeremy Wheaton, 100105823
 * @version 2012-04-03
 */
public class RentalItemModel extends AbstractTableModel
{
    private static final long serialVersionUID = 1L;

    private static final int COLUMN_COUNT = 4;
    private static final int DESCRIPTION_COLUMN = 0;
    private static final int RENTED_COLUMN = 1;
    private static final int DUE_COLUMN = 2;
    private static final int PRICE_COLUMN = 3;

    private List<RentalItem> rentalItems;
    private boolean editable;

    /**
     * Create the model.
     * 
     * @param rental
     *            the rental data to model
     * @param editable
     *            whether or not the cells should be editable
     */
    public RentalItemModel(List<RentalItem> rentalItems, boolean editable)
    {
        this.rentalItems = rentalItems;
        this.editable = editable;
    }

    /**
     * Create the model.
     * 
     * @param rental
     *            the rental data to model
     */
    public RentalItemModel(List<RentalItem> rentalItems)
    {
        this.rentalItems = rentalItems;
        this.editable = false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        switch (columnIndex)
        {
        case DESCRIPTION_COLUMN:
            return String.class;
        case RENTED_COLUMN:
            return Boolean.class;
        case DUE_COLUMN:
            return Date.class;
        case PRICE_COLUMN:
            return String.class;
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
        case DESCRIPTION_COLUMN:
            return "Description";
        case RENTED_COLUMN:
            return "Rented";
        case DUE_COLUMN:
            return "Due Date";
        case PRICE_COLUMN:
            return "Price";
        default:
            return null;
        }
    }

    @Override
    public int getRowCount()
    {
        return rentalItems.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        switch (columnIndex)
        {
        case DESCRIPTION_COLUMN:
            return this.rentalItems.get(rowIndex).getItem().getDescription();
        case RENTED_COLUMN:
            return this.rentalItems.get(rowIndex).isRented();
        case DUE_COLUMN:
            return this.rentalItems.get(rowIndex).getDueDate();
        case PRICE_COLUMN:
            return new DecimalFormat("$#0.00").format((double) this.rentalItems
                    .get(rowIndex).getPrice() / 100);
        default:
            return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        if (this.editable && columnIndex == RENTED_COLUMN
                && this.rentalItems.get(rowIndex).isRentable())
            return true;
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        if (this.editable && columnIndex == RENTED_COLUMN)
        {
            this.rentalItems.get(rowIndex).setRented((Boolean) aValue);
            this.fireTableCellUpdated(rowIndex, PRICE_COLUMN);
        }
    }
}
