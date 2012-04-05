package lars;

/**
 * Calculates tax for a given percentage
 * 
 * @author Jeremy Wheaton, 100105823
 * @version 2012-04-04
 */
public class TaxCalculator
{
    public static final int TAX_PERCENTAGE = 1500;

    public static int getTax(int price)
    {
        return (int) ((double) (price * TAX_PERCENTAGE) / 10000);
    }

    public static int getTotalWithTax(int price)
    {
        return getTax(price) + price;
    }
}
