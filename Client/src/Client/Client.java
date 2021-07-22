package Client;
import Client.Requests.Log_In_Request;
import Client.Requests.Read_Conversation_Request;
import Client.Requests.Request;
import Client.Requests.Send_Message_Request;
import Client.Responses.Read_Conversation_Response;
import Client.Responses.Send_Message_Response;
import com.google.gson.Gson;
import com.hit.algorithm.AES;
import Client.dm.User_In_Client;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    User_In_Client user;
    Socket myServer;
    ObjectOutputStream out;
    ObjectInputStream in;

    private boolean set_connection_output_input() throws IOException {
        try {
            myServer = new Socket(InetAddress.getLocalHost(), 1111);
        } catch (IOException e) {
            return false;
        }
        out = new ObjectOutputStream(myServer.getOutputStream());
        in = new ObjectInputStream(myServer.getInputStream());
        return true;
    }

    public User_In_Client log_in_request(long phone_number) throws IOException, ClassNotFoundException {

        if(!set_connection_output_input()) {
            return null;
        }

        Log_In_Request request = new Log_In_Request(phone_number);

        String request_as_json = new Gson().toJson(request);
        Request request_and_header = new Request(request.getHeader(), new AES().encrypt(request_as_json));
        request_as_json = new Gson().toJson(request_and_header);

        out.writeObject(request_as_json);
        String response = (String) in.readObject();

        String decrypted_response = new AES().decrypt(response);
        user = new Gson().fromJson(decrypted_response, User_In_Client.class);
        return user;
    }
    public boolean send_message_request(long source, long target, String message) throws IOException, ClassNotFoundException {

       if(!set_connection_output_input()) {
           return false;
       }

       Send_Message_Request request = new Send_Message_Request(source, target, message);

       String request_as_json = new Gson().toJson(request);
       Request request_and_header = new Request(request.getHeader(), new AES().encrypt(request_as_json));
       request_as_json = new Gson().toJson(request_and_header);

       out.writeObject(request_as_json);
       String message_from_server = (String) in.readObject();

       Send_Message_Response response = new Gson().fromJson(message_from_server, Send_Message_Response.class);
       return response.isResponse();
    }

    public String read_conversation_request(long source, long target) throws IOException, ClassNotFoundException {

        if(!set_connection_output_input()) {
            return null;
        }

        Read_Conversation_Request request = new Read_Conversation_Request(source, target);

        String request_as_json = new Gson().toJson(request);
        Request request_and_header = new Request(request.getHeader(), new AES().encrypt(request_as_json));
        request_as_json = new Gson().toJson(request_and_header);

        out.writeObject(request_as_json);
        String response_from_server = (String) in.readObject();

        String decrypted_response = new AES().decrypt(response_from_server);
        Read_Conversation_Response response = new Gson().fromJson(decrypted_response, Read_Conversation_Response.class);

        return response.getSession();
    }
}

