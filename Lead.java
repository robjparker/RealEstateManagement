import java.io.Serializable;
import java.util.ArrayList;

public class Lead implements Serializable {
    private int id;
    private Realtor realtor;
    private String sellerEmail;
    private String sellerPhone;
    private String notes;

    public Lead(int id, Realtor realtor) {
        this.id = id;
        this.realtor = realtor;
    }

    public int getId() {
        return id;
    }

    public Realtor getRealtor() {
        return realtor;
    }

    public void setRealtor(Realtor realtor) {
        this.realtor = realtor;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }

    public String getNote() {
        return notes;
    }

    public void setNote(String notes) {
        this.notes = notes;
    }

    public String toString()
    {
        return String.format("%d, %s, %s, %s %s\n", id, realtor.toString(), sellerEmail, sellerPhone, notes);
    }
}