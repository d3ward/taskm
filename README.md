# taskm
Progetto SIW 2020 - Task Manager

## Specifiche

* Si vuole realizzare il sistema informativo su Web per la gestione di progetti
* Possono usare il sistema due tipologie di utenti: gli utenti registrati e l'amministratore.
* Gli utenti possono creare e gestire Progetti
* L'amministratore può cancellare gli utenti e i loro progetti

### Specifiche Base

* Un Progetto ha un nome, una data di inizio e si compone di uno o più Task 
* Ogni Progetto ha un proprietario e può essere condiviso da uno o più utenti
* Ogni utente ha uno username e una password, un nome, un cognome, una data di creazione:
– può essere proprietario di uno o più progetti e può avere visibilità su uno o più progetti (di cui non è proprietario)
– si autentica sul sistema con un username (univoco) e password
– può creare progetti
– può aggiungere task ai propri progetti
– può concedere la visibilità dei propri progetti ad altri utenti
* Ogni Task ha un nome, una descrizione, una data di creazione, ed è assegnato ad un solo utente tra quelli che hanno visibilità sul progetto

### Specifiche Estese

* Ogni Tag ha un nome, un colore e una descrizione
$ Ad ogni progetto posso associare uno o più tag
$ L' utente può associare ad ogni task uno o più tag scegliendo dalla lista di tag associati al progetto corrente
$ Solo l' utente proprietario del progetto può effettuare le sueguenti operazioni di modifica e cancellazione:
  + creazione, modifica e cancellazione di una task
  + assegnazione di una task ad un utente che ha visibilità  sul progetto
  + creazione, modifica e cancellazione di un tag
  + assegnazione di una tag a una task scegliendo tra i tag associati al progetto corrente
  
* Ogni utente che abbia visibilità di un progetto può scrivere uno o più Commenti sotto qualsiasi Task di quel progetto



### Casi d'uso: User

- [x] Registrazione nuovo utente
- [x] Login utente
- [x] Visualizzare il mio profilo 
- [x] Aggiornare il mio profilo

### Casi d'uso: Project

- [x] Creare un nuovo progetto
- [x] Visualizzare i miei progetti
- [x] Visualizzare i progetti condivisi con me
- [x] Aggiornare i dati di un mio progetto
- [x] Cancellare un mio progetto
- [x] Condividere un mio progetto con un altro utente
- [x] Aggiungere un tag ad un mio progetto (estensione)

### Casi d'uso: Task

- [x] Aggiungere un nuovo Task a un mio progetto
- [x] Aggiornare un Task di un mio progetto
- [x] Cancellare un Task da un mio progetto
- [x] Assegnare un Task di un mio progetto ad un utente che ha visibilità sul mio progetto
- [x] Aggiungere un Tag ad un task di un mio progetto 
- [x] Aggiungere un Commento ad un Task di un progetto su cui ho visibilità 


### Casi d'uso: Tag

- [x] Aggiungere un nuovo Tag a un mio progetto
- [x] Cancellare un Tag da un mio progetto
- [x] Aggiungere un Tag ad un task scegliendo dalla lista di tag associati al progetto
- [x] Rimuovere un Tag da un task





