import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Primary functionality of our app.
 *
 * @author John Ryder and Jelal Kaufman
 * @version 1.1
 * Honor Code and Acknowledgements:
 * - Used https://www.freecodecamp.org/news/java-scanner-nextline-call-gets-skipped-solved/ for why some nextline
 *   calls were being skipped.
 * Known flaws/issues to be fixed:
 * - Right now, nothing that links properties to people or v.v. can be added when the item is being created. We need
 *   to add this for the next step.
 * - If the system is expecting an int and a string is given, an exception will be thrown.
 * - Our system stores a list of past owners, but we do not save exact data about the sale (when, how much, etc).
 */
public class NewMainApp {

    private static ArrayList<Buyer> buyers = new ArrayList<>();
    private static ArrayList<Seller> sellers = new ArrayList<>();
    private static ArrayList<Realtor> realtors = new ArrayList<>();
    private static ArrayList<Property> properties = new ArrayList<>();
    private static ArrayList<Lead> leads = new ArrayList<>();
    private static int buyerID = 0;
    private static int sellerID = 0;
    private static int realtorID = 0;
    private static int propertyID = 0;
    private static int leadID = 0;

    /**
     * Print the property value histogram
     */
    public static void printHistogram() {
        int[] bins = new int[5];
        ArrayList<Integer> values = new ArrayList<>();
        for(int i = 0; i < properties.size(); i++)
        {
            values.add(properties.get(i).getPrice());
        }
        int total = values.size();
        for (int value : values) {
            if (value == 0)
                total--;
            else if (value < 200000)
                bins[0]++;
            else if (value < 400000)
                bins[1]++;
            else if (value < 600000)
                bins[2]++;
            else if (value < 800000)
                bins[3]++;
            else
                bins[4]++;
        }
        String[] labels = {
                "$0 - $200k: ",
                "$200k - $400k: ",
                "$400k - $600k: ",
                "$600k - $800k: ",
                "$800k - $1m: "
        };
        for (int i = 0; i < bins.length; i++) {
            int percentageBarLength = (int) ((bins[i] / (double) total) * 20);
            String bar = "";
            for (int j = 0; j < percentageBarLength; j++)
                bar += "=";
            System.out.printf("\n%s |%s (%d)%n\n", labels[i], bar, bins[i]);
        }
    }


