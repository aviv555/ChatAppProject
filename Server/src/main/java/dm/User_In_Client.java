package dm;

import java.util.HashMap;

public class User_In_Client {
    private long phone_number;
    private final HashMap<String, Long> contacts;
    private Conversation conversation;

    public User_In_Client(long phone_number) {
        this.phone_number = phone_number;
        this.contacts = new HashMap<>();
    }

    public User_In_Client(User_In_Server user_in_server) {
        this.phone_number = user_in_server.getPhone_number();
        this.contacts = new HashMap<>();

        for (Contact contact : user_in_server.getContacts()) {
            this.contacts.put(contact.getName(), contact.getPhone_number());
        }
    }

    public long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }
}


