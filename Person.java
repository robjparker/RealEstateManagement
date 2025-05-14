import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Superclass of all people objects, defining common fields and methods.
 *
 * @author John Ryder and Jelal Kaufman
 * @version 1.0
 */
public abstract class Person implements Serializable {
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private ArrayList<Property> properties1;
    private ArrayList<Property> properties2;

    /**
     * Construct the person object.
     * @param id the person's id.
     * @param name the person's name.
     * @param email the person's email.
     * @param phoneNumber the person's phone #.
     */
    public Person(int id, String name, String email, String phoneNumber)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.properties1 = new ArrayList<>();
        this.properties2 = new ArrayList<>();
    }

    /**
     * Get the person's id. (ID's are immutable to preserve referential integrity)
     * @return the person's id
     */
    public int getId()
    {
        return  this.id;
    }

    /**
     * Get the person's name.
     * @return the person's name.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Set the person's name.
     * @param name the new name of the person.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Get the person's email.
     * @return the person's email.
     */
    public String getEmail()
    {
        return this.email;
    }

    /**
     * Set the person's email.
     * @param email the new email of the person.
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Get the person's phone number.
     * @return the person's phone number.
     */
    public String getPhoneNumber()
    {
        return this.phoneNumber;
    }

    /**
     * Set the person's phone number.
     * @param phoneNumber the new phone number of the person.
     */
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get the person's first array list of properties (contents/context differ based on subclass.
     * @return the first array list of properties.
     */
    public ArrayList<Property> getProperties1()
    {
        return this.properties1;
    }

    /**
     * Add a property to property1.
     * @param property the property to add.
     * @return true if the property was added successfully, false if not (will fail if property in properties1).
     */
    public boolean addToProperties1(Property property)
    {
        if (this.properties1.contains(property))
        {
            return false;
        }
        this.properties1.add(property);
        return true;
    }

    /**
     * Remove a property from property1.
     * @param property the property to remove.
     * @return true if the property was removed successfully, false if not (will fail if property not in properties1).
     */
    public boolean removeFromProperties1(Property property)
    {
        if (!this.properties1.contains(property))
        {
            return false;
        }
        this.properties1.remove(property);
        return true;
    }

    /**
     * Remove all properties from property1.
     */
    public void clearProperties1()
    {
        this.properties1 = new ArrayList<>();
    }

    /**
     * Get the person's second array list of properties (contents/context differ based on subclass.
     * @return the second array list of properties.
     */
    public ArrayList<Property> getProperties2()
    {
        return this.properties2;
    }

    /**
     * Add a property to property2.
     * @param property the property to add.
     * @return true if the property was added successfully, false if not (will fail if property in properties1).
     */
    public boolean addToProperties2(Property property)
    {
        if (this.properties2.contains(property))
        {
            return false;
        }
        this.properties2.add(property);
        return true;
    }

    /**
     * Remove a property from property2.
     * @param property the property to remove.
     * @return true if the property was removed successfully, false if not (will fail if property not in properties1).
     */
    public boolean removeFromProperties2(Property property)
    {
        if (!this.properties2.contains(property))
        {
            return false;
        }
        this.properties2.remove(property);
        return true;
    }

    /**
     * Remove all properties from property2.
     */
    public void clearProperties2()
    {
        this.properties2 = new ArrayList<>();
    }

    @Override
    public String toString()
    {
        String formatted = String.format
                ("ID = %d: Name = %s; Email = %s; Phone = %s\n", this.id, this.name, this.email, this.phoneNumber);
        return formatted;
    }

    @Override
    public boolean equals(Object obj) {
        Person otherPerson = (Person) obj;
        return this.id == otherPerson.getId();
    }
}
