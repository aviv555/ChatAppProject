package server.Responses;

import java.io.Serializable;

public class Send_Message_Response implements Serializable {
    private boolean response;

    public Send_Message_Response(boolean response) {
        this.response = response;
    }

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Send_Message_Response{" +
                "response=" + response +
                '}';
    }
}