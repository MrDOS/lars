package lars;

/**
 * Data class describing an account.
 * 
 * @author Jeremy Wheaton, 100105823
 * @author Samuel Coleman, 100105709
 * @version 2012-04-04
 */
public class Account
{
    public static final int ACCOUNT_ID_LENGTH = 8;

    private int accountId;
    private String name;
    private String address;
    private boolean manager;

    public Account(int accountId, String name, String address, boolean manager)
    {
        this.accountId = accountId;
        this.name = name;
        this.address = address;
        this.manager = manager;
    }

    public int getAccountId()
    {
        return accountId;
    }

    public void setAccountId(int accountId)
    {
        this.accountId = accountId;
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
