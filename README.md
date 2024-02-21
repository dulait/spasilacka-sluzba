# 🚑 Softverski sistem za praćenje rada spasilačke službe

Projekat je rađen za potrebe izrade seminarskog rada iz Projektovanja softvera.

## 📘Sadržaj
- [📝Funkcionalnosti](#funkcionalnosti)
  - [🤵Klijent](#klijent)
  - [💻Server](#server)
  - [🤝Zajednicki](#zajednički)
- [🔧Instalacija](#instalacija)
- [🚀Pokretanje](#pokretanje)
- [🤙Doprinos](#doprinos)

## 📝Funkcionalnosti
Projekat se sastoji iz 3 dela:
- **klijent**
- **server**
- **zajednicki**

### 🤵Klijent

Projekat `klijent` predstavlja klijentski deo aplikacije. Pokretanjem klijenta omogućene su glavne funkcionalnosti aplikacije:

- CRUD operacije za spasioce
- CRUD operacije za smene
- CRUD operacije za rasporede
- CRUD operacije za angažovanja
- CRUD operacije za izveštaje

### 💻Server

Projekat `server` predstavlja serverski deo aplikacije. Pokretanjem servera omogućeno je:

- Povezivanje sa bazom podataka
- Otvaranje serverskog soketa
- Obrada zahteva od klijenata

### 🤝Zajednički

Projekat `zajednicki` predstavlja zajedničku biblioteku domenskih klasa koje će biti korišćene i operacija koje će biti implementirane od strane klijentske i serverske aplikacije.

## 🔧Instalacija

Instaliranje je jednostavno i radi se u par koraka:
1. Preuzeti ili klonirati repo
2. Izvršiti SQL skriptu `baza.sql` u alatima kao što su Microsoft SQL Server ili SQLyog

## 🚀Pokretanje

Pre pokretanja aplikacije, potrebno je otvoriti sva tri projekta (klijent, server i zajednicki) u izabranom okruženju.
Nakon toga, potrebno je dodati `.jar` fajl od projekta `zajednicki` kao biblioteku u klijentski i serverski `Libraries` folder na sledeći način:
1. **Buildovanje Projekta Zajednicki:**
   - Desnim klikom izvršiti build projekta.
   - Nakon builda, pronaći generisani `.jar` fajl u `dist` folderu projekta.

2. **Dodavanje .jar Fajla u Projekte Klijent i Server:**
   - Kopirati generisani `.jar` fajl iz `dist` foldera projekta `zajednicki`.
   - Pronaći ili kreirati `Libraries` folder unutar klijentskog i serverskog projekta.
   - Prekopirati `.jar` fajl u `Libraries` folder.

Da biste uspešno pokrenuli celu aplikaciju potrebno je:
- Pokrenuti serversku aplikaciju
- Klikom na dugme u serverskoj GUI formi pokrenuti server
- Pokrenuti klijentsku aplikaciju
- Prijaviti se na sistem korišćenjem korisničkog imena `admin` i lozinke `admin`

## 🤙Doprinos

Ako želiš da doprineseš razvoju projekta, pogledaj [smernice za doprinos](CONTRIBUTING.md). 
Hvala!
