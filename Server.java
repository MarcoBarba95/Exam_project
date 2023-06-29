import java.io.*;
import java.net.ServerSocket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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
        var persone = new ArrayList<String>();
        if(tipo_lista.equals("LISTA_V")){
            for (Visitatore v: list_v) {
                persone.add(v.toString());
            }
        }
        else if (tipo_lista.equals("LISTA_P")){
            for (Personale p: list_p) {
                persone.add(p.toString());
            }
        }
        else {
            System.out.println("LISTA NON TROVATA");
        }
        return persone;
    }

    public static String generaBadge(String mansione){
        String badge="";
        int rand= (int) (Math.random()*(9999-1000));
        if(mansione.equals("Archeologo") || mansione.equals("archeologo")){
            String badgeArch= "CTDE1000AC74";
            badge=badgeArch+Integer.toString(rand);
        }
        else if(mansione.equals("Sicurezza") || mansione.equals("sicurezza")){
            String badgeSic= "CTDE1000SC89";
            badge=badgeSic+Integer.toString(rand);
        }
        else if(mansione.equals("Amministrazione") || mansione.equals("amministrazione")){
            String badgeAmm= "CTDE1000AM69";
            badge=badgeAmm+Integer.toString(rand);
        }
        else if(mansione.equals("Guida") || mansione.equals("guida")){
            String badgeGui= "CTDE1000GT15";
            badge=badgeGui+Integer.toString(rand);
        }
        else if(mansione.equals("Pulizie") || mansione.equals("pulizie")){
            String badgePul= "CTDE1000PZ35";
            badge=badgePul+Integer.toString(rand);
        }
        return badge;
    }
    
    public static String generaBiglietto(String tipo_visita){
        String biglietto="";
        int rand= (int) (Math.random()*(999999-100000));
        if(tipo_visita.equals("Guidata") || tipo_visita.equals("guidata")){
            biglietto="GDT654AC"+rand;
        }
        if(tipo_visita.equals("Libera") || tipo_visita.equals("libera")){
            biglietto="LBR290GJ"+rand;
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
        DateTimeFormatter DataIngressoFormattata = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        int i = 0;
        for(Personale p: list_p){
            var Badge = p.getNumBadge();
            if(numBadge.equals(Badge) && (!p.isStatus())){
                i=1;
                Ingresso = DataIngresso.format(DataIngressoFormattata);
                System.out.println("Badge numero "+ numBadge + " timbra all'orario: " + Ingresso);
                p.setOrarioIngresso(Ingresso);
                p.setStatus(true);
            }

        }
        if (i==0){
            System.out.println("Badge non riconosciuto");
            Ingresso = "Badge non riconosciuto, reinserire!";
        }
        return Ingresso;
    }

    public synchronized String uscitaPersonale(String numBadge){
        String Uscita = "";
        LocalDateTime DataUscita = LocalDateTime.now();
        DateTimeFormatter DataUscitaFormattata = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        int i = 0;
        for(Personale p: list_p){
            var Badge = p.getNumBadge();
            if(numBadge.equals(Badge) && p.isStatus()){
                i=1;
                Uscita = DataUscita.format(DataUscitaFormattata);
                System.out.println("Badge numero "+ numBadge + " timbra l'uscita all'orario: " + Uscita);
                p.setOrarioUscita(Uscita);
                p.setStatus(false);
            }
        }
        if (i==0){
            System.out.println("Badge non riconosciuto");
            Uscita = "Badge non riconosciuto, reinserire!";
        }
        return Uscita;
    }
    
    public synchronized long contaMinuti(String numBadge){
        long totMinInMilSec = 0;
        long totMinInMinutes = 0;
        for(Personale p: list_p){
            var Badge = p.getNumBadge();
            if(numBadge.equals(Badge)){
                String uscitaInString = p.getOrarioUscita();
                String ingressoInString =  p.getOrarioIngresso();
                try {
                    Date uscitaInDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(uscitaInString);
                    Date ingressoInDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(ingressoInString);
                    totMinInMilSec = (uscitaInDate.getTime() - ingressoInDate.getTime());
                    totMinInMinutes = TimeUnit.MILLISECONDS.toMinutes(totMinInMilSec);
                    totMinInMinutes = totMinInMinutes + p.getTotMinuti();
                    p.setTotMinuti(totMinInMinutes);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return totMinInMinutes;
    }
    
    public static void main(String[] args) {
        var myserver = new Server();
        int port = Integer.parseInt(args[0]);
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