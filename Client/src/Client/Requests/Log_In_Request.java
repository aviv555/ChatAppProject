package Client.Requests;

public class Log_In_Request {
    private String header = "Log_In";
    private Long phone_number;

    public Log_In_Request(long phone_number) {
        this.phone_number = phone_number;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(Long phone_number) {
        this.phone_number = phone_number;
    }
}
