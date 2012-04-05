package lars;

/**
 * A system account. All accounts can rent items; some accounts are managers.
 * 
 * @author Jeremy Wheaton, 100105823
 * @author Samuel Coleman, 100105709
 * @version 2012-04-04
 */
public class Account
{
    /**
     * The ID of the first account created by human interaction.
     */
    public static final int LOW_ACCOUNT_ID = 1001;
    /**
     * The expected maximum length of account IDs; to be used by text fields.
     */
    public static final int ACCOUNT_ID_LENGTH = 8;

    private int accountId;
    private String name;
    private String address;
    private boolean manager;

    /**
     * Instantiate an account.
     * 
     * @param accountId
     *            the account ID
     * @param name
     *            the name of the account holder
     * @param address
     *            the address of the account holder
     * @param manager
     *            whether or not the account has managerial rights
     */
    public Account(int accountId, String name, String address, boolean manager)
    {
        this.accountId = accountId;
        this.name = name;
        this.address = address;
        this.manager = manager;
    }

    /**
     * Get the account ID.
     * 
     * @return the account ID
     */
    public int getAccountId()
    {
        return accountId;
    }

    /**
     * Set the account ID.
     * 
     * @param accountId
     *            the account ID
     */
    public void setAccountId(int accountId)
    {
        this.accountId = accountId;
    }

    /**
     * Get the name of the account holder.
     * 
     * @return the name of the account holder
     */
    public String getName()
    {
        return name;
    }

    /**
     * Set the name of the account holder.
     * 
     * @param name
     *            the name of the account holder
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Get the address of the account holder.
     * 
     * @return the address of the account holder
     */
    public String getAddress()
    {
        return address;
    }

    /**
     * Set the address of the account holder.
     * 
     * @param address
     *            the address of the account holder
     */
    public void setAddress(String address)
    {
        this.address = address;
    }

    /**
     * Get whether or not the account has managerial rights.
     * 
     * @return whether or not the account has managerial rights
     */
    public boolean isManager()
    {
        return manager;
    }

    /**
     * Set whether or not the account has managerial rights
     * 
     * @param manager
     *            whether or not the account has managerial rights
     */
    public void setManager(boolean manager)
    {
        this.manager = manager;
    }
}
