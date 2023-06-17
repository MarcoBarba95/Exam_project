import java.io.*;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Scanner;


public class Server {
ArrayList<Personale> list_p = new ArrayList<>();
ArrayList<Visitatore> list_v = new ArrayList<>();

    public static void main(String[] args) {
        var myserver = new Server();

        int port = Integer.parseInt(args[0]); //Cast da stringa a intero


        try {
            var serverSocket = new ServerSocket(port);
            System.out.println("SERVER: Waiting for connection...");
            var client_socket=serverSocket.accept();
            System.out.println("SERVER: Accepted connection from "+client_socket.getRemoteSocketAddress());

            var cm = new ClientManager(client_socket, myserver);
            cm.start();
        }
        catch (IOException e){
                throw new RuntimeException(e);

        }
    }
}
