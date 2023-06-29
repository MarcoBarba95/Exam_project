import java.io.IOException;
import java.net.Socket;
import java.io.PrintWriter;
import java.util.Scanner;

public class Client  {
    public static void main(String[] args) {
        String ip = args[0];
        int port = Integer.parseInt(args[1]);
        var Badge = "";
        try {
            var socket = new Socket(ip, port);
            System.out.println("Connesso");
            var is=socket.getInputStream();
            var os=socket.getOutputStream();
            var scanner=new Scanner(is);
            var pw=new PrintWriter(os);
            String sceltaOpzione = "";
            String inputString = "";
            var input = new Scanner(System.in);
            while(!sceltaOpzione.equals("c")){
                System.out.println("------------------------------------------------------------");
                System.out.println("Selezionare il tipo di opzione:");
                System.out.println("p - Gestione lista del personale");
                System.out.println("v - Gestione lista visitatori");
                System.out.println("b - Biglietteria");
                System.out.println("i - Ingresso del personale");
                System.out.println("u - Uscita del personale");
                System.out.println("c - Chiudi il programma");
                System.out.println("-------------------------------------------------------------");
                System.out.println("Inserisci scelta: ");
                sceltaOpzione = input.nextLine();
                switch (sceltaOpzione){
                    case "p":
                        System.out.println("Gestione lista del personale");
                        pw.println("LISTA_P");
                        pw.flush();
                        inputString = "";
                        while (!inputString.equals("q")){
                            System.out.println("------------------------------------------------------------");
                            System.out.println("a - Aggiungi membro del personale");
                            System.out.println("r - Rimuovi membro del personale");
                            System.out.println("l - Mostra lista membri del personale");
                            System.out.println("s - Salva lista membri del personale");
                            System.out.println("c - Carica lista membri del personale");
                            System.out.println("q - Quit");
                            System.out.println("-------------------------------------------------------------");
                            System.out.println("Seleziona comando:");
                            inputString = input.nextLine();
                            switch (inputString){
                                case "a":
                                    System.out.println("Inserisci nome: ");
                                    var nome = input.nextLine();
                                    System.out.println("Inserisci cognome:");
                                    var cognome = input.nextLine();
                                    System.out.println("Inserisci età:");
                                    var eta = input.nextLine();
                                    System.out.println("Inserisci sesso:");
                                    var sesso = input.nextLine();
                                    System.out.println("Inserisci mansione (Archeologo, Sicurezza, Amministrazione, Guida, Pulizie):");
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
                                    pw.println(mansione);
                                    pw.flush();
                                    pw.println("END_CMD");
                                    pw.flush();
                                    var conferma=scanner.nextLine();
                                    System.out.println(conferma);
                                    break;
                                case "r":
                                    System.out.println("Inserire numero di Badge da eliminare:");
                                    pw.println("CMD_REMOVE_PERSONALE");
                                    pw.flush();
                                    Badge=input.nextLine();
                                    pw.println(Badge);
                                    pw.flush();
                                    pw.println("END_CMD");
                                    pw.flush();
                                    var rimozione = scanner.nextLine();
                                    if(rimozione.equals("Utente rimosso correttamente")){
                                        System.out.println("Utente rimosso correttamente");
                                    }
                                    else if(rimozione.equals("Impossibile rimuovere utente, possibili cause: utente non presente nella lista o numero di badge errato")){
                                        System.out.println("Impossibile rimuovere utente, possibili cause: utente non presente nella lista o numero di badge errato");
                                    }
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
                        inputString = "";
                        while (!inputString.equals("q")){
                            System.out.println("------------------------------------------------------------");
                            System.out.println("a - Aggiungi visitatore");
                            System.out.println("r - Rimuovi visitatore");
                            System.out.println("l - Mostra lista visitatori");
                            System.out.println("s - Salva lista visitatori");
                            System.out.println("c - Carica lista visitatori");
                            System.out.println("q - Quit");
                            System.out.println("-------------------------------------------------------------");
                            System.out.println("Seleziona comando:");
                            inputString = input.nextLine();
                            switch (inputString){
                                case "a":
                                    System.out.println("Inserisci nome: ");
                                    var nome = input.nextLine();
                                    System.out.println("Inserisci cognome:");
                                    var cognome = input.nextLine();
                                    System.out.println("Inserisci età:");
                                    var eta = input.nextLine();
                                    System.out.println("Inserisci sesso:");
                                    var sesso = input.nextLine();
                                    System.out.println("Inserisci tipologia biglietto - Guidata o Libera: ");
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
                                    pw.println(TipologiaBiglietto);
                                    pw.flush();
                                    pw.println("END_CMD");
                                    pw.flush();
                                    var conferma=scanner.nextLine();
                                    System.out.println(conferma);
                                    break;
                                case "r":
                                    System.out.println("Inserire numero di Biglietto da eliminare:");
                                    pw.println("CMD_REMOVE_VISITATORE");
                                    pw.flush();
                                    var biglietto=input.nextLine();
                                    pw.println(biglietto);
                                    pw.flush();
                                    pw.println("END_CMD");
                                    pw.flush();
                                    var rimozione = scanner.nextLine();
                                    if(rimozione.equals("Utente rimosso correttamente")){
                                        System.out.println("Utente rimosso correttamente");
                                    }
                                    else if(rimozione.equals("Impossibile rimuovere utente, possibili cause: utente non presente nella lista o ID Biglietto errato")){
                                        System.out.println("Impossibile rimuovere utente, possibili cause: utente non presente nella lista o ID Biglietto errato");
                                    }
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
                    case "b":
                        System.out.println("Inserire numero biglietto: ");
                        var IDBiglietto = input.nextLine();
                        pw.println("BIGLIETTERIA");
                        pw.flush();
                        pw.println(IDBiglietto);
                        pw.flush();
                        pw.println("END_CMD");
                        pw.flush();
                        var ticket = scanner.nextLine();
                        if(ticket.equals("Cliente accettato")){
                            System.out.println("Benvenuto!");
                        }
                        else if(ticket.equals("Biglietto già usato")){
                            System.out.println("Hai già usato il tuo biglietto!");
                        }
                        else if(ticket.equals("Biglietto non presente")){
                            System.out.println("Biglietto non presente in lista");
                        }
                        break;
                    case "i":
                        System.out.println("Inserire numero di badge per timbrare l'ingresso:");
                        Badge=input.nextLine();
                        pw.println("IN_PERS");
                        pw.flush();
                        pw.println(Badge);
                        pw.flush();
                        pw.println("END_CMD");
                        pw.flush();
                        var enter = scanner.nextLine();
                        if(enter.isBlank()){
                            System.out.println("Numero di Badge sbagliato, reinserire!");
                        }
                        else {
                            System.out.println("Data ingresso: " + enter);
                        }
                        break;
                    case "u":
                        System.out.println("Inserire numero di badge per timbrare l'uscita:");
                        Badge=input.nextLine();
                        pw.println("OUT_PERS");
                        pw.flush();
                        pw.println(Badge);
                        pw.flush();
                        pw.println("END_CMD");
                        pw.flush();
                        var exit = scanner.nextLine();
                        if(exit.isBlank()){
                            System.out.println("Numero di Badge sbagliato, reinserire!");
                        }
                        else {
                            var min = scanner.nextLine();
                            System.out.println("Data uscita: " + exit);
                            System.out.println("Minuti accumulati: " + min);
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