import java.io.*;
import java.net.ServerSocket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.util.Random;
import java.util.Scanner;


public class Server {
    ArrayList<Personale> list_p = new ArrayList<>();
    ArrayList<Visitatore> list_v = new ArrayList<>();

    public synchronized void AddPersonale(Personale p){
        list_p.add(p);
    }
/*    public synchronized String RemovePersonale(String NumBadge){
        String rimozione = "";
        int i = 0;
        for(Personale p: list_p){
            var Badge=p.getNumBadge();
            if(NumBadge.equals(Badge)){
                i=1;
                list_p.remove(p);
                rimozione="Utente rimosso correttamente";
                System.out.println("Utente rimosso correttamente");
            }
        }

       if (i==0){
            System.out.println("Impossibile rimuovere utente, possibili cause: utente non presenti nella lista o numero di badge errato");
            rimozione="Impossibile rimuovere utente, possibili cause: utente non presenti nella lista o numero di badge errato";
        }
        return rimozione;
    }*/
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

    public static String generaBadge(String mansione){
        String badge="";
        int randA= (int) (Math.random()*(9999-1000));
        if(mansione.equals("Archeologo") || mansione.equals("archeologo")){
            String badgeArch= "CTDE1000AC74";
            badge=badgeArch+Integer.toString(randA);
        }
        else if(mansione.equals("Sicurezza") || mansione.equals("sicurezza")){
            String badgeSic= "CTDE1000SC89";
            badge=badgeSic+Integer.toString(randA);
        }
        else if(mansione.equals("Amministrazione") || mansione.equals("amministrazione")){
            String badgeAmm= "CTDE1000AM69";
            badge=badgeAmm+Integer.toString(randA);
        }
        else if(mansione.equals("Guida") || mansione.equals("guida")){
            String badgeGui= "CTDE1000GT15";
            badge=badgeGui+Integer.toString(randA);
        }
        else if(mansione.equals("Pulizie") || mansione.equals("pulizie")){
            String badgePul= "CTDE1000PZ35";
            badge=badgePul+Integer.toString(randA);
        }
        return badge;
    }

    public static String generaBiglietto(String tipo_visita){
        String biglietto="";
        int randB= (int) (Math.random()*(999999-100000));
        if(tipo_visita.equals("Guidata") || tipo_visita.equals("guidata")){
            biglietto="GDT654AC"+randB;
        }
        if(tipo_visita.equals("Libera") || tipo_visita.equals("libera")){
            biglietto="LBR290GJ"+randB;
        }
        return biglietto;
    }
    public synchronized String controlloBiglietto(String IDBiglietto){
        String Validita = "";
        int i = 0;
        for(Visitatore v: list_v){
            var Biglietto = v.getIDBiglietto();
            if(IDBiglietto.equals(Biglietto)){
                i=1;
                if(v.isValidita()){
                    System.out.println("Cliente accettato");
                    v.setValidita(false);
                    Validita = "Cliente accettato";
                }
                else {
                    System.out.println("Biglietto già usato");
                    Validita = "Biglietto già usato";
                }
            }

            

        }
        if (i==0){
            System.out.println("Biglietto non presente");
            Validita = "Biglietto non presente";
        }
        return Validita;
    }


    public synchronized String ingressoPersonale(String numBadge){
        String Ingresso = "";
        LocalDateTime DataIngresso = LocalDateTime.now();
        DateTimeFormatter DataIngressoFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        int i = 0;
        for(Personale p: list_p){
            var Badge = p.getNumBadge();
            if(numBadge.equals(Badge)){
                i=1;
                Ingresso = DataIngresso.format(DataIngressoFormat);
                System.out.println("Benvenuto! " + Ingresso);
                p.setOrarioIngresso(Ingresso);
            }

        }
        if (i==0){
            System.out.println("Badge non riconosciuto, reinserire!");
            Ingresso = "Badge non riconosciuto, reinserire!";
        }
        return Ingresso;
    }


    public static void main(String[] args) {
        var myserver = new Server();

        int port = Integer.parseInt(args[0]); //Cast da stringa a intero



        try {
            var serverSocket = new ServerSocket(port);
            while (true){
                System.out.println("SERVER: In attesa di connessione...");
                var client_socket=serverSocket.accept();
                System.out.println("SERVER: Accettata connessione con "+client_socket.getRemoteSocketAddress());

                var cm = new ClientManager(client_socket, myserver);
                new Thread(cm).start();
            }

        }
        catch (IOException e){
                throw new RuntimeException(e);

        }
    }
}
