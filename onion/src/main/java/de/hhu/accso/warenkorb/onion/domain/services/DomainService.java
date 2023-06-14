package de.hhu.accso.warenkorb.onion.domain.services;

import de.hhu.accso.warenkorb.onion.domain.model.anzahl.Anzahl;
import de.hhu.accso.warenkorb.onion.domain.model.artikel.Artikel;
import de.hhu.accso.warenkorb.onion.domain.model.lager.Lager;
import de.hhu.accso.warenkorb.onion.domain.model.warenkorb.Warenkorb;
import org.springframework.stereotype.Service;

@Service
public class DomainService {

    public void legeArtikelInDenWarenkorb(Warenkorb warenkorb, Artikel artikel, Anzahl anzahl, Lager lager) {
        if (artikel == null) {
            throw new IllegalArgumentException("Produkt existiert nicht.");
        }

        if(lager.findeBestandZu(artikel.artikelID()).getAnzahl().istGroesserAls(anzahl)) {
            throw new IllegalArgumentException("Begrenzte Anzahl von Produkten.");
        }

        warenkorb.fuegeHinzu(artikel, anzahl);
        lager.reduziere(artikel.artikelID(), anzahl);
    }

    public void entferneArtikelAusDemWarenkorb(Warenkorb warenkorb, Artikel artikel, Lager lager) {
        Anzahl anzahl = warenkorb.findeZeileZu(artikel.artikelID()).getAnzahl();
        warenkorb.entferne(artikel);
        lager.fuegeHinzu(artikel.artikelID(), anzahl);
    }

    public void reduziereAnzahlVonArtikelInDemWarenkorb(Warenkorb warenkorb, Artikel artikel, Anzahl anzahl, Lager lager) {
        warenkorb.reduziere(artikel, anzahl);
        lager.fuegeHinzu(artikel.artikelID(), anzahl);
    }
}
