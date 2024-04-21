# Onemove
Progetto Team 1 - One Move

 <strong>Obiettivi</strong>
 
L’applicazione ha l’obiettivo di:
- Dare la possibilità di effettuare il noleggio a tempo della macchina, verificando Data, ora e durata con tariffa oraria e gestirle successivamente. 
- Verificare i propri noleggi
- Dare la possibilità di controllare e gestire i noleggi  del parco macchine
- Pagare il noleggio.


<strong>Attori coinvolti</strong>

Le classi principali sono: 
- Utente
- Veicolo
- Noleggio
- Recensione 


Tecnologie utilizzate: Spring Rest, Spring Boot, Java, JPA, SQL, Swagger


<strong>Utente</strong>

Deve avere un costruttore con questi parametri:
- id 
- nome
- cognome
- data di nascita
- indirizzo email (unique)
- codice fiscale (unique)
- genere
- indirizzo
- numero patente
- is verificato
  
Deve avere i seguenti metodi:
- add utente
- vedi utente
- vedi lista utenti
- update 
- delete
- soft delete
- cerca nome
- cerca cognome

<strong>Veicolo</strong>

Deve avere un costruttore con questi parametri:
- id 
- nome 
- marca 
- modello
- dettagli
- città
- tipo veicolo
- è disponibile
  
Deve avere i seguenti metodi:
- crea 
- vedi veicolo
- vedi lista veicoli
- update 
- delete
- cerca per brand
- cerca per modello
- è disponibile 
- cerca per tipo
- cerca per città

<strong>Noleggio</strong>

Deve avere un costruttore con questi parametri:
- id
- data ora inizio
- data ora fine 
- prezzo
- veicolo
- utente
  
Deve avere i seguenti metodi:
- add
- remove
- modifica 
- modifica stato veicolo
- vedi singolo veicolo 
- vedi lista veicoli 
- calcolo prezzo
- termina noleggio


<strong>Recensione</strong>

Deve avere un costruttore con questi parametri:
- id
- nome
- descrizione
- voto 
- id noleggio
  
Deve avere i seguenti metodi:
- add
- remove
- modifica 
- vedi singola recensione
- vedi lista recensioni
- trova per rating
- sorted list

