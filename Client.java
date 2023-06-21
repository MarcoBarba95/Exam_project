import java.io.IOException;
import java.net.Socket;
import java.io.PrintWriter;
import java.util.Scanner;

public class Client  {
    public static void main(String[] args) {
        String ip = args[0];
        int port = Integer.parseInt(args[1]);
        try {
            var socket = new Socket(ip, port);
            System.out.println("Connesso");
            var is=socket.getInputStream();
            var os=socket.getOutputStream();

            var scanner=new Scanner(is);
            var pw=new PrintWriter(os);

            String seleziona_lista = "";
            String seleziona_cmd = "";
            var input = new Scanner(System.in);

            while(!seleziona_lista.equals("c")){
                System.out.println("------------------------------------------------------------");
                System.out.println("Procedura di selezione della lista");
                System.out.println("p - Gestione lista personale");
                System.out.println("v - Gestione lista visitatori");
                System.out.println("c - Chiudi il programma");
                System.out.println("-------------------------------------------------------------");
                System.out.println("Inserisci scelta: ");
                seleziona_lista = input.nextLine();

                switch (seleziona_lista){
                    case "p":
                        System.out.println("Gestione lista personale");
                        pw.println("LISTA_P");
                        pw.flush();
                        seleziona_cmd = "";
                        while (!seleziona_cmd.equals("q")){
                            System.out.println("------------------------------------------------------------");
                            System.out.println("a - Aggiungi membro del personale");
                            System.out.println("l - Mostra lista membri del personale");
                            System.out.println("s - Salva lista membri del personale");
                            System.out.println("c - Carica lista membri del personale");
                            System.out.println("q - Quit");
                            System.out.println("-------------------------------------------------------------");
                            System.out.println("Seleziona comando:");
                            seleziona_cmd = input.nextLine();
                            switch (seleziona_cmd){
                                case "a":
                                    System.out.println("Inserisci nome: ");
                                    var nome = input.nextLine();
                                    System.out.println("Inserisci cognome:");
                                    var cognome = input.nextLine();
                                    System.out.println("Inserisci età:");
                                    var eta = input.nextLine();
                                    System.out.println("Inserisci sesso:");
                                    var sesso = input.nextLine();
                                    System.out.println("Inserisci Numero di Badge:");
                                    var NumBadge = input.nextLine();
                                    System.out.println("Inserisci mansione:");
                                    var mansione = input.nextLine();

                                    pw.println("CMD_ADD_PERSONALE");
                                    pw.flush();
                                    pw.println(nome);
                                    pw.flush();
                                    pw.println(cognome);
                                    pw.flush();
                                    pw.println(eta);
                                    pw.flush();
                                    pw.println(sesso);
                                    pw.flush();
                                    pw.println(NumBadge);
                                    pw.flush();
                                    pw.println(mansione);
                                    pw.flush();
                                    pw.println("END_CMD");
                                    pw.flush();
                                    break;
                                case "l":
                                    pw.println("CMD_MOSTRA_PERSONALE");
                                    pw.flush();
                                    pw.println("END_CMD");
                                    pw.flush();

                                    boolean continue_list=true;
                                    System.out.println("----------------------------------------------------");
                                    System.out.println("Lista del personale:");
                                    System.out.println("----------------------------------------------------");
                                    while(continue_list){
                                        String line=scanner.nextLine();
                                        if(line.equals("FINE_LISTA")){
                                            continue_list=false;
                                        }else{
                                            System.out.println(line);
                                            System.out.println("-----------------------------------------------------");
                                        }
                                    }
                                    System.out.println("-----------------------------------------------------");
                                    break;
                                case "s":
                                    System.out.println("Inserisci nome file: ");
                                    var name = input.nextLine();
                                    pw.println("CMD_SAVE_PERSONALE");
                                    pw.flush();
                                    pw.println(name);
                                    pw.flush();
                                    pw.println("END_CMD");
                                    pw.flush();
                                    break;
                                case "c":
                                    System.out.println("Inserisci nome file: ");
                                    name = input.nextLine();
                                    pw.println("CMD_LOAD_PERSONALE");
                                    pw.flush();
                                    pw.println(name);
                                    pw.flush();
                                    pw.println("END_CMD");
                                    pw.flush();
                                    break;
                                case "q":
                                    pw.println("CMD_QUIT");
                                    pw.flush();
                                    break;
                                default:
                                    System.out.println("Comando non riconosciuto");
                                    break;
                            }
                        }
                        break;
                    case "v":
                        System.out.println("Gestione lista visitatori");
                        pw.println("LISTA_V");
                        pw.flush();
                        seleziona_cmd = "";
                        while (!seleziona_cmd.equals("q")){
                            System.out.println("------------------------------------------------------------");
                            System.out.println("a - Aggiungi visitatore");
                            System.out.println("l - Mostra lista visitatori");
                            System.out.println("s - Salva lista visitatori");
                            System.out.println("c - Carica lista visitatori");
                            System.out.println("q - Quit");
                            System.out.println("-------------------------------------------------------------");
                            System.out.println("Seleziona comando:");
                            seleziona_cmd = input.nextLine();
                            switch (seleziona_cmd){
                                case "a":
                                    System.out.println("Inserisci nome: ");
                                    var nome = input.nextLine();
                                    System.out.println("Inserisci cognome:");
                                    var cognome = input.nextLine();
                                    System.out.println("Inserisci età:");
                                    var eta = input.nextLine();
                                    System.out.println("Inserisci sesso:");
                                    var sesso = input.nextLine();
                                    System.out.println("Inserisci ID Biglietto:");
                                    var IDBiglietto = input.nextLine();
                                    System.out.println("Inserisci tipologia biglietto: ");
                                    var TipologiaBiglietto = input.nextLine();

                                    pw.println("CMD_ADD_VISITATORE");
                                    pw.flush();
                                    pw.println(nome);
                                    pw.flush();
                                    pw.println(cognome);
                                    pw.flush();
                                    pw.println(eta);
                                    pw.flush();
                                    pw.println(sesso);
                                    pw.flush();
                                    pw.println(IDBiglietto);
                                    pw.flush();
                                    pw.println(TipologiaBiglietto);
                                    pw.flush();
                                    pw.println("END_CMD");
                                    pw.flush();
                                    break;
                                case "l":
                                    pw.println("CMD_MOSTRA_VISITATORE");
                                    pw.flush();
                                    pw.println("END_CMD");
                                    pw.flush();

                                    boolean continue_list=true;
                                    System.out.println("----------------------------------------------------");
                                    System.out.println("Lista dei visitatori:");
                                    System.out.println("----------------------------------------------------");
                                    while(continue_list){
                                        String line=scanner.nextLine();
                                        if(line.equals("FINE_LISTA")){
                                            continue_list=false;
                                        }else{
                                            System.out.println(line);
                                            System.out.println("-----------------------------------------------------");
                                        }
                                    }
                                    System.out.println("-----------------------------------------------------");
                                    break;
                                case "s":
                                    System.out.println("Inserisci nome file: ");
                                    var name = input.nextLine();
                                    pw.println("CMD_SAVE_VISITATORE");
                                    pw.flush();
                                    pw.println(name);
                                    pw.flush();
                                    pw.println("END_CMD");
                                    pw.flush();
                                    break;
                                case "c":
                                    System.out.println("Inserisci nome file: ");
                                    name = input.nextLine();
                                    pw.println("CMD_LOAD_VISITATORE");
                                    pw.flush();
                                    pw.println(name);
                                    pw.flush();
                                    pw.println("END_CMD");
                                    pw.flush();
                                    break;
                                case "q":
                                    pw.println("CMD_QUIT");
                                    pw.flush();
                                    break;
                                default:
                                    System.out.println("Comando non riconosciuto");
                                    break;
                            }
                        }
                        break;
                    case "c":
                        System.out.println("Chiusura programma...");
                        pw.println("CMD_CLOSE");
                        pw.flush();
                        break;
                }
            }
        }
        catch (IOException e){
            System.out.println("Impossibile connettersi a " + ip);
            System.exit(-1);
        }
    }
}
