package server;
import com.google.gson.Gson;
import server.requests.Request;
import service.ChattingService;
import service.Controller;
import java.io.*;
import java.net.Socket;

public class HandleRequest implements Runnable {
    private Socket socket;
    private Controller controller;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public HandleRequest(Socket client, ChattingService chattingService) {
        this.controller = new Controller(chattingService);
        this.socket = client;
        try {
            out = new ObjectOutputStream(this.socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            in = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void run() {

        System.out.println("request in progress... you are on thread:" + Thread.currentThread());

        String request_as_json;
        try {
            request_as_json = (String) in.readObject(); //reading request from client
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        Request request = new Gson().fromJson(request_as_json, Request.class);

        String header = request.getHeader_and_json()[0];
        String encrypted_request = request.getHeader_and_json()[1];

        if(header.contains("Log_In")) {
            String response = controller.Log_In(encrypted_request);
            try {
                out.writeObject(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (header.contains("Send_Message")) {
            String response = controller.Send_Message(encrypted_request); //we send encrypted data to controller
            try {
                out.writeObject(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else if (header.contains("Read_Conversation")) {
            String response = controller.Read_Conversation(encrypted_request);
            try {
                out.writeObject(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("request finished in thread:" + Thread.currentThread());
    }
}

