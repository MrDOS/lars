package lars.gui;

import java.sql.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import lars.Rental;

/**
 * Table model for {@link Rental}s.
 * 
 * @author Samuel Coleman, 100105823
 * @version 2012-04-05
 */
public class RentalModel extends AbstractTableModel
{
    private static final long serialVersionUID = 1L;

    private static final int COLUMN_COUNT = 2;
    private static final int DATE_COLUMN = 0;
    private static final int ITEMS_COLUMN = 1;

    private List<Rental> rentals;

    /**
     * Create the model.
     * 
     * @param rentals
     *            the rental data to model
     */
    public RentalModel(List<Rental> rentals)
    {
        this.rentals = rentals;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        switch (columnIndex)
        {
        case DATE_COLUMN:
            return Date.class;
        case ITEMS_COLUMN:
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
        case DATE_COLUMN:
            return "Rental Date";
        case ITEMS_COLUMN:
            return "Items";
        default:
            return null;
        }
    }

    @Override
    public int getRowCount()
    {
        return rentals.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        switch (columnIndex)
        {
        case DATE_COLUMN:
            return this.rentals.get(rowIndex).getDate();
        case ITEMS_COLUMN:
            return this.rentals.get(rowIndex).getRentalItems().size();
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
    }
}
