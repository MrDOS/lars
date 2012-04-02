package lars;

/**
 * Data class describing an account.
 * 
 * @author Jeremy Wheaton, 100105823
 * @version April 1, 2012
 */
public class Account
{
    private int accountID;
    private String name;
    private String address;
    private boolean manager;

    public Account(int accountID, String name, String address, boolean manager)
    {
        this.accountID = accountID;
        this.name = name;
        this.address = address;
        this.manager = manager;
    }

    public int getAccountID()
    {
        return accountID;
    }

    public void setAccountID(int accountID)
    {
        this.accountID = accountID;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public boolean isManager()
    {
        return manager;
    }

    public void setManager(boolean manager)
    {
        this.manager = manager;
    }
}