    /**
     * Run the application.
     */
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("J&J Realty System, Version 1.1");
        System.out.println("Please press \'Y\' to load saved data; \'N\' to skip.");
        if (scanner.nextLine().equalsIgnoreCase("y")) //Load saved data from files.
        {
            try
            {
                buyers = LoadData.loadBuyers();
            } catch (RuntimeException e) {
                System.out.println("Data load error; please check buyer file.");
            }
            try
            {
                sellers = LoadData.loadSellers();
            } catch (RuntimeException e) {
                System.out.println("Data load error; please check seller file.");
            }
            try
            {
                realtors = LoadData.loadRealtors();
            } catch (RuntimeException e) {
                System.out.println("Data load error; please check realtor file.");
            }
            try
            {
                properties = LoadData.loadProperties();
            } catch (RuntimeException e) {
                System.out.println("Data load error; please check property file.");
            }
            try
            {
                leads = LoadData.loadLeads();
            } catch (RuntimeException e) {
                System.out.println("Data load error; please check lead file.");
            }
            try
            {
                ArrayList<Integer> ids = LoadData.loadIDs();
                buyerID = ids.get(0);
                sellerID = ids.get(1);
                realtorID = ids.get(2);
                propertyID = ids.get(3);
                leadID = ids.get(4);
            } catch (RuntimeException e) {
                System.out.println("Data load error; please check ID file.");
            }

        }
        boolean prompt = true;
        while(prompt)
        {
            System.out.println("Please Enter a Function:");
            System.out.println("R: Realtor Functions");
            System.out.println("B: Buyer Functions");
            System.out.println("S: Seller Functions");
            System.out.println("P: Property Functions");
            System.out.println("H: Histogram");
            System.out.println("Q: Quit Application");
            String nextIn = scanner.nextLine();
            if (nextIn.equalsIgnoreCase("R"))
            {
                realtorFunctions();
            } else if (nextIn.equalsIgnoreCase("B"))
            {
                buyerFunctions();
            } else if (nextIn.equalsIgnoreCase("S"))
            {
                sellerFunctions();
            } else if (nextIn.equalsIgnoreCase("P"))
            {
                propertyFunctions();
            } else if (nextIn.equalsIgnoreCase("H"))
            {
                printHistogram();
            } else if (nextIn.equalsIgnoreCase("Q"))
            {
                prompt = false;
            }
        }
        System.out.println("Save data? Y or N:");
        if(scanner.nextLine().equalsIgnoreCase("y"))
        {
            SaveData.saveBuyer(buyers);
            SaveData.saveSeller(sellers);
            SaveData.saveRealtor(realtors);
            SaveData.saveProperty(properties);
            SaveData.saveLeads(leads);
            ArrayList<Integer> ids = new ArrayList<>();
            ids.add(buyerID);
            ids.add(sellerID);
            ids.add(realtorID);
            ids.add(propertyID);
            ids.add(leadID);
            SaveData.saveIDs(ids);
            System.out.println("Data saved successfully!");
        }
        System.out.println("Logging out...");
    }

    public static void buyerFunctions()
    {
        System.out.println("Buyer Functions:");
        Scanner scanner = new Scanner(System.in);
        boolean prompt = true;
        while(prompt)
        {
            System.out.println("A: Add new buyer");
            System.out.println("E: Edit existing buyer");
            System.out.println("R: Remove existing buyer");
            System.out.println("L: List existing buyers");
            System.out.println("Q: Return to main menu");
            String nextIn = scanner.nextLine();
            if(nextIn.equalsIgnoreCase("A"))
            {
                addPerson(1);
            } else if (nextIn.equalsIgnoreCase("R"))
            {
                removePerson(1);
            } else if (nextIn.equalsIgnoreCase("E"))
            {
                editPerson(1);
            } else if (nextIn.equalsIgnoreCase("L"))
            {
                listPerson(1);
            } else if (nextIn.equalsIgnoreCase("Q"))
            {
                prompt = false;
            }
        }
    }

    public static void sellerFunctions()
    {
        System.out.println("Seller Functions:");
        Scanner scanner = new Scanner(System.in);
        boolean prompt = true;
        while(prompt)
        {
            System.out.println("A: Add new seller");
            System.out.println("E: Edit existing seller");
            System.out.println("R: Remove existing seller");
            System.out.println("L: List existing seller");
            System.out.println("Q: Return to main menu");
            String nextIn = scanner.nextLine();
            if(nextIn.equalsIgnoreCase("A"))
            {
                addPerson(2);
            } else if (nextIn.equalsIgnoreCase("R"))
            {
                removePerson(2);
            } else if (nextIn.equalsIgnoreCase("E"))
            {
                editPerson(2);
            } else if (nextIn.equalsIgnoreCase("L"))
            {
                listPerson(2);
            } else if (nextIn.equalsIgnoreCase("Q"))
            {
                prompt = false;
            }
        }
    }

    public static void realtorFunctions()
    {
        System.out.println("Realtor Functions:");
        Scanner scanner = new Scanner(System.in);
        boolean prompt = true;
        while(prompt)
        {
            System.out.println("A: Add new realtor");
            System.out.println("E: Edit existing realtor");
            System.out.println("R: Remove existing realtor");
            System.out.println("L: List existing realtors");
            System.out.println("LA: Add Lead");
            System.out.println("LR: Remove Lean");
            System.out.println("LL: List Leads");
            System.out.println("Q: Return to main menu");
            String nextIn = scanner.nextLine();
            if(nextIn.equalsIgnoreCase("A"))
            {
                addPerson(3);
            } else if (nextIn.equalsIgnoreCase("R"))
            {
                removePerson(3);
            } else if (nextIn.equalsIgnoreCase("E"))
            {
                editPerson(3);
            } else if (nextIn.equalsIgnoreCase("L"))
            {
                listPerson(3);
            } else if (nextIn.equalsIgnoreCase("LA"))
            {
                manageLeads(1);
            }
            else if (nextIn.equalsIgnoreCase("LR"))
            {
                manageLeads(2);
            }
            else if (nextIn.equalsIgnoreCase("LL"))
            {
                listLeads();
            }
            else if (nextIn.equalsIgnoreCase("Q"))
            {
                prompt = false;
            }
        }
    }

    public static void propertyFunctions()
    {
        System.out.println("Property Functions:");
        Scanner scanner = new Scanner(System.in);
        boolean prompt = true;
        while(prompt)
        {
            System.out.println("A: Add new property");
            System.out.println("E: Edit existing property");
            System.out.println("R: Remove existing property");
            System.out.println("L: List existing properties");
            System.out.println("Q: Return to main menu");
            String nextIn = scanner.nextLine();
            if(nextIn.equalsIgnoreCase("A"))
            {
                addProperty();
            } else if (nextIn.equalsIgnoreCase("R"))
            {
                removeProperty();
            } else if (nextIn.equalsIgnoreCase("E"))
            {
                editProperty();
            } else if (nextIn.equalsIgnoreCase("L"))
            {
                listProperty();
            } else if (nextIn.equalsIgnoreCase("Q"))
            {
                prompt = false;
            }
        }
    }



    public static void addPerson(int classification)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter person name:");
        String name = scanner.nextLine();
        System.out.println("Enter person email:");
        String email = scanner.nextLine();
        System.out.println("Enter person phone:");
        String phone = scanner.nextLine();
        if (classification == 1) //Buyer
        {
            System.out.println("Enter upper price bound:");
            int upperPrice = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter lower price bound:");
            int lowerPrice = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Is this buyer currently in the market?: Y or N");
            if (scanner.nextLine().equalsIgnoreCase("y"))
            {
                Buyer newBuyer = new Buyer(buyerID, name, email, phone, lowerPrice, upperPrice, true);
                buyerID = buyerID + 1;
                buyers.add(newBuyer);
            } else {
                Buyer newBuyer = new Buyer(buyerID, name, email, phone, lowerPrice, upperPrice, false);
                buyerID = buyerID + 1;
                buyers.add(newBuyer);
            }
        }
        else if (classification == 2) //Seller
        {
            System.out.println("Is this seller currently in the market?: Y or N");
            if (scanner.nextLine().equalsIgnoreCase("Y"))
            {
                Seller newSeller = new Seller(sellerID, name, email, phone, true);
                sellerID = sellerID + 1;
                sellers.add(newSeller);
            } else {
                Seller newSeller = new Seller(sellerID,name, email, phone, false);
                sellerID = sellerID + 1;
                sellers.add(newSeller);
            }
        }
        else if (classification == 3)//Realtor
        {
            Realtor newRealtor = new Realtor(realtorID, name, email, phone);
            realtorID = realtorID + 1;
            realtors.add(newRealtor);
        }
    }

    public static void removePerson(int classification)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("WARNING: THIS ACTION CANNOT BE UNDONE; Enter id of person to delete:");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (classification == 1) //Buyer
        {
            Buyer matchedBuyer = null;
            for (int i = 0; i < buyers.size(); i++)
            {
                Buyer curBuyer = buyers.get(i);
                if(curBuyer.getId() == id)
                {
                    matchedBuyer = curBuyer;
                    break;
                }
            }
            if (matchedBuyer == null)
            {
                System.out.println("Error: No buyer with specified ID.");
            } else {
                for (int i = 0; i < matchedBuyer.getPropertiesViewed().size(); i++)
                {
                    Property cur = matchedBuyer.getPropertiesViewed().get(i);
                    cur.removeFromInterestedCustomers(matchedBuyer);
                }
                for (int i = 0; i < matchedBuyer.getPropertiesPurchased().size(); i++)
                {
                    Property cur = matchedBuyer.getPropertiesPurchased().get(i);
                    cur.removeFromPastOwners(matchedBuyer);
                }
                buyers.remove(matchedBuyer);
                System.out.println("Buyer removed successfully.");
            }
        }
        else if (classification == 2) //Seller
        {
            Seller matchedSeller = null;
            for (int i = 0; i < sellers.size(); i++)
            {
                Seller curSeller = sellers.get(i);
                if(curSeller.getId() == id)
                {
                    matchedSeller = curSeller;
                    break;
                }
            }
            if (matchedSeller == null)
            {
                System.out.println("Error: No seller with specified ID.");
            } else {
                for (int i = 0; i < matchedSeller.getPropertiesForSale().size(); i++)
                {
                    Property cur = matchedSeller.getPropertiesForSale().get(i);
                    cur.setCurrentOwner(null);
                }
                sellers.remove(matchedSeller);
                System.out.println("Seller removed successfully.");
            }
        }
        else if (classification == 3) //Realtor
        {
            Realtor matchedRealtor = null;
            for(int i = 0; i < realtors.size(); i++)
            {
                Realtor curRealtor = realtors.get(i);
                if(curRealtor.getId() == id)
                {
                    matchedRealtor = curRealtor;
                    break;
                }
            }
            if (matchedRealtor == null)
            {
                System.out.println("Error: No realtor with specified ID.");
            } else {
                for(int i = 0; i < matchedRealtor.getCurrentProperties().size(); i++)
                {
                    Property cur = matchedRealtor.getCurrentProperties().get(i);
                    cur.setCurrentRealtor(null);
                }
                for(int i = 0; i < matchedRealtor.getPreviousProperties().size(); i++)
                {
                    Property cur = matchedRealtor.getPreviousProperties().get(i);
                    cur.removeFromPastRealtors(matchedRealtor);
                }
                realtors.remove(matchedRealtor);
                System.out.println("Realtor removed successfully.");
            }
        }
    }

    public static void editPerson(int classification)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("WARNING: THIS ACTION CANNOT BE UNDONE; Enter id of person to edit:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter person's new name:");
        String name = scanner.nextLine();
        System.out.println("Enter person's new email:");
        String email = scanner.nextLine();
        System.out.println("Enter person's new phone number:");
        String phoneNum = scanner.nextLine();
        if (classification == 1) //Buyer
        {
            Buyer matchedBuyer = null;
            for (int i = 0; i < buyers.size(); i++)
            {
                Buyer curBuyer = buyers.get(i);
                if(curBuyer.getId() == id)
                {
                    matchedBuyer = curBuyer;
                }
            }
            if (matchedBuyer == null)
            {
                System.out.println("Error: No buyer with specified ID.");
            } else {
                System.out.println("Enter buyer's new lower price range:");
                int lowerBound = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter buyer's new upper price range:");
                int upperBound = scanner.nextInt();
                scanner.nextLine();
                matchedBuyer.clearProperties1();
                System.out.println("Enter the IDs of properties this buyer has viewed; enter -1 when complete");
                boolean prompt = true;
                while(prompt)
                {
                    int nextIn = scanner.nextInt();
                    scanner.nextLine();
                    if (nextIn == (-1))
                    {
                        prompt = false;
                    }
                    else
                    {
                        Property markedProperty = null;
                        for(int i = 0; i < properties.size(); i++)
                        {
                            Property curProperty = properties.get(i);
                            if (curProperty.getId() == nextIn)
                            {
                                markedProperty = curProperty;
                                break;
                            }
                        }
                        if (markedProperty == null)
                        {
                            System.out.println("No property with specified ID exists.");
                        } else
                        {
                            matchedBuyer.addToProperties1(markedProperty);
                            markedProperty.addToInterestedCustomers(matchedBuyer);
                        }
                    }
                }
                matchedBuyer.clearProperties2();
                System.out.println("Enter the IDs of properties this buyer has purchased; enter -1 when complete");
                prompt = true;
                while(prompt)
                {
                    int nextIn = scanner.nextInt();
                    scanner.nextLine();
                    if (nextIn == (-1))
                    {
                        prompt = false;
                    }
                    else
                    {
                        Property markedProperty = null;
                        for(int i = 0; i < properties.size(); i++)
                        {
                            Property curProperty = properties.get(i);
                            if (curProperty.getId() == nextIn)
                            {
                                markedProperty = curProperty;
                                break;
                            }
                        }
                        if (markedProperty == null)
                        {
                            System.out.println("No property with specified ID exists.");
                        } else
                        {
                            matchedBuyer.addToProperties2(markedProperty);
                            markedProperty.addToPastOwners(matchedBuyer);
                        }
                    }
                }
                System.out.println("Is this buyer a current buyer? Y or N");
                boolean curBuyer = false;
                if (scanner.nextLine().equalsIgnoreCase("y"))
                {
                    curBuyer = true;
                }
                matchedBuyer.setName(name);
                matchedBuyer.setEmail(email);
                matchedBuyer.setPhoneNumber(phoneNum);
                matchedBuyer.setUpperPrice(upperBound);
                matchedBuyer.setLowerPrice(lowerBound);
                matchedBuyer.setCurrentCustomer(curBuyer);
                System.out.println("Buyer edited successfully.");
            }
        }
        else if(classification == 2) //Seller
        {
            Seller matchedSeller = null;
            for (int i = 0; i < sellers.size(); i++)
            {
                Seller curSeller = sellers.get(i);
                if(curSeller.getId() == id)
                {
                    matchedSeller = curSeller;
                }
            }
            if (matchedSeller == null)
            {
                System.out.println("Error: No seller with specified ID.");
            } else {

                System.out.println("Enter the IDs of properties this seller has for sale; enter -1 when complete");
                matchedSeller.clearProperties1();
                boolean prompt = true;
                while(prompt)
                {
                    int nextIn = scanner.nextInt();
                    scanner.nextLine();
                    if (nextIn == (-1))
                    {
                        prompt = false;
                    }
                    else
                    {
                        Property markedProperty = null;
                        for(int i = 0; i < properties.size(); i++)
                        {
                            Property curProperty = properties.get(i);
                            if (curProperty.getId() == nextIn)
                            {
                                markedProperty = curProperty;
                                break;
                            }
                        }
                        if (markedProperty == null)
                        {
                            System.out.println("No property with specified ID exists.");
                        } else
                        {
                            matchedSeller.addToProperties1(markedProperty);
                            markedProperty.setCurrentOwner(matchedSeller);
                        }
                    }
                }
                System.out.println("Enter the IDs of properties this seller has sold; enter -1 when complete");
                matchedSeller.clearProperties2();
                prompt = true;
                while(prompt)
                {
                    int nextIn = scanner.nextInt();
                    scanner.nextLine();
                    if (nextIn == (-1))
                    {
                        prompt = false;
                    }
                    else
                    {
                        Property markedProperty = null;
                        for(int i = 0; i < properties.size(); i++)
                        {
                            Property curProperty = properties.get(i);
                            if (curProperty.getId() == nextIn)
                            {
                                markedProperty = curProperty;
                                break;
                            }
                        }
                        if (markedProperty == null)
                        {
                            System.out.println("No property with specified ID exists.");
                        } else
                        {
                            matchedSeller.addToProperties2(markedProperty);
                        }
                    }
                }
                System.out.println("Is this seller currently in the market? Y or N:");
                boolean curSeller = false;
                if (scanner.nextLine().equalsIgnoreCase("y"))
                {
                    curSeller = true;
                }
                matchedSeller.setName(name);
                matchedSeller.setEmail(email);
                matchedSeller.setPhoneNumber(phoneNum);
                matchedSeller.setCurrentSeller(curSeller);
            }
        }
        else if(classification == 3) //Realtor
        {
            Realtor matchedRealtor = null;
            for (int i = 0; i < realtors.size(); i++) {
                Realtor curRealtor = realtors.get(i);
                if (curRealtor.getId() == id) {
                    matchedRealtor = curRealtor;
                }
            }
            if (matchedRealtor == null)
            {
                System.out.println("Error: No realtor with specified ID.");
            } else {
                matchedRealtor.setName(name);
                matchedRealtor.setEmail(email);
                matchedRealtor.setPhoneNumber(phoneNum);
                matchedRealtor.clearProperties1();
                System.out.println("Enter the IDs of properties this realtor is currently the realtor for; enter -1 when complete");
                boolean prompt = true;
                while(prompt)
                {
                    int nextIn = scanner.nextInt();
                    scanner.nextLine();
                    if (nextIn == (-1))
                    {
                        prompt = false;
                    }
                    else
                    {
                        Property markedProperty = null;
                        for(int i = 0; i < properties.size(); i++)
                        {
                            Property curProperty = properties.get(i);
                            if (curProperty.getId() == nextIn)
                            {
                                markedProperty = curProperty;
                                break;
                            }
                        }
                        if (markedProperty == null)
                        {
                            System.out.println("No property with specified ID exists.");
                        } else
                        {
                            matchedRealtor.addToProperties1(markedProperty);
                            markedProperty.setCurrentRealtor(matchedRealtor);
                        }
                    }
                }
                System.out.println("Enter the IDs of properties this realtor has previously been the realtor on; enter -1 when complete");
                matchedRealtor.clearProperties2();
                prompt = true;
                while(prompt)
                {
                    int nextIn = scanner.nextInt();
                    scanner.nextLine();
                    if (nextIn == (-1))
                    {
                        prompt = false;
                    }
                    else
                    {
                        Property markedProperty = null;
                        for(int i = 0; i < properties.size(); i++)
                        {
                            Property curProperty = properties.get(i);
                            if (curProperty.getId() == nextIn)
                            {
                                markedProperty = curProperty;
                                break;
                            }
                        }
                        if (markedProperty == null)
                        {
                            System.out.println("No property with specified ID exists.");
                        } else
                        {
                            matchedRealtor.addToProperties2(markedProperty);
                            markedProperty.addToPastRealtors(matchedRealtor);
                        }
                    }
                }
            }
        }
    }

    public static void listPerson(int classification)
    {
        if (classification == 1)
        {
            System.out.println("Buyers:");
            for (int i = 0; i < buyers.size(); i++)
            {
                Buyer currentBuyer = buyers.get(i);
                System.out.println(currentBuyer.toString());
                System.out.println("Properties Viewed:");
                for (int j = 0; j < currentBuyer.getPropertiesViewed().size(); j++)
                {
                    System.out.println(currentBuyer.getPropertiesViewed().get(j).toString());
                }
                System.out.println("Properties Purchased:");
                for (int j = 0; j < currentBuyer.getPropertiesPurchased().size(); j++)
                {
                    System.out.println(currentBuyer.getPropertiesPurchased().get(j).toString());
                }
            }
        }
        else if (classification == 2)
        {
            System.out.println("Sellers:");
            for (int i = 0; i < sellers.size(); i++)
            {
                System.out.println(sellers.get(i).toString());
                System.out.println("Properties for sale:");
                Seller currentSeller = sellers.get(i);
                for (int j = 0; j < currentSeller.getPropertiesForSale().size(); j++)
                {
                    System.out.println(currentSeller.getPropertiesForSale().get(j).toString());
                }
                System.out.println("Properties sold:");
                for (int j = 0; j < currentSeller.getPropertiesSold().size(); j++)
                {
                    System.out.println(currentSeller.getPropertiesSold().get(j).toString());
                }
            }
        }
        else if (classification == 3)
        {
            System.out.println("Realtors:");
            for (int i = 0; i < realtors.size(); i++)
            {
                Realtor currentRealtor = realtors.get(i);
                System.out.println(currentRealtor.toString());
                System.out.println("Current Properties:");
                for (int j = 0; j < currentRealtor.getCurrentProperties().size(); j++)
                {
                    System.out.println(currentRealtor.getCurrentProperties().get(j).toString());
                }
                System.out.println("Previous Properties:");
                for (int j = 0; j < currentRealtor.getPreviousProperties().size(); j++)
                {
                    System.out.println(currentRealtor.getPreviousProperties().get(j).toString());
                }
            }

        }
    }

    public static void addProperty()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter property street address: ");
        String address = scanner.nextLine();
        System.out.println("Enter property city:");
        String city = scanner.nextLine();
        System.out.println("Enter property state:");
        String state = scanner.nextLine();
        System.out.println("Enter property zip code:");
        int zip = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter property price as integer:");
        int price = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter property picture URL:");
        String url = scanner.nextLine();
        System.out.println("Enter property amenities; enter D when done:");
        boolean prompt = true;
        ArrayList<String> amenities = new ArrayList<>();
        while(prompt)
        {
            String nextIn = scanner.nextLine();
            if (nextIn.equalsIgnoreCase("d"))
            {
                prompt = false;
            }
            else
            {
                amenities.add(nextIn);
            }
        }
        System.out.println("Enter property description:");
        String description = scanner.nextLine();
        System.out.println("Is the property currently for sale? Y or N:");
        boolean forSale = false;
        if (scanner.nextLine().equalsIgnoreCase("Y"))
        {
            forSale = true;
        }
        Property newProperty = new Property(propertyID, address, city, state, zip, price, url, amenities, description, forSale, null, null);
        propertyID = propertyID + 1;
        properties.add(newProperty);
    }

    public static void removeProperty()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter property ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Property matchedProperty = null;
        for(int i = 0; i < properties.size(); i++)
        {
            Property curProperty = properties.get(i);
            if(curProperty.getId() == id)
            {
                matchedProperty = curProperty;
                break;
            }
        }
        if (matchedProperty == null)
        {
            System.out.println("Error: No property with specified id");
        }
        else
        {
            properties.remove(matchedProperty);
            for(int i = 0; i < buyers.size(); i++)
            {
                Person curPerson = (Person) buyers.get(i);
                if(curPerson.getProperties1().contains(matchedProperty))
                {
                    curPerson.removeFromProperties1(matchedProperty);
                }
                if(curPerson.getProperties2().contains(matchedProperty))
                {
                    curPerson.removeFromProperties2(matchedProperty);
                }
            }
            for(int i = 0; i < sellers.size(); i++)
            {
                Person curPerson = (Person) sellers.get(i);
                if(curPerson.getProperties1().contains(matchedProperty))
                {
                    curPerson.removeFromProperties1(matchedProperty);
                }
                if(curPerson.getProperties2().contains(matchedProperty))
                {
                    curPerson.removeFromProperties2(matchedProperty);
                }
            }
            for(int i = 0; i < realtors.size(); i++)
            {
                Person curPerson = (Person) realtors.get(i);
                if(curPerson.getProperties1().contains(matchedProperty))
                {
                    curPerson.removeFromProperties1(matchedProperty);
                }
                if(curPerson.getProperties2().contains(matchedProperty))
                {
                    curPerson.removeFromProperties2(matchedProperty);
                }
            }
            System.out.println("Property removed successfully.");
        }
    }

    public static void editProperty()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter property ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Property matchedProperty = null;
        for(int i = 0; i < properties.size(); i++)
        {
            Property curProperty = properties.get(i);
            if(curProperty.getId() == id)
            {
                matchedProperty = curProperty;
                break;
            }
        }
        if (matchedProperty == null)
        {
            System.out.println("Error: No property with specified id");
        }
        else
        {
            System.out.println("Enter property street address: ");
            String address = scanner.nextLine();
            System.out.println("Enter property city:");
            String city = scanner.nextLine();
            System.out.println("Enter property state:");
            String state = scanner.nextLine();
            System.out.println("Enter property zip code:");
            int zip = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter property price as integer:");
            int price = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter property picture URL:");
            String url = scanner.nextLine();
            System.out.println("Enter property amenities; enter D when done:");
            boolean prompt = true;
            while(prompt)
            {
                String nextIn = scanner.nextLine();
                if (nextIn.equalsIgnoreCase("d"))
                {
                    prompt = false;
                }
                else
                {
                    matchedProperty.addToAmenities(nextIn);
                }
            }
            System.out.println("Enter property description:");
            String description = scanner.nextLine();
            System.out.println("Is the property currently for sale? Y or N:");
            boolean forSale = false;
            if (scanner.nextLine().equalsIgnoreCase("Y"))
            {
                forSale = true;
            }
            boolean hasOwner = false;
            System.out.println("Does the property have an owner? Y or N:");
            if(scanner.nextLine().equalsIgnoreCase("Y"))
            {
                System.out.println("Enter seller ID");
                int sellerID = scanner.nextInt();
                scanner.nextLine();
                Seller matchedSeller = null;
                for (int i = 0; i < sellers.size(); i++)
                {
                    Seller curSeller = sellers.get(i);
                    if (curSeller.getId() == sellerID)
                    {
                        matchedSeller = curSeller;
                        break;
                    }
                }
                if(matchedSeller == null)
                {
                    System.out.println("No seller with specified ID.");
                }
                else
                {
                    matchedProperty.setCurrentOwner(matchedSeller);
                }
            }
            boolean hasReal = false;
            System.out.println("Does the property have a realtor? Y or N:");
            if(scanner.nextLine().equalsIgnoreCase("Y"))
            {
                System.out.println("Enter realtor ID");
                int realtorID = scanner.nextInt();
                scanner.nextLine();
                Realtor matchedRealtor = null;
                for (int i = 0; i < realtors.size(); i++)
                {
                    Realtor curRealtor = realtors.get(i);
                    if (curRealtor.getId() == realtorID)
                    {
                        matchedRealtor = curRealtor;
                        break;
                    }
                }
                if(matchedRealtor == null)
                {
                    System.out.println("No seller with specified ID.");
                }
                else
                {
                    matchedProperty.setCurrentRealtor(matchedRealtor);
                }
            }
            matchedProperty.setStreetAddress(address);
            matchedProperty.setCity(city);
            matchedProperty.setState(state);
            matchedProperty.setZip(zip);
            matchedProperty.setPrice(price);
            matchedProperty.setPicURL(url);
            matchedProperty.setDescription(description);
            matchedProperty.setIsForSale(forSale);
            System.out.println("Property edited successfully.");
        }
    }

    public static void listProperty()
    {
        System.out.println("Properties");
        for(int i = 0; i < properties.size(); i++)
        {
            System.out.println(properties.get(i).toString());
            Property curProperty = properties.get(i);
            System.out.println("Past realtors:");
            for(int j = 0; j < curProperty.getPastRealtors().size(); j++)
            {
                System.out.println(curProperty.getPastRealtors().get(j));
            }
            System.out.println("Interested Customers:");
            for(int j = 0; j < curProperty.getInterestedCustomers().size(); j++)
            {
                System.out.println(curProperty.getInterestedCustomers().get(j));
            }
            System.out.println("Past Owners:");
            for(int j = 0; j < curProperty.getPastOwners().size(); j++)
            {
                System.out.println(curProperty.getPastOwners().get(j));
            }

        }
    }

    public static void manageLeads(int addOrRemove)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter realtor ID to add lead to.");
        int nextIn = scanner.nextInt();
        scanner.nextLine();
        Realtor matchedRealtor = null;
        for (int i = 0; i < realtors.size(); i++)
        {
            Realtor cur = realtors.get(i);
            if (cur.getId() == nextIn)
            {
                matchedRealtor = cur;
                break;
            }
        }
        if (matchedRealtor == null)
        {
            System.out.println("Error: no realtor with specified ID.");
        }
        else
        {
            if (addOrRemove == 1) //Adding
            {
                System.out.println("Please enter seller's email:");
                String email = scanner.nextLine();
                System.out.println("Please enter seller's phone number:");
                String phoneNum = scanner.nextLine();
                System.out.println("Please enter lead note:");
                String note = scanner.nextLine();
                Lead newLead = new Lead(leadID, matchedRealtor);
                leadID = leadID + 1;
                newLead.setSellerEmail(email);
                newLead.setSellerPhone(phoneNum);
                newLead.setNote(note);
                leads.add(newLead);
                System.out.println("Lead added to realtor successfully.");
            }
            else
            {
                System.out.println("Please enter lead ID to remove:");
                int id = scanner.nextInt();
                scanner.nextLine();
                Lead matchedLead = null;
                for(int i = 0; i < matchedRealtor.getLeads().size(); i++)
                {
                    Lead cur = matchedRealtor.getLeads().get(i);
                    if (cur.getId() == id)
                    {
                        matchedLead = cur;
                        break;
                    }
                }
                if (matchedLead == null)
                {
                    System.out.println("Error: No lead with specified id.");
                }
                else
                {
                    matchedRealtor.removeFromLeads(matchedLead);
                    System.out.println("Removed lead from realtor.");
                }
            }
        }
    }

    public static void listLeads()
    {
        System.out.println("Leads:");
        for(int i = 0; i < leads.size(); i++)
        {
            System.out.println(leads.get(i).toString());
        }
    }
}
