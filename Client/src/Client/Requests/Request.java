package Client.Requests;

import java.io.Serializable;
import java.util.Arrays;

public class Request implements Serializable {
    String[] header_and_json;

    public Request(String[] header_and_json) {
        this.header_and_json = header_and_json;
    }

    public Request(String header, String request_as_json) {
        this.header_and_json = new String[2];
        this.header_and_json[0] = header;
        this.header_and_json[1] = request_as_json;
    }

    public String[] getHeader_and_json() {
        return header_and_json;
    }

    public void setHeader_and_json(String[] header_and_json) {
        this.header_and_json = header_and_json;
    }

    @Override
    public String toString() {
        return "Model.Requests.Request{" +
                "header_and_json=" + Arrays.toString(header_and_json) +
                '}';
    }
}
