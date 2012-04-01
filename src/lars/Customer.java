package lars;

/**
 * Data class describing customer.
 * 
 * @author Jeremy Wheaton
 * @version April 1, 2012
 */
public class Customer
{
    private String name;
    private String address;
    private Account account;
    
    public Customer(String newName, String newAddress)
    {
        name = newName;
        address = newAddress;
        setAccount(null);   //Needs to be linked to an account
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

    public void setAccount(Account account)
    {
        this.account = account;
    }

    public Account getAccount()
    {
        return account;
    }
}
