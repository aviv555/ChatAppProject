package Client.Requests;

public class Read_Conversation_Request {
    private final String header = "Read_Conversation";
    private long phone_number_source;
    private long phone_number_target;

    public Read_Conversation_Request(long phone_number_source, long phone_number_target) {
        this.phone_number_source = phone_number_source;
        this.phone_number_target = phone_number_target;
    }

    public String getHeader() {
        return header;
    }

    public long getPhone_number_source() {
        return phone_number_source;
    }

    public void setPhone_number_source(long phone_number_source) {
        this.phone_number_source = phone_number_source;
    }

    public long getPhone_number_target() {
        return phone_number_target;
    }

    public void setPhone_number_target(long phone_number_target) {
        this.phone_number_target = phone_number_target;
    }
}
