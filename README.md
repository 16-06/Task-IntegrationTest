
Prosta aplikacja REST-owa integrująca się z publicznym API GitHuba w celu pobierania **nie-forkowanych repozytoriów** użytkownika oraz ich gałęzi z SHA commitów.  
Projekt zawiera kompletny **test integracyjny**

---

## Założenia funkcjonalne

- Endpoint `/github/{username}/repos` pobiera **wszystkie repozytoria użytkownika**, filtrując tylko te, które **nie są forkami**.
- Dla każdego repozytorium zwracane są wszystkie **gałęzie (branches)** wraz z SHA ostatniego commita.
- Aplikacja zwraca dane w formacie JSON.
---

## Testy

Projekt zawiera **jeden kompletny test integracyjny**, który:

- Mockuje odpowiedź GitHuba z wykorzystaniem WireMocka (`/users/{username}/repos`, `/repos/{username}/{repo}/branches`).
- Sprawdza pełną integrację kontrolera z klientem HTTP.
- Weryfikuje poprawność danych: nazwy repo, login właściciela, nazwy gałęzi i SHA commitów.
- Nie używa mockowania serwisu – testuje pełną ścieżkę.

---

## Założenia projektowe
- Brak paginacji (upraszczamy integrację).
- Brak mapowania domenowego (prosta logika DTO → DTO).
- Jeden pełny test integracyjny – brak potrzeby testów jednostkowych.
- Aplikacja zgodna z podejściem API Gateway – pobiera, filtruje i agreguje dane.

---
## Projekt na potrzeby zadania rekrutacyjnego.

