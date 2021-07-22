package server.requests;

public class Log_In_Request {
    private String header;
    private Long phone_number;

    public Log_In_Request(String header, Long phone_number) {
        this.header = header;
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
