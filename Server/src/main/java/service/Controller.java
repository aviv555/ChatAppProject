package service;
import com.google.gson.Gson;
import dm.Conversation;
import dm.Message;
import dm.User_In_Client;
import dm.User_In_Server;
import server.Responses.Read_Conversation_Response;
import server.Responses.Send_Message_Response;
import server.requests.Log_In_Request;
import server.requests.Read_Conversation_Request;
import server.requests.Send_Message_Request;
import java.util.List;

public class Controller {
    private final ChattingService service;
    public Controller(ChattingService service) {this.service = service;}

    private void update_source_data(List<User_In_Server> users, long source, long target, String message) {
        boolean user_found = false;
        //updating source data
        for(User_In_Server user : users)
        {
            if(user.getPhone_number() == source) //we found our source
            {
                user_found = true;
                for(Conversation session : user.getSessions())
                {
                    if(session.getOther().getPhone_number() == target) //found session with target
                    {
                        session.getSession().add(new Message("You", message));
                        break;
                    }
                }
            }
            if (user_found) break;
        }
    }

    private void update_target_data(List<User_In_Server> users, long source, long target, String message) {
        boolean user_exist_flag = false;

        for(User_In_Server user : users)
        {
            if(user.getPhone_number() == target) //we found our target
            {
                user_exist_flag = true;
                for(Conversation session : user.getSessions())
                {
                    if(session.getOther().getPhone_number() == source) //found session with source
                    {
                        session.getSession().add(new Message(session.getOther().getName(), message));//we found the session
                        break;
                    }
                }
            }
            if (user_exist_flag) break;
        }
    }

    public String Log_In(String encrypted_Log_In_req) {
        String encrypted = this.service.getAlg().decrypt(encrypted_Log_In_req);
        Log_In_Request request = new Gson().fromJson(encrypted, Log_In_Request.class);

        List<User_In_Server> users;
        users = this.service.getUser_db_handler().read();

        for(User_In_Server user_in_server : users) {
            if (user_in_server.getPhone_number() == request.getPhone_number()) //We found the user requesting to log in
            {
                User_In_Client user_in_client = new User_In_Client(user_in_server);
                String user_in_client_as_json = new Gson().toJson(user_in_client);
                return this.service.getAlg().encrypt(user_in_client_as_json);
            }
        }
        return null; //phone number was incorrect and the log failed
    }

     public String Send_Message(String encrypted_send_message_req) {

            synchronized (Controller.class) {
            String encrypted = this.service.getAlg().decrypt(encrypted_send_message_req);
            Send_Message_Request request = new Gson().fromJson(encrypted, Send_Message_Request.class);

            long source = request.getPhone_number_source();
            long target = request.getPhone_number_target();
            String message = request.getMessage();

            List<User_In_Server> users;
            users = this.service.getUser_db_handler().read();

            update_source_data(users, source, target, message);
            update_target_data(users, source, target, message);

            this.service.getUser_db_handler().write_lst(users);

            Send_Message_Response response = new Send_Message_Response(true);

            return new Gson().toJson(response);
        }
    }


    public String Read_Conversation(String encrypted_read_conversation_req) {
        String encrypted = this.service.getAlg().decrypt(encrypted_read_conversation_req);
        Read_Conversation_Request request = new Gson().fromJson(encrypted, Read_Conversation_Request.class);

        long source = request.getPhone_number_source();
        long target = request.getPhone_number_target();

        List<User_In_Server> users;
        users = this.service.getUser_db_handler().read();

        for(User_In_Server user : users)
        {
            if(user.getPhone_number() == source) //we found our source
            {
                for(Conversation conversation : user.getSessions())
                {
                    if(conversation.getOther().getPhone_number() == target) //found session with target
                    {
                        String res = Conversation.conversation_to_string(conversation.getSession());
                        Read_Conversation_Response response = new Read_Conversation_Response(res);
                        String response_as_json = new Gson().toJson(response);
                        return this.service.getAlg().encrypt(response_as_json);
                    }
                }
            }
        }
        return null;
    }
}
