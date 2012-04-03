package lars.gui;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import lars.Transaction;

public class TransactionTableModel implements TableModel
{
    private static final int NUMBER_OF_COLUMNS = 4;
    private Transaction transaction;

    public TransactionTableModel(Transaction trans)
    {
        transaction = trans;
    }

    @Override
    public Class<?> getColumnClass(int arg0)
    {
        return String.class;
    }

    @Override
    public int getColumnCount()
    {
        return NUMBER_OF_COLUMNS;
    }

    @Override
    public String getColumnName(int col)
    {
        switch (col)
        {
        case 0:
            return "Description";
        case 1:
            return "Quantity";
        case 2:
            return "Price";
        case 3:
            return "Total Price";
        default:
            return "Invalid Column";
        }
    }

    @Override
    public int getRowCount()
    {
        return transaction.getTransactionItems().size();
    }

    @Override
    public Object getValueAt(int arg0, int arg1)
    {
        if (arg0 >= getRowCount())
        {
            return "Invalid Cell (Y)";
        }

        switch (arg1)
        {
        case 0:
            return transaction.getTransactionItems().get(arg0).getItem()
                    .getDescription();
        case 1:
            return transaction.getTransactionItems().get(arg0).getQuantity();
        case 2:
            if (transaction.getTransactionItems().get(arg0).isRented())
                return transaction.getTransactionItems().get(arg0).getItem()
                        .getRentalPrice();
            else
                return transaction.getTransactionItems().get(arg0).getItem()
                        .getPurchasePrice();
        case 3:
            return transaction.getTransactionItems().get(arg0).getPrice();
        default:
            return "Invalid Cell (X)";
        }
    }

    @Override
    public boolean isCellEditable(int arg0, int arg1)
    {
        return false;
    }

    @Override
    public void setValueAt(Object arg0, int arg1, int arg2)
    {
        if (isCellEditable(arg1, arg2))
        {
            int newVal = Integer.valueOf((String) arg0);
            transaction.getTransactionItems().get(arg1).setQuantity(newVal);
        }
    }

    @Override
    public void addTableModelListener(TableModelListener arg0)
    {
    }

    @Override
    public void removeTableModelListener(TableModelListener arg0)
    {
    }
}
