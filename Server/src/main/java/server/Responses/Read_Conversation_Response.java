package server.Responses;
import java.io.Serializable;

public class Read_Conversation_Response implements Serializable {
    private String session;

    public Read_Conversation_Response(String session) {
        this.session = session;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }


    @Override
    public String toString() {
        return "Read_Conversation_Response{" +
                "session=" + session +
                '}';
    }
}