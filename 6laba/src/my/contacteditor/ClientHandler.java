package my.contacteditor;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private int clientId;

    private ContactEditorUI ui;

    public ClientHandler(Socket socket, int clientId, ContactEditorUI ui) {

        this.socket = socket;
        this.clientId = clientId;
        this.ui = ui;

        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();

            in = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try {

            while (!socket.isClosed()) {

                Message msg = (Message) in.readObject();

                if (msg.getType() == Message.Type.PARTIAL_RESULT) {

                    System.out.println(
                            "Client " + clientId +
                            " result: " + msg.getResult() +
                            " for task: " + msg.getTaskId()
                    );

                    
                    ui.handleClientResult(
                            msg.getTaskId(),
                            msg.getResult(),
                            msg.getTableRow()
                    );
                }
            }

        } catch (Exception e) {

            System.out.println("Client disconnected: " + clientId);

        } finally {
            close();
        }
    }

    public void sendTask(Message msg) {

        try {
            synchronized (out) {
                out.writeObject(msg);
                out.flush();
            }
        } catch (IOException e) {
            System.out.println("Failed to send task to client " + clientId);
        }
    }

    private void close() {

        try {

            if (socket != null) socket.close();
            if (in != null) in.close();
            if (out != null) out.close();

            System.out.println("Client handler closed: " + clientId);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}