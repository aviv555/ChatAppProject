package Client.dm;
import java.util.HashMap;

public class User_In_Client {
    private long phone_number;
    private HashMap<String,Long> contacts;

    public User_In_Client() {
        this.contacts = new HashMap<>();
    }

    public User_In_Client(long phone_number) {
        this.phone_number = phone_number;
        this.contacts = new HashMap<>();
    }

    public String contacts_as_string() {
        StringBuilder result = new StringBuilder();
        this.contacts.forEach((k, v) -> {
            result.append(k).append("\n");
        });
        return result.toString();
    }
    public long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }


    public HashMap<String, Long> getContacts() {
        return contacts;
    }

    public void setContacts(HashMap<String, Long> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "User_In_Client{" +
                "phone_number=" + phone_number +
                ", contacts=" + contacts +
                '}';
    }
}


