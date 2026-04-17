# Progetto E-Commerce Core: Full-Stack Spring Boot Application

![Spring Boot](https://img.shields.io/badge/Backend-Spring%20Boot%203.x-brightgreen)
![JWT](https://img.shields.io/badge/Security-JSON%20Web%20Token-blue)
![JPA](https://img.shields.io/badge/Persistence-Spring%20Data%20JPA-orange)

Un sistema e-commerce progettato per gestire l'intero workflow di vendita: dall'autenticazione granulare dell'utente alla persistenza degli ordini nel database, passando per una gestione dinamica del carrello.

## Sviluppatori
* **Donato Morra** - [GitHub Profile](https://github.com/DonatoMorra)
* **Michela Della Gatta** - [GitHub Profile](https://github.com/Michecosa)

## Tech Stack & Highlights

Il cuore dell'applicazione è costruito su **Spring Boot**, sfruttando l'architettura a livelli (Controller-Service-Repository) per garantire manutenibilità e prestazioni

* **Security Layer (JWT):** Autenticazione Stateless avanzata. All'accesso, il sistema rilascia un token criptato che valida ogni successiva operazione di acquisto, garantendo la protezione dei dati sensibili
* **Gestione Prodotti & Carrello:** Sistema reattivo per la navigazione del catalogo e logica di calcolo dei totali lato server per prevenire discrepanze di prezzo
* **Persistence & Entities:** Utilizzo di **Spring Data JPA**. La classe `Ordine` estende `BaseEntity`, ereditandone i metadati comuni e gestendo relazioni `@ManyToOne` verso l'utente e il tracking logistico.
* **Payment Simulator:** Integrazione di un workflow di checkout che valida l'indirizzo di spedizione e aggiorna lo stato `pagato` in tempo reale

## Funzionalità Principali

* **User Area:** Registrazione e login protetti. Solo gli utenti loggati possono accedere al carrello e procedere al pagamento.
* **Order Lifecycle:**
    * Generazione automatica dell'ordine post-checkout
    * Gestione della `LocalDate consegna` per una stima precisa della spedizione
    * Storico ordini persistente filtrato per singolo utente
* **RESTful API:** Endpoint ottimizzati per la comunicazione con qualsiasi frontend moderno

## 🏗️ Architettura del Database (Entity Ordine)
Il backend sfrutta **Spring Data JPA** per mappare un modello relazionale complesso e ottimizzato. Ogni entità eredita da una `BaseEntity` (per la gestione automatica degli ID e dei timestamp) e definisce relazioni coerenti per garantire l'integrità dei dati.

### Schema delle Entità Principali

| Entità | Attributi Chiave | Relazioni & Note |
| :--- | :--- | :--- |
| **Utente** | `username`, `password`, `email`, `ruolo` | Gestisce l'autenticazione JWT e possiede molteplici `Ordini` |
| **Prodotto** | `nome`, `descrizione`, `prezzo`, `quantitaDisponibile` | Collegato a una `Categoria`. Gestisce lo stock in tempo reale |
| **Categoria** | `nome`, `descrizione` | Relazione `@OneToMany` con i prodotti per una navigazione filtrata |
| **Ordine** | `dataOrdine`, `consegna`, `pagato`, `indirizzo` | Relazione `@ManyToOne` con l'Utente. Funge da testata dell'acquisto |
| **DettaglioOrdine** | `quantita`, `prezzoUnitario` | Tabella di pivot tra `Ordine` e `Prodotto` per tracciare i singoli articoli acquistati |
| **Carrello** | `sessioneId`, `totale` | Gestisce gli articoli temporanei prima della conversione in ordine |

### Logica delle Relazioni (JPA Mapping)

* **User & Order:** Un utente può effettuare molti ordini (`@OneToMany`), ma ogni ordine appartiene a un solo utente (`@ManyToOne`)
* **Product & Category:** I prodotti sono organizzati in categorie per ottimizzare le query di ricerca e il posizionamento nel catalogo
* **Order & Product (Composition):** L'integrità del prezzo è garantita salvando il `prezzoUnitario` nel `DettaglioOrdine` al momento dell'acquisto, proteggendo lo storico ordini da future variazioni di listino dei prodotti


## 🔒 Sicurezza e Privacy
L'implementazione di **Spring Security** con filtro **JWT** garantisce che le risorse degli ordini siano isolate: un utente può consultare esclusivamente i propri dati. I dati delle carte di credito non vengono memorizzati; la proprietà `pagato` viene commutata esclusivamente tramite il servizio di simulazione protetto