package lars.gui;

import java.text.DecimalFormat;

import javax.swing.table.AbstractTableModel;

import lars.Rental;

/**
 * Table model for {@link Rental}s.
 * 
 * @author Jeremy Wheaton, 100105823
 * @version 2012-04-03
 */
public class RentalModel extends AbstractTableModel
{
    private static final long serialVersionUID = 1L;

    private static final int COLUMN_COUNT = 3;
    private static final int DESCRIPTION_COLUMN = 0;
    private static final int RENTED_COLUMN = 1;
    private static final int PRICE_COLUMN = 2;

    private Rental rental;
    private boolean editable;

    /**
     * Create the model.
     * 
     * @param rental
     *            the rental data to model
     * @param editable
     *            whether or not the cells should be editable
     */
    public RentalModel(Rental rental, boolean editable)
    {
        this.rental = rental;
        this.editable = editable;
    }

    /**
     * Create the model.
     * 
     * @param rental
     *            the rental data to model
     */
    public RentalModel(Rental rental)
    {
        this.rental = rental;
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
        case PRICE_COLUMN:
            return "Price";
        default:
            return null;
        }
    }

    @Override
    public int getRowCount()
    {
        return rental.getRentalItems().size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        switch (columnIndex)
        {
        case DESCRIPTION_COLUMN:
            return rental.getRentalItems().get(rowIndex).getItem()
                    .getDescription();
        case RENTED_COLUMN:
            return rental.getRentalItems().get(rowIndex).isRented();
        case PRICE_COLUMN:
            return new DecimalFormat("$#0.00").format((double) rental
                    .getRentalItems().get(rowIndex).getPrice() / 100);
        default:
            return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        if (editable && columnIndex == RENTED_COLUMN
                && rental.getRentalItems().get(rowIndex).isRentable())
            return true;
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        if (editable && columnIndex == RENTED_COLUMN)
        {
            this.rental.getRentalItems().get(rowIndex)
                    .setRented((Boolean) aValue);
            this.fireTableCellUpdated(rowIndex, PRICE_COLUMN);
        }
    }
}
