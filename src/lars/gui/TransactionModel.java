package lars.gui;

import java.text.DecimalFormat;

import javax.swing.table.AbstractTableModel;

import lars.Transaction;

/**
 * Table model for {@link Transaction}s.
 * 
 * @author Jeremy Wheaton, 100105823
 * @version 2012-04-03
 */
public class TransactionModel extends AbstractTableModel
{
    private static final long serialVersionUID = 1L;

    private static final int COLUMN_COUNT = 3;
    private static final int DESCRIPTION_COLUMN = 0;
    private static final int RENTED_COLUMN = 1;
    private static final int PRICE_COLUMN = 2;

    private Transaction transaction;

    public TransactionModel(Transaction transaction)
    {
        this.transaction = transaction;
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
        return transaction.getTransactionItems().size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        switch (columnIndex)
        {
        case DESCRIPTION_COLUMN:
            return transaction.getTransactionItems().get(rowIndex).getItem()
                    .getDescription();
        case RENTED_COLUMN:
            return transaction.getTransactionItems().get(rowIndex).isRented();
        case PRICE_COLUMN:
            return new DecimalFormat("$#.00").format(transaction.getTransactionItems().get(rowIndex).getPrice() / 100);
        default:
            return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        if (columnIndex == RENTED_COLUMN
                && transaction.getTransactionItems().get(rowIndex).isRentable())
            return true;
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        if (columnIndex == RENTED_COLUMN)
            transaction.getTransactionItems().get(rowIndex)
                    .setRented((Boolean) aValue);
    }
}
