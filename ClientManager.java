import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientManager  {
Socket client_socket;
Server myserver;
public ClientManager(Socket client, Server server){
    System.out.println("Creazione di un nuovo gestore di client!");
    this.myserver = server;
    this.client_socket = client;
}

public void start(){
    Scanner sc = null;

    try {
        sc = new Scanner(client_socket.getInputStream());
        var pw = new PrintWriter(client_socket.getOutputStream());


        String scelta_lista = "";
        String received_command = "";

        while (!scelta_lista.equals("CMD_CLOSE")){
            scelta_lista = sc.nextLine();
            System.out.println("Gestione lista: " + scelta_lista);

            if(scelta_lista.equals("LISTA_P")){
                received_command = "";
                while (!received_command.equals("CMD_QUIT")) {
                    received_command = sc.nextLine();

                    System.out.println("Comando ricevuto " + received_command);
                    switch (received_command){

                        case "CMD_ADD_PERSONALE":
                            var nome = sc.nextLine();
                            var cognome = sc.nextLine();
                            var eta = sc.nextLine();
                            var sesso = sc.nextLine();
                            var NumBadge = sc.nextLine();
                            var mansione = sc.nextLine();
                            var end_command = sc.nextLine();
                            if(!end_command.equals("END_CMD")){
                                System.out.println("Format error");
                            }
                            System.out.println("Membro del personale aggiunto: " +nome + " " + cognome + " " + eta + " " + sesso + " " + NumBadge + " " + mansione);
                            var personale = new Personale(nome, cognome, Integer.parseInt(eta),sesso, Integer.parseInt(NumBadge), mansione);
                            myserver.list_p.add(personale);
                            break;

                        case "CMD_SAVE_PERSONALE":
                            System.out.println("Saving list...");
                            String filename=sc.nextLine();
                            end_command=sc.nextLine();
                            if(!end_command.equals("END_CMD")){
                                System.out.println("Format error");
                            }
                            var fos=new FileOutputStream(filename);
                            var oos=new ObjectOutputStream(fos);
                            oos.writeObject(myserver.list_p);
                            oos.close();
                            break;
                        case "CMD_MOSTRA_PERSONALE":
                            end_command=sc.nextLine();
                            if(!end_command.equals("END_CMD")){
                                System.out.println("Format error");
                            }
                            for (Personale p: myserver.list_p){
                                pw.println(p);
                                pw.flush();
                            }
                            pw.println("FINE_LISTA");
                            pw.flush();
                            break;
                        case "CMD_LOAD_PERSONALE":
                            System.out.println("Caricamento lista...");
                            String file_to_load=sc.nextLine();
                            end_command=sc.nextLine();
                            if(!end_command.equals("END_CMD")){
                                System.out.println("Format error");
                            }
                            var fis=new FileInputStream(file_to_load);
                            var ois=new ObjectInputStream(fis);

                            try {
                                myserver.list_p=(ArrayList<Personale>) ois.readObject();
                            } catch (ClassNotFoundException e) {
                                System.out.println("Impossibile caricare la lista");
                            }
                            ois.close();
                            break;
                        case "CMD_QUIT":
                            System.out.println("Ritorno alla selezione lista...");
                            scelta_lista = "";

                            break;
                        default:
                            if(!received_command.isBlank()) {
                                System.out.println("Comando sconosciuto");
                            }
                    }
                }
            }
            else {
                received_command = "";
                while (!received_command.equals("CMD_QUIT")) {
                    received_command = sc.nextLine();

                    System.out.println("Comando ricevuto " + received_command);
                    switch (received_command){

                        case "CMD_ADD_VISITATORE":
                            var nome = sc.nextLine();
                            var cognome = sc.nextLine();
                            var eta = sc.nextLine();
                            var sesso = sc.nextLine();
                            var NumDoc = sc.nextLine();
                            var IDBiglietto = sc.nextLine();
                            var TipologiaVisita = sc.nextLine();
                            var end_command = sc.nextLine();
                            if(!end_command.equals("END_CMD")){
                                System.out.println("Format error");
                            }
                            System.out.println("Visitatore aggiunto: " +nome + " " + cognome + " " + eta + " " + sesso + " " + NumDoc + " " + IDBiglietto + " " + TipologiaVisita);
                            var visitatore = new Visitatore(nome, cognome, Integer.parseInt(eta),sesso, NumDoc, IDBiglietto, TipologiaVisita);
                            myserver.list_v.add(visitatore);
                            break;

                        case "CMD_SAVE_VISITATORE":
                            System.out.println("Saving list...");
                            String filename=sc.nextLine();
                            end_command=sc.nextLine();
                            if(!end_command.equals("END_CMD")){
                                System.out.println("Format error");
                            }
                            var fos=new FileOutputStream(filename);
                            var oos=new ObjectOutputStream(fos);
                            oos.writeObject(myserver.list_v);
                            oos.close();
                            break;
                        case "CMD_MOSTRA_VISITATORE":
                            end_command=sc.nextLine();
                            if(!end_command.equals("END_CMD")){
                                System.out.println("Format error");
                            }
                            for (Visitatore v : myserver.list_v){
                                pw.println(v);
                                pw.flush();
                            }
                            pw.println("FINE_LISTA");
                            pw.flush();
                            break;
                        case "CMD_LOAD_VISITATORE":
                            System.out.println("Caricamento lista...");
                            String file_to_load=sc.nextLine();
                            end_command=sc.nextLine();
                            if(!end_command.equals("END_CMD")){
                                System.out.println("Format error");
                            }
                            var fis=new FileInputStream(file_to_load);
                            var ois=new ObjectInputStream(fis);

                            try {
                                myserver.list_v=(ArrayList<Visitatore>) ois.readObject();
                            } catch (ClassNotFoundException e) {
                                System.out.println("Impossibile caricare la lista");
                            }
                            ois.close();
                            break;
                        case "CMD_QUIT":
                            System.out.println("Ritorno alla selezione lista...");
                            scelta_lista = "";
                            break;
                        default:
                            if(!received_command.isBlank()) {
                                System.out.println("Comando sconosciuto");
                            }
                    }
                }
            }
        }
    }
        catch (IOException e) {
        throw new RuntimeException(e);
    }
}


}
