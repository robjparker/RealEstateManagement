import java.io.*;
import java.text.FieldPosition;
import java.util.ArrayList;

import static java.lang.System.exit;

/**
 * Load data from a file into our application
 *
 * @Author John Ryder and Jelal Kaufman
 * @Version 3/13/25
 *
 * Honor Code/Source Acknowledgements:
 * Used https://www.baeldung.com/java-serialization for help with serialization.
 */

public class LoadData {

    public static ArrayList<Buyer> loadBuyers () throws RuntimeException
    {
        try {
            FileInputStream inStream = new FileInputStream("buyers.rp");
            ObjectInputStream objIn = new ObjectInputStream(inStream);
            ArrayList<Buyer> buyers = (ArrayList<Buyer>) objIn.readObject();
            return buyers;
        } catch (IOException e) {
            throw new RuntimeException("File read error.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Buyer data read error.");
        }
    }
    public static ArrayList<Seller> loadSellers() throws RuntimeException
    {
        try {
            FileInputStream inStream = new FileInputStream("sellers.rp");
            ObjectInputStream objIn = new ObjectInputStream(inStream);
            ArrayList<Seller> sellers = (ArrayList<Seller>) objIn.readObject();
            return sellers;
        } catch (IOException e) {
            throw new RuntimeException("File read error.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Buyer data read error.");
        }
    }

    public static ArrayList<Realtor> loadRealtors() throws RuntimeException
    {
        try {
            FileInputStream inStream = new FileInputStream("realtors.rp");
            ObjectInputStream objIn = new ObjectInputStream(inStream);
            ArrayList<Realtor> realtors = (ArrayList<Realtor>) objIn.readObject();
            return realtors;
        } catch (IOException e) {
            throw new RuntimeException("File read error.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Buyer data read error.");
        }
    }

    public static ArrayList<Property> loadProperties() throws RuntimeException
    {
        try {
            FileInputStream inStream = new FileInputStream("properties.rp");
            ObjectInputStream objIn = new ObjectInputStream(inStream);
            ArrayList<Property> properties = (ArrayList<Property>) objIn.readObject();
            return properties;
        } catch (IOException e) {
            throw new RuntimeException("File read error.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Buyer data read error.");
        }
    }

    public static ArrayList<Lead> loadLeads() throws RuntimeException
    {
        try {
            FileInputStream inStream = new FileInputStream("leads.rp");
            ObjectInputStream objIn = new ObjectInputStream(inStream);
            ArrayList<Lead> leads = (ArrayList<Lead>) objIn.readObject();
            return leads;
        } catch (IOException e) {
            throw new RuntimeException("File read error.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Buyer data read error.");
        }
    }

    public static ArrayList<Integer> loadIDs() throws RuntimeException
    {
        try {
            FileInputStream inStream = new FileInputStream("ids.rp");
            ObjectInputStream objIn = new ObjectInputStream(inStream);
            ArrayList<Integer> ids = (ArrayList<Integer>) objIn.readObject();
            return ids;
        } catch (IOException e) {
            throw new RuntimeException("File read error.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("ID data read error.");
        }
    }

}
