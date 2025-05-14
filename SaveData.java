import java.io.*;
import java.util.ArrayList;

/**
 * A class to handle the saving of data from our application.
 *
 * @Author John Ryder and Jelal Kaufman
 * @Version 4/11/25
 *
 * Honor Code/Source Acknowledgements:
 * Used https://www.baeldung.com/java-serialization for help with serialization.
 */
public class SaveData {

    public static void saveBuyer(ArrayList<Buyer> buyers)
    {
        FileOutputStream fileStream = null;
        ObjectOutputStream objOut = null;
        try
        {
            fileStream = new FileOutputStream("buyers.rp");
            objOut = new ObjectOutputStream(fileStream);
            objOut.writeObject(buyers);
            objOut.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException("Save exception: Illegal filename.");
        }
    }

    public static void saveSeller(ArrayList<Seller> sellers)
    {
        FileOutputStream fileStream = null;
        ObjectOutputStream objOut = null;
        try
        {
            fileStream = new FileOutputStream("sellers.rp");
            objOut = new ObjectOutputStream(fileStream);
            objOut.writeObject(sellers);
            objOut.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException("Save exception: Illegal filename.");
        }
    }

    public static void saveRealtor(ArrayList<Realtor> realtors)
    {
        FileOutputStream fileStream = null;
        ObjectOutputStream objOut = null;
        try
        {
            fileStream = new FileOutputStream("realtors.rp");
            objOut = new ObjectOutputStream(fileStream);
            objOut.writeObject(realtors);
            objOut.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException("Save exception: Illegal filename.");
        }
    }

    public static void saveProperty(ArrayList<Property> properties)
    {
        FileOutputStream fileStream = null;
        ObjectOutputStream objOut = null;
        try
        {
            fileStream = new FileOutputStream("properties.rp");
            objOut = new ObjectOutputStream(fileStream);
            objOut.writeObject(properties);
            objOut.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException("Save exception: Illegal filename.");
        }
    }

    public static void saveLeads(ArrayList<Lead> leads)
    {
        FileOutputStream fileStream = null;
        ObjectOutputStream objOut = null;
        try
        {
            fileStream = new FileOutputStream("leads.rp");
            objOut = new ObjectOutputStream(fileStream);
            objOut.writeObject(leads);
            objOut.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException("Save exception: Illegal filename.");
        }
    }

    public static void saveIDs(ArrayList<Integer> ids)
    {
        FileOutputStream fileStream = null;
        ObjectOutputStream objOut = null;
        try
        {
            fileStream = new FileOutputStream("ids.rp");
            objOut = new ObjectOutputStream(fileStream);
            objOut.writeObject(ids);
            objOut.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException("Save exception: Illegal filename.");
        }
    }
}
