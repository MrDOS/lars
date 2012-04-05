package lars.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import lars.Account;

/**
 * Table model for {@link Account}s.
 * 
 * @author Samuel Coleman, 100105709
 * @version 2012-04-03
 */
public class AccountModel extends AbstractTableModel
{
    private static final long serialVersionUID = 1L;

    private static final int COLUMN_COUNT = 4;
    private static final int ACCOUNT_ID_COLUMN = 0;
    private static final int NAME_COLUMN = 1;
    private static final int ADDRESS_COLUMN = 2;
    private static final int MANAGER_COLUMN = 3;

    private List<Account> accounts;

    /**
     * Create the model.
     * 
     * @param accounts
     *            account data to model
     */
    public AccountModel(List<Account> accounts)
    {
        this.accounts = accounts;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        switch (columnIndex)
        {
        case ACCOUNT_ID_COLUMN:
            return Integer.class;
        case NAME_COLUMN:
            return String.class;
        case ADDRESS_COLUMN:
            return String.class;
        case MANAGER_COLUMN:
            return Boolean.class;
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
        case ACCOUNT_ID_COLUMN:
            return "Account ID";
        case NAME_COLUMN:
            return "Name";
        case ADDRESS_COLUMN:
            return "Address";
        case MANAGER_COLUMN:
            return "Manager";
        default:
            return null;
        }
    }

    @Override
    public int getRowCount()
    {
        return accounts.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        switch (columnIndex)
        {
        case ACCOUNT_ID_COLUMN:
            return accounts.get(rowIndex).getAccountId();
        case NAME_COLUMN:
            return accounts.get(rowIndex).getName();
        case ADDRESS_COLUMN:
            return accounts.get(rowIndex).getAddress();
        case MANAGER_COLUMN:
            return accounts.get(rowIndex).isManager();
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
        case ACCOUNT_ID_COLUMN:
            accounts.get(rowIndex).setAccountId((Integer) aValue);
        case NAME_COLUMN:
            accounts.get(rowIndex).setName((String) aValue);
        case ADDRESS_COLUMN:
            accounts.get(rowIndex).setAddress((String) aValue);
        case MANAGER_COLUMN:
            accounts.get(rowIndex).setManager((Boolean) aValue);
        }
    }
}
