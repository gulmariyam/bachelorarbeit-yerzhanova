# Bachelorarbeit-yerzhanova

Dieses Repository enthält Beispielanwendungen einer Java REST-Anwendung, 
die jeweils mit den verschiedenen Architekturstilen: Hexagonal, Onion und Clean umgesetzt wurden.

## Ziel
Das Ziel dieser Anwendungen ist es, ein grundlegendes Verständnis für die gegebenen Architekturstile - 
Hexagonal, Onion und Clean - zu vermitteln.
Die Beispielanwendungen dienen als Demonstrationsprojekte, 
um den Einsatz dieser Architekturmuster zu verdeutlichen und die Unterschiede zwischen ihnen zu zeigen.
Sie sollen auch dazu beitragen, die Forschungsfragen im Rahmen der Abschlussarbeit zu beantworten.

Das Beispiel ist eine kleine Warenkorb-Anwendung mit folgenden Use Cases:

![Warenkorb.png](fig%2FWarenkorb.png)

Warenkorb Use-Cases:
1. Der Benutzer kann ein Produkt in den Warenkorb legen: Warenkorb eröffnen.
2. Der Warenkorb hat einen maximalen Einkaufswert, der nicht überschritten werden darf.
3. Der Benutzer kann ein Produkt aus dem Warenkorb entfernen.
4. Der Benutzer kann die Anzahl der Produkte im Warenkorb ändern.
5. Wird erstmals ein Produkt in den Warenkorb gelegt, gibt es eine Preisbindung.
6. Für Produkte kann eine Limitierung pro Warenkorb existieren.

Lager Use-Cases: (für Domain Services):
1. Wenn ein Kunde ein Produkt in den Warenkorb legt, wird der verfügbare Lagerbestand des Produkts entsprechend verringert.
2. Wenn der Lagerbestand auf null fällt und ein Kunde versucht, das Produkt in den Warenkorb zu legen, wird eine Fehlermeldung angezeigt, dass das Produkt nicht mehr verfügbar ist.
3. Wenn ein Kunde ein Produkt aus dem Warenkorb entfernt, wird der Lagerbestand entsprechend erhöht.
4. Es gibt eine Limitierung von Lager.