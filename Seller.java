import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class representing a seller
 *
 * @author John Ryder and Jelal Kaufman
 * @version 1.0
 */
public class Seller extends Person implements Serializable {

    private boolean currentSeller;
    private int id;
    /**
     * Construct the seller object.
     * @param id the seller's id.
     * @param name the name of the seller.
     * @param email the email of the seller.
     * @param phoneNumber the phone number of the seller.
     * @param currentSeller if the seller is currently selling a property(s) or not.
     */
    public Seller(int id, String name, String email, String phoneNumber, boolean currentSeller)
    {
        super(id, name, email, phoneNumber);
        this.currentSeller = currentSeller;

    }

    /**
     * Get the state of this seller.
     * @return the state of this seller.
     */
    public boolean getCurrentSeller()
    {
        return this.currentSeller;
    }

    /**
     * Set the state of this seller.
     * @param currentSeller the new state of this seller.
     */
    public void setCurrentSeller(boolean currentSeller)
    {
        this.currentSeller = currentSeller;
    }

    /**
     * Alias for getProperties1.
     * @return properties1 (properties for sale.)
     */
    public ArrayList<Property> getPropertiesForSale()
    {
        return getProperties1();
    }

    /**
     * Alias for addToProperties1.
     * @param property the property to add.
     * @return true or false based on if the property was added or not.
     */
    public boolean addToPropertiesForSale(Property property)
    {
        return addToProperties1(property);
    }

    /**
     * Alias for removeFromProperties1.
     * @param property the property to remove.
     * @return true or false based on if the property was added or not.
     */
    public boolean removeFromPropertiesForSale(Property property)
    {
        return removeFromProperties1(property);
    }

    /**
     * Alias for clearProperties1.
     */
    public void clearPropertiesForSale()
    {
        clearProperties1();
    }

    /**
     * Alias for getProperties2.
     * @return the properties this seller has sold.
     */
    public ArrayList<Property> getPropertiesSold()
    {
        return getProperties2();
    }

    /**
     * Alias for addToProperties2.
     * @param property the property to add.
     * @return true or false based on if the property was added or not.
     */
    public boolean addToPropertiesSold(Property property)
    {
        return addToProperties2(property);
    }

    /**
     * Alias for removeFromProperties2.
     * @param property the property to remove.
     * @return true or false based on if the property was removed or not.
     */
    public boolean removeFromPropertiesSold(Property property)
    {
        return removeFromProperties2(property);
    }

    /**
     * Alias for clearProperties2.
     */
    public void clearPropertiesSold()
    {
        clearProperties2();
    }

    @Override
    public String toString()
    {
        String formatted = String.format
                ("ID = %d: Name = %s; Email = %s; Phone = %s; In the market = %s\n", getId(), getName(), getEmail(), getPhoneNumber(), this.currentSeller);
        return formatted;
    }
}
