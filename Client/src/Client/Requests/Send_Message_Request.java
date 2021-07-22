package Client.Requests;

import java.io.Serializable;

public class Send_Message_Request implements Serializable {
    private final String header = "Send_Message";
    private long phone_number_source;
    private long phone_number_target;
    private String message;

    public Send_Message_Request(long phone_number_source, long phone_number_target, String message) {
        this.phone_number_source = phone_number_source;
        this.phone_number_target = phone_number_target;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Model.Requests.Send_Message_Request{" +
                "header='" + header + '\'' +
                ", phone_number_source=" + phone_number_source +
                ", phone_number_target=" + phone_number_target +
                ", message='" + message + '\'' +
                '}';
    }
}
