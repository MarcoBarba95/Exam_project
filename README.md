#APPompei

L’applicazione presentata si occupa della gestione del sito archeologico di Pompei. L’obiettivo è permettere 
l’organizzazione delle persone che si recano al sito per motivi differenti. Vengono identificate due categorie 
di persone: i membri del personale e i visitatori del sito archeologico. Per entrambe le categorie è stata predisposta
la possibilità di effettuare operazioni basilari all’interno di due liste differenti, le quali sono:
•	Aggiunta in lista;
•	Rimozione dalla lista;
•	Visualizzazione lista;
•	Salvataggio della lista su file;
•	Caricamento della lista da file;
Nell’operazione “Aggiunta in lista”, dopo aver inserito i dati, viene generato automaticamente il numero di badge, nel 
caso del personale, o l’ID Biglietto, nel caso dei visitatori. I badge vengono differenziati per categoria lavorativa 
(Sicurezza, Amministrazione, Pulizie, Guida, Archeologo), mentre i biglietti per tipologia di visita (Guidata o Libera).
Sono stati inoltre implementati dei servizi aggiuntivi differenziati in base alla categoria di appartenenza.
Nel caso dei membri del personale viene implementata l’operazione di “timbratura” sia in ingresso che in uscita, 
considerando la possibilità che il lavoratore possa entrare e uscire più volte durante il corso della stessa giornata. 
Per questo motivo è stata aggiunta una funzione che permette di calcolare i minuti effettivi che il lavoratore ha speso
all’interno del posto di lavoro. Inoltre, vengono effettuati dei controlli aggiuntivi:
•	Badge inserito correttamente;
•	Il lavoratore che ha già timbrato l’ingresso non viene accettato nuovamente in ingresso se prima non ha effettuato 
    l’uscita;
•	Il lavoratore non ha la possibilità di timbrare l’uscita se prima non è stato timbrato l’ingresso;
Per quanto riguarda i visitatori, la funzione aggiuntiva riguarda il controllo dei biglietti, in particolare:
•	ID Biglietto inserito correttamente;
•	Verifica che il biglietto non sia stato già validato;
