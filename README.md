# Softverski sistem za praćenje rada spasilačke službe

## Sadržaj
- [Opis](#opis)
- [Funkcionalnosti](#funkcionalnosti)
- [Instalacija](#instalacija)
- [Pokretanje](#pokretanje)
- [Doprinos](#doprinos)

## Opis
Projekat je rađen za potrebe izrade seminarskog rada iz Projektovanja softvera.

## Funkcionalnosti
Projekat se sastoji iz 3 dela:
- **klijent**
- **server**
- **zajednicki**

### Klijent

Klijent predstavlja klijentski deo aplikacije. Pokretanjem klijenta omogućene su glavne funkcionalnosti aplikacije:

- CRUD operacije za spasioce
- CRUD operacije za smene
- CRUD operacije za rasporede
- CRUD operacije za angažovanja
- CRUD operacije za izveštaje

### Server

Server predstavlja serverski deo aplikacije. Pokretanjem servera omogućeno je:

- Povezivanje sa bazom podataka
- Otvaranje serverskog soketa
- Obrada zahteva od klijenata

### Zajednički

Projekat `Zajednicki` predstavlja zajedničku biblioteku domenskih klasa koje će biti korišćene i operacija koje će biti implementirane od strane klijentske i serverske aplikacije.

## Instalacija

Instaliranje je jednostavno i radi se u par koraka:
1. Preuzeti ili klonirati repozitorijum
2. Izvršiti SQL skriptu `baza.sql` u alatima kao što su Microsoft SQL Server ili SQLyog

## Pokretanje

Da biste uspešno pokrenuli celu aplikaciju potrebno je:
- Pokrenuti serversku aplikaciju
- Klikom na dugme u serverskoj GUI formi pokrenuti server
- Pokrenuti klijentsku aplikaciju
- Prijaviti se na sistem korišćenjem korisničkog imena `admin` i lozinke `admin`

## Doprinos

Ako želiš da doprineseš razvoju projekta, pogledaj [smernice za doprinos](CONTRIBUTING.md). 
Hvala!