package Client.dm;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Conversation implements Serializable {
    private Contact other;
    private List<Message> session;

    public Conversation(Contact other) {
        this.other = other;
        this.session = new ArrayList<>();
    }

    public Contact getOther() {
        return other;
    }

    public void setOther(Contact other) {
        this.other = other;
    }

    public List<Message> getSession() {
        return session;
    }

    public void setSession(List<Message> session) {
        this.session = session;
    }

    public static String conversation_to_string(List<Message> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Message message : list) {
            stringBuilder.append(message.getName()).append(":").append(message.getMessage()).append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return "Model.dm.Conversation{" +
                "session=" + session +
                '}';
    }
}



