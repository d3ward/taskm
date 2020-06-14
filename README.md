# taskm
Progetto SIW 2020 - Task Manager

## Specifiche
• SivuolerealizzareilsistemainformativosuWebper la gestione di progetti
• Possono usare il sistema due tipologie di attori: gli utenti registrati e l'amministratore
• Gli utenti possono creare e gestire Progetti
• L'amministratorepuòcancellaregliutentieiloro progetti
### Specifiche Base
• Un Progetto ha un nome, una data di inizio e si compone di uno o più Task (attività)
• Ogni Progetto ha un proprietario (un utente del sistema) e può essere condiviso da uno o più utenti
• Ogni utente ha uno username e una password, un nome, un cognome, una data di creazione:
– può essere proprietario di uno o più progetti e può avere visibilità su uno o più progetti (di cui non è proprietario)
– si autentica sul sistema con un username (univoco) e password
– può creare progetti
– può aggiungere task ai propri progetti
– può concedere la visibilità dei propri progetti ad altri utenti
• Ogni Task ha un nome, una descrizione, una data di creazione, ed è assegnato ad un solo utente tra quelli che hanno visibilità sul progetto

### Specifiche Estese
• Ogni Progetto può essere associato ad uno o più Tag
– OgniTaghaunnome,uncolore(unastringa)eunadescrizione
• Ogni Task può essere associato ad uno o più Tag del progetto a cui appartiene, e ogni Tag può essere associato ad uno o più Task
– Solol'utenteproprietariodelprogettopuòeffettuarel'assegnazione dei Tag al Task
• Ogni utente che abbia visibilità di un progetto può scrivere uno o più Commenti sotto qualsiasi Task di quel progetto

## Casi d'uso
• Segue una traccia schematica dei principali casi d'uso
• I casi d'uso dovranno essere estesi e completati a piacere (giustificando ogni scelta)

### Casi d'uso: User
• Signup
• Autenticazione
• Visualizzare il mio profilo • Aggiornare il mio profilo

### Casi d'uso: Project
• Creare un nuovo progetto
• Visualizzare i miei progetti
• Visualizzare i progetti condivisi con me
• Aggiornare i dati di un mio progetto
• Cancellare un mio progetto
• Condividere un mio progetto con un altro utente
• Aggiungere un tag ad un mio progetto (estensione)

### Casi d'uso: Task
• Aggiungere un nuovo Task a un mio progetto
• Aggiornare un Task di un mio progetto
• Cancellare un Task da un mio progetto
• Assegnare un Task di un mio progetto ad un utente che ha visibilità sul mio progetto
• Aggiungere un Tag ad un task di un mio progetto (estensione)
• Aggiungere un Commento ad un Task di un progetto su cui ho visibilità (estensione)

## Termini e modalità di consegna
• Progettare il sistema, definendone casi d’uso, class diagram (con indicazioni utili alla progettazione dello strato di persistenza)
• Implementare con Spring Boot tutto lo strato di persistenza (repository) e lo strato della logica applicativa servizi (model e service)
• Implementare con Spring Boot lo strato di presentazione per almeno 5 casi d'uso (che prevedano operazioni crud diverse)
