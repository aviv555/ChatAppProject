package server.requests;

public class Request {
    String[] header_and_json;

    public Request(String[] header_and_json) {
        this.header_and_json = header_and_json;
    }

    public String[] getHeader_and_json() {
        return header_and_json;
    }

    public void setHeader_and_json(String[] header_and_json) {
        this.header_and_json = header_and_json;
    }
}
