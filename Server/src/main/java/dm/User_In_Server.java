package dm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User_In_Server implements Serializable {
    private String name;
    private long phone_number;
    private List<Contact> contacts;
    private List<Conversation> sessions;

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Conversation> getSessions() {
        return sessions;
    }

    public void setSessions(List<Conversation> sessions) {
        this.sessions = sessions;
    }

    public User_In_Server(String name, long phone_number) {
        this.name = name;
        this.phone_number = phone_number;
        this.contacts = new ArrayList<>();
        this.sessions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }

    public void add_contact(String name, long phone_number) {
        Contact new_contact = new Contact(name, phone_number);
        this.contacts.add(new_contact);
        this.sessions.add(new Conversation(new_contact));
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", phone_number=" + phone_number +
                '}';
    }
}
