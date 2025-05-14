import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Class representing a property.
 *
 * @author John Ryder and Jelal Kaufman
 * @version 1.0
 */
public class Property implements Serializable {

    private int id;
    private String streetAddress;
    private String city;
    private String state;
    private int zip;
    private int price;
    private String picURL;
    private ArrayList<String> amenities;
    private String description;
    private boolean isForSale;
    private Person currentOwner;
    private Realtor currentRealtor;
    ArrayList<Realtor> pastRealtors;
    ArrayList<Buyer> interestedCustomers;
    ArrayList<Buyer> pastOwners;

    /**
     * Construct the property object.
     * @param id the property's id.
     * @param streetAddress the property's street address.
     * @param city the property's city.
     * @param state the property's state.
     * @param zip the property's zip.
     * @param price the property's price.
     * @param isForSale if the property is for sale.
     * @param currentOwner the current owner of the property.
     */
    public Property(int id, String streetAddress, String city, String state, int zip, int price, String picURL, ArrayList<String> amenities,
                    String description, boolean isForSale, Person currentOwner, Realtor currentRealtor)
    {
        this.id = id;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.price = price;
        this.picURL = "";
        this.amenities = new ArrayList<>();
        this.description = "";
        this.isForSale = isForSale;
        this.currentOwner = currentOwner;
        this.currentRealtor = currentRealtor;
        this.pastRealtors = new ArrayList<>();
        this.interestedCustomers = new ArrayList<>();
        this.pastOwners = new ArrayList<>();
    }

    /**
     * Get the property's id.
     * @return the property's id.
     */
    public int getId()
    {
        return this.id;
    }

    /**
     * Get the property's street address.
     * @return the property's street address.
     */
    public String getStreetAddress()
    {
        return this.streetAddress;
    }

    /**
     * Set the property's street address.
     * @param streetAddress the new street address.
     */
    public void setStreetAddress(String streetAddress)
    {
        this.streetAddress = streetAddress;
    }

    /**
     * Get the property's city.
     * @return the property's city.
     */
    public String getCity()
    {
        return this.city;
    }

    /**
     * Set the property's city.
     * @param city the property's new city.
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     * Get the property's state.
     * @return the property's state.
     */
    public String getState()
    {
        return this.state;
    }

    /**
     * Set the property's state.
     * @param state the property's new state.
     */
    public void setState(String state)
    {
        this.state = state;
    }

    /**
     * Get the property's zip.
     * @return the property's zip.
     */
    public int getZip()
    {
        return this.zip;
    }

    /**
     * Set the property's zip.
     * @param zip the property's new zip.
     */
    public void setZip(int zip)
    {
        this.zip = zip;
    }

    /**
     * Get this property's price.
     * @return this property's price.
     */
    public int getPrice()
    {
        return this.price;
    }

    /**
     * Set this property's price.
     * @param price the property's new price.
     * @return true if price was set successfully, false if not (will fail if price <= 0).
     */
    public boolean setPrice(int price)
    {
        if (price <= 0)
        {
            throw new RuntimeException("Illegal price value. Price cannot be <= 0.\n");
        }
        this.price = price;
        return true;
    }

    /**
     * Get this property's picture URL.
     * @return this property's picture URL.
     */
    public String getPicURL()
    {
        return this.picURL;
    }

    /**
     * Set this property's picture URL.
     * @param picURL this property's new picture URL.
     */
    public void setPicURL(String picURL)
    {
        this.picURL = picURL;
    }

    /**
     * Get this property's amenities.
     * @return this property's amenities.
     */
    public ArrayList<String> getAmenities()
    {
        return this.amenities;
    }

    /**
     * Add an amenity to this property.
     * @param amenity the amenity to add.
     * @return true if the amenity is added successfully, false if not (will fail if amenity already in list.)
     */
    public boolean addToAmenities(String amenity)
    {
        if (this.amenities.contains(amenity))
        {
            return false;
        }
        this.amenities.add(amenity);
        return true;
    }

    /**
     * Remove an amenity from this property.
     * @param amenity the amenity to remove.
     * @return true if the amenity was removed successfully, false if not (will fail if the amenity not in list.)
     */
    public boolean removeFromAmenities(String amenity)
    {
        if (!this.amenities.contains(amenity))
        {
            return false;
        }
        this.amenities.remove(amenity);
        return true;
    }

    /**
     * Remove all amenities.
     */
    public void clearAmenities()
    {
        this.amenities = new ArrayList<>();
    }

    /**
     * Get this property's description.
     * @return this property's description.
     */
    public String getDescription()
    {
        return this.description;
    }

    /**
     * Set this property's description.
     * @param description the property's new description.
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Check if this property is currently for sale.
     * @return if this property is currently for sale.
     */
    public boolean getIsForSale()
    {
        return this.isForSale;
    }

    /**
     * Set if this property is currently for sale.
     * @return if this property is currently for sale.
     */
    public void setIsForSale(boolean isForSale)
    {
        this.isForSale = isForSale;
    }

    public void setCurrentOwner(Seller seller) { this.currentOwner = currentOwner; }

    public void setCurrentRealtor(Realtor realtor) { this.currentRealtor = currentRealtor; }

    public ArrayList<Realtor> getPastRealtors() {return this.pastRealtors;}

    public boolean addToPastRealtors(Realtor realtor)
    {
        if(pastRealtors.contains(realtor))
        {
            return false;
        }
        else
        {
            pastRealtors.add(realtor);
            return true;
        }
    }

    public boolean removeFromPastRealtors(Realtor realtor)
    {
        if(pastRealtors.contains(realtor))
        {
            pastRealtors.remove(realtor);
            return true;
        }
        else
        {
            return false;
        }
    }

    public ArrayList<Buyer> getPastOwners() {return this.pastOwners;}

    public boolean addToPastOwners(Buyer owner)
    {
        if(pastOwners.contains(owner))
        {
            return false;
        }
        else
        {
            pastOwners.add(owner);
            return true;
        }
    }

    public boolean removeFromPastOwners(Buyer owner)
    {
        if(pastOwners.contains(owner))
        {
            pastOwners.remove(owner);
            return true;
        }
        else
        {
            return false;
        }
    }

    public ArrayList<Buyer> getInterestedCustomers() {return this.interestedCustomers;}

    public boolean addToInterestedCustomers(Buyer customer)
    {
        if (interestedCustomers.contains(customer))
        {
            return false;
        }
        else
        {
            interestedCustomers.add(customer);
            return true;
        }
    }

    public boolean removeFromInterestedCustomers(Buyer customer)
    {
        if (interestedCustomers.contains(customer))
        {
            interestedCustomers.remove(customer);
            return true;
        }
        else
        {
            return false;
        }
    }

    public Realtor getCurrRealtor(){return this.currentRealtor;}

    public Person getCurrOwner() {
        return this.currentOwner;
    }

    public String toString()
    {
        return String.format("ID = %d, Street Address = %s, City = %s, State = %s, Zip = %d, Price = %d\n", id, streetAddress, city, state, zip, price);
    }

}
