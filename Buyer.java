import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class representing a buyer.
 *
 * @author John Ryder and Jelal Kaufman
 * @version 1.0
 */

public class Buyer extends Person implements Serializable {
    private int lowerPrice;
    private int upperPrice;
    private boolean currentCustomer;
    private int id = 0;

    /**
     * Construct the buyer.
     * @param id the buyer's id.
     * @param name the buyer's name.
     * @param email the buyer's email.
     * @param phoneNumber the buyer's phone #.
     * @param lowerPrice the lower bound of a buyer's price range.
     * @param upperPrice the upper bound of a buyer's price range.
     * @param currentCustomer if the buyer is currently in the market or not.
     */
    public Buyer(int id, String name, String email, String phoneNumber,
                 int lowerPrice, int upperPrice,boolean currentCustomer)
    {
        super(id, name, email, phoneNumber);
        if (lowerPrice <= 0)
        {
            throw new RuntimeException("Illegal lower price bound. Lower bound for prices cannot be less than 0.\n");
        }
        if (upperPrice <= 0 || upperPrice < lowerPrice)
        {
            throw new RuntimeException
                    ("Illegal upper price bound. " +
                            "Upper bound for prices cannot be less than 0 or less than lower bound.\n");
        }
        this.lowerPrice = lowerPrice;
        this.upperPrice = upperPrice;
        this.currentCustomer = currentCustomer;
    }

    /**
     * Get the lower price bound of the buyer.
     * @return the lower price bound of the buyer.
     */
    public int getLowerPrice()
    {
        return this.lowerPrice;
    }

    /**
     * Set the lower price bound of the buyer.
     * @param lowerPrice the new lower price bound of the buyer.
     */
    public void setLowerPrice(int lowerPrice)
    {
        if (lowerPrice <= 0 || lowerPrice > this.upperPrice)
        {
            throw new RuntimeException("Illegal lower price bound. " +
                    "Lower bound for prices cannot be less than 0 or greater than the upper bound for prices\n");
        }
        this.lowerPrice = lowerPrice;
    }

    /**
     * Get the upper price bound of the buyer.
     * @return the upper price bound of the buyer.
     */
    public int getUpperPrice()
    {
        return this.upperPrice;
    }

    /**
     * Set the upper price bound of the buyer.
     * @param upperPrice the new upper price bound of the buyer.
     */
    public void setUpperPrice(int upperPrice)
    {
        if (upperPrice < 0 || upperPrice < this.lowerPrice)
        {
            throw new RuntimeException
                    ("Illegal upper price bound. " +
                            "Upper bound for prices cannot be less than 0 or less than lower bound.\n");
        }
        this.upperPrice = upperPrice;
    }

    /**
     * Get if this buyer is a current customer.
     * @return if the buyer is a current customer.
     */
    public boolean getCurrentCustomer()
    {
        return this.currentCustomer;
    }

    /**
     * Set the status of this buyer.
     * @param currentCustomer the status of this buyer.
     */
    public void setCurrentCustomer(boolean currentCustomer)
    {
        this.currentCustomer = currentCustomer;
    }

    /**
     * Alias for getProperties1.
     * @return properties1 (properties viewed.)
     */
    public ArrayList<Property> getPropertiesViewed()
    {
        return getProperties1();
    }

    /**
     * Alias for addToProperties1.
     * @param property the property to add.
     * @return true or false based on if the property was added or not.
     */
    public boolean addToPropertiesViewed(Property property)
    {
        return addToProperties1(property);
    }

    /**
     * Alias for removeFromProperties1.
     * @param property the property to remove.
     * @return true or false based on if the property was added or not.
     */
    public boolean removeFromPropertiesViewed(Property property)
    {
        return removeFromProperties1(property);
    }

    /**
     * Alias for clearProperties1.
     */
    public void clearPropertiesViewed()
    {
        clearProperties1();
    }

    /**
     * Alias for getProperties2.
     * @return the properties this buyer has purchased.
     */
    public ArrayList<Property> getPropertiesPurchased()
    {
        return getProperties2();
    }

    /**
     * Alias for addToProperties2.
     * @param property the property to add.
     * @return true or false based on if the property was added or not.
     */
    public boolean addToPropertiesPurchased(Property property)
    {
        return addToProperties2(property);
    }

    /**
     * Alias for removeFromProperties2.
     * @param property the property to remove.
     * @return true or false based on if the property was removed or not.
     */
    public boolean removeFromPropertiesPurchased(Property property)
    {
        return removeFromProperties2(property);
    }

    /**
     * Alias for clearProperties2.
     */
    public void clearPropertiesPurchased()
    {
        clearProperties2();
    }

    @Override
    public String toString()
    {
        String formatted = String.format
                ("ID = %d: Name = %s; Email = %s; Phone = %s; Price Range = %d-%d; In the market = %s\n", getId(), getName(), getEmail(), getPhoneNumber(),
                        this.lowerPrice, this.upperPrice, this.currentCustomer);
        return formatted;
    }
}