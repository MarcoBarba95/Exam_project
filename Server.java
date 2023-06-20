import java.io.*;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;


public class Server {
ArrayList<Personale> list_p = new ArrayList<>();
ArrayList<Visitatore> list_v = new ArrayList<>();

public synchronized void AddPersonale(Personale p){
    list_p.add(p);
}
public synchronized void AddVisitatore(Visitatore v){
        list_v.add(v);
}
public synchronized void commandSave(String filename,String tipo_lista){

    FileOutputStream fos = null;
    ObjectOutputStream oos = null;

    try {
        fos = new FileOutputStream(filename);
        oos = new ObjectOutputStream(fos);
        if(tipo_lista.equals("LISTA_P")){
        oos.writeObject(list_p);}
        else if(tipo_lista.equals("LISTA_V")){
            oos.writeObject(list_v);
        }
        else {
            System.out.println("LISTA NON TROVATA");
        }
        oos.close();
    } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}

    public synchronized void commandLoad(String filename,String tipo_lista) {

        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(filename);
            ois = new ObjectInputStream(fis);
            if (tipo_lista.equals("LISTA_P")) {
                list_p = (ArrayList<Personale>) ois.readObject();
            } else if (tipo_lista.equals("LISTA_V")) {
                list_v = (ArrayList<Visitatore>) ois.readObject();
            } else {
                System.out.println("LISTA NON TROVATA");
            }
            ois.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }









    public synchronized ArrayList<String> getListString(String tipo_lista) {
        var people = new ArrayList<String>();
        if(tipo_lista.equals("LISTA_V")){
            for (Visitatore v: list_v) {
                people.add(v.toString());
            }
        }
        else if (tipo_lista.equals("LISTA_P")){
            for (Personale p: list_p) {
                people.add(p.toString());
            }
        }
        else {
            System.out.println("LISTA NON TROVATA");
        }
        return people;
    }


    public static void main(String[] args) {
        var myserver = new Server();

        int port = Integer.parseInt(args[0]); //Cast da stringa a intero


        try {
            var serverSocket = new ServerSocket(port);
            while (true){
                System.out.println("SERVER: Waiting for connection...");
                var client_socket=serverSocket.accept();
                System.out.println("SERVER: Accepted connection from "+client_socket.getRemoteSocketAddress());

                var cm = new ClientManager(client_socket, myserver);
                new Thread(cm).start();
            }

        }
        catch (IOException e){
                throw new RuntimeException(e);

        }
    }
}
