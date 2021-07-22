package Controller;
import Client.Client;
import Client.dm.User_In_Client;
import java.io.IOException;

public class Controller {
    Client client = new Client();

    public User_In_Client log_in_request_to_server(long phone_number) throws IOException, ClassNotFoundException {
        return client.log_in_request(phone_number);
    }

    public boolean send_message_request_to_server(long source, long target, String message) throws IOException, ClassNotFoundException {
        return client.send_message_request(source, target, message);
    }

    public String read_conversation_request_to_server(long source, long target) throws IOException, ClassNotFoundException {
        return client.read_conversation_request(source, target);
    }
}
