package lars;

/**
 * Calculates tax at a predefined rate.
 * 
 * @author Jeremy Wheaton, 100105823
 * @version 2012-04-04
 */
public class TaxCalculator
{
    /**
     * The percentage at which tax is calculated * 100.
     */
    public static final int TAX_PERCENTAGE = 1500;

    /**
     * Get the tax on a price.
     * 
     * @param price
     *            the price
     * @return the tax
     */
    public static int getTax(int price)
    {
        return (int) ((double) (price * TAX_PERCENTAGE) / 10000);
    }

    /**
     * Get a total price with tax.
     * 
     * @param price
     *            the price
     * @return the total price with tax
     */
    public static int getTotalWithTax(int price)
    {
        return getTax(price) + price;
    }
}
