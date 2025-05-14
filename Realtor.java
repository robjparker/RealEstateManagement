import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class representing a realtor.
 *
 * @author John Ryder
 * @version 1.0
 */
public class Realtor extends Person implements Serializable {
    private int id;
    private ArrayList<Lead> leads;

    public Realtor(int id, String name, String email, String phoneNumber)
    {
        super(id, name, email, phoneNumber);
    }

    /**
     * Alias for getProperties1.
     * @return properties1 (properties for sale.)
     */
    public ArrayList<Property> getCurrentProperties()
    {
        return getProperties1();
    }

    /**
     * Alias for addToProperties1.
     * @param property the property to add.
     * @return true or false based on if the property was added or not.
     */
    public boolean addToCurrentProperties(Property property)
    {
        return addToProperties1(property);
    }

    /**
     * Alias for removeFromProperties1.
     * @param property the property to remove.
     * @return true or false based on if the property was added or not.
     */
    public boolean removeFromCurrentProperties(Property property)
    {
        return removeFromProperties1(property);
    }

    /**
     * Alias for clearProperties1.
     */
    public void clearCurrentProperties()
    {
        clearProperties1();
    }

    /**
     * Alias for getProperties2.
     * @return the properties this realtor has previously been the realtor for.
     */
    public ArrayList<Property> getPreviousProperties()
    {
        return getProperties2();
    }

    /**
     * Alias for addToProperties2.
     * @param property the property to add.
     * @return true or false based on if the property was added or not.
     */
    public boolean addToPreviousProperties(Property property)
    {
        return addToProperties2(property);
    }

    /**
     * Alias for removeFromProperties2.
     * @param property the property to remove.
     * @return true or false based on if the property was removed or not.
     */
    public boolean removeFromPreviousProperties(Property property)
    {
        return removeFromProperties2(property);
    }

    /**
     * Alias for clearProperties2.
     */
    public void clearPreviousProperties()
    {
        clearProperties2();
    }

    public ArrayList<Lead> getLeads()
    {
        return this.leads;
    }

    public boolean addToLeads(Lead lead)
    {
        if (leads.contains(lead))
        {
            return false;
        }
        else
        {
            leads.add(lead);
            return true;
        }
    }

    public boolean removeFromLeads(Lead lead)
    {
        if (leads.contains(lead))
        {
            leads.remove(lead);
            return true;
        }
        else
        {
            return false;
        }
    }
}