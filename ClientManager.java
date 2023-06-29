import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ClientManager  implements Runnable{
Socket client_socket;
Server myserver;
public ClientManager(Socket client, Server server){
    System.out.println("Creazione di un nuovo gestore di client!");
    this.myserver = server;
    this.client_socket = client;
}
public void go(){
    Scanner sc = null;

    try {
        sc = new Scanner(client_socket.getInputStream());
        var pw = new PrintWriter(client_socket.getOutputStream());
        String sceltaOpzione = "";
        String inputString = "";

        while (!sceltaOpzione.equals("CMD_CLOSE")){
                sceltaOpzione = sc.nextLine();
            System.out.println("Gestione lista: " + sceltaOpzione);
            if(sceltaOpzione.equals("CMD_CLOSE")){
                break;
            }
            else if(sceltaOpzione.equals("LISTA_P")){
                inputString = "";
                while (!inputString.equals("CMD_QUIT")) {
                    inputString = sc.nextLine();
                    System.out.println("Comando ricevuto " + inputString);
                    switch (inputString){
                        case "CMD_ADD_PERSONALE":
                            var nome = sc.nextLine();
                            var cognome = sc.nextLine();
                            var eta = sc.nextLine();
                            if(Integer.parseInt(eta)<18 || Integer.parseInt(eta)>70){
                                System.out.println("Età non valida");
                                pw.println("Età non valida!");
                                pw.flush();
                                break;
                            }
                            var sesso = sc.nextLine();
                            if(!(sesso.equals("M") || sesso.equals("m")) && !(sesso.equals("F") || sesso.equals("f"))){
                                System.out.println("Sesso non valido");
                                pw.println("Sesso non valido! Inserire M,F,m,f");
                                pw.flush();
                                break;
                            }
                            var mansione = sc.nextLine();
                            var end_command = sc.nextLine();
                            if(!end_command.equals("END_CMD")){
                                System.out.println("Format error");
                            }
                            String NumBadge=myserver.generaBadge(mansione);
                            if(NumBadge.isBlank()){
                                System.out.println("MANSIONE NON VALIDA");
                                pw.println("Mansione non valida!");
                                pw.flush();
                                break;
                            }
                            var ingresso="";
                            var uscita="";
                            var status=false;
                            var totminuti=0;
                            System.out.println("Membro del personale aggiunto: " +nome + " " + cognome + " " + eta + " " + sesso + " " + NumBadge + " " + mansione);
                            var personale = new Personale(nome, cognome, Integer.parseInt(eta),sesso, NumBadge, mansione, ingresso, uscita, status, totminuti);
                            myserver.AddPersonale(personale);
                            pw.println("Utente aggiunto correttamente!");
                            pw.flush();
                            break;
                        case "CMD_REMOVE_PERSONALE":
                            var Badge = sc.nextLine();
                            end_command=sc.nextLine();
                            if(!end_command.equals("END_CMD")){
                                System.out.println("Format error");
                            }
                            var checkOperazione = myserver.rimuoviPersonale(Badge);
                            pw.println(checkOperazione);
                            pw.flush();
                            break;
                        case "CMD_SAVE_PERSONALE":
                            System.out.println("Salvataggio lista...");
                            String filename=sc.nextLine();
                            end_command=sc.nextLine();
                            if(!end_command.equals("END_CMD")){
                                System.out.println("Format error");
                            }
                            myserver.commandSave(filename,sceltaOpzione);
                            break;
                        case "CMD_MOSTRA_PERSONALE":
                            end_command=sc.nextLine();
                            if(!end_command.equals("END_CMD")){
                                System.out.println("Format error");
                            }
                            for (String s: myserver.getListString(sceltaOpzione)){
                                pw.println(s);
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
                            myserver.commandLoad(file_to_load, sceltaOpzione);
                            break;
                        case "CMD_QUIT":
                            System.out.println("Ritorno alla scelta opzione...");
                            sceltaOpzione = "";
                            break;
                        default:
                            if(!inputString.isBlank()) {
                                System.out.println("Comando sconosciuto");
                            }
                    }
                }
            }
            else if(sceltaOpzione.equals("LISTA_V")){
                inputString = "";
                while (!inputString.equals("CMD_QUIT")) {
                    inputString = sc.nextLine();
                    System.out.println("Comando ricevuto " + inputString);
                    switch (inputString){
                        case "CMD_ADD_VISITATORE":
                            var nome = sc.nextLine();
                            var cognome = sc.nextLine();
                            var eta = sc.nextLine();
                            var sesso = sc.nextLine();
                            if(!(sesso.equals("M") || sesso.equals("m")) && !(sesso.equals("F") || sesso.equals("f"))){
                                System.out.println("Sesso non valido");
                                pw.println("Sesso non valido! Inserire M,F,m,f");
                                pw.flush();
                                break;
                            }
                            var TipologiaVisita = sc.nextLine();
                            var end_command = sc.nextLine();
                            if(!end_command.equals("END_CMD")){
                                System.out.println("Format error");
                            }
                            String IDBiglietto=myserver.generaBiglietto(TipologiaVisita);
                            if(IDBiglietto.isBlank()){
                                System.out.println("Tipologia biglietto NON VALIDA");
                                pw.println("Tipologia biglietto non valida! Inserire Guidata o Libera");
                                pw.flush();
                                break;
                            }
                            var Validita = true;
                            System.out.println("Visitatore aggiunto: " +nome + " " + cognome + " " + eta + " " + sesso + " " + IDBiglietto + " " + TipologiaVisita);
                            var visitatore = new Visitatore(nome, cognome, Integer.parseInt(eta),sesso, IDBiglietto, TipologiaVisita, Validita);
                            myserver.AddVisitatore(visitatore);
                            pw.println("Visitatore aggiunto");
                            pw.flush();
                            break;
                        case "CMD_REMOVE_VISITATORE":
                            var biglietto = sc.nextLine();
                            end_command=sc.nextLine();
                            if(!end_command.equals("END_CMD")){
                                System.out.println("Format error");
                            }
                            var checkOperazione = myserver.rimuoviVisitatore(biglietto);
                            pw.println(checkOperazione);
                            pw.flush();
                            break;
                        case "CMD_SAVE_VISITATORE":
                            System.out.println("Salvataggio lista...");
                            String filename=sc.nextLine();
                            end_command=sc.nextLine();
                            if(!end_command.equals("END_CMD")){
                                System.out.println("Format error");
                            }
                            myserver.commandSave(filename,sceltaOpzione);
                            break;
                        case "CMD_MOSTRA_VISITATORE":
                            end_command=sc.nextLine();
                            if(!end_command.equals("END_CMD")){
                                System.out.println("Format error");
                            }
                            for (String s: myserver.getListString(sceltaOpzione)){
                                pw.println(s);
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
                            myserver.commandLoad(file_to_load, sceltaOpzione);
                            break;
                        case "CMD_QUIT":
                            System.out.println("Ritorno alla selezione opzioni...");
                            sceltaOpzione = "";
                            break;
                        default:
                            if(!inputString.isBlank()) {
                                System.out.println("Comando sconosciuto");
                            }
                    }
                }
            }
            else if (sceltaOpzione.equals("BIGLIETTERIA")){
                inputString = sc.nextLine();
                var end_command=sc.nextLine();
                if(!end_command.equals("END_CMD")){
                    System.out.println("Format error");
                }
                var checkBiglietto = myserver.controlloBiglietto(inputString);
                pw.println(checkBiglietto);
                pw.flush();
            }
            else if(sceltaOpzione.equals("IN_PERS")){
                inputString=sc.nextLine();
                var end_command=sc.nextLine();
                if(!end_command.equals("END_CMD")){
                    System.out.println("Format error");
                }
                String IngressoPers = myserver.ingressoPersonale(inputString);
                if(!IngressoPers.equals("Badge non riconosciuto, reinserire!") && !IngressoPers.equals("Il lavoratore ha già timbrato l'ingresso")){
                    pw.println(IngressoPers);
                    pw.flush();
                }
                else {
                    pw.println("");
                    pw.flush();
                }
            }
            else if(sceltaOpzione.equals("OUT_PERS")){
                inputString=sc.nextLine();
                var end_command=sc.nextLine();
                if(!end_command.equals("END_CMD")){
                    System.out.println("Format error");
                }
                String UscitaPers = myserver.uscitaPersonale(inputString);
                float totMin = myserver.contaMinuti(inputString);
                if(!UscitaPers.equals("Badge non riconosciuto, reinserire!")){
                    pw.println(UscitaPers);
                    pw.flush();
                    pw.println(totMin);
                    pw.flush();
                }
                else {
                    pw.println("");
                    pw.flush();
                }
            }
        }
    }
        catch (IOException e) {
        throw new RuntimeException(e);
    }
}
    @Override
    public void run() {
        System.out.println("Inizio esecuzione...");
        go();
    }
}