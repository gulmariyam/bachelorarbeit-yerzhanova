package de.hhu.accso.warenkorb.onion.application.services;

import de.hhu.accso.warenkorb.onion.domain.repository.ArtikelRepository;
import de.hhu.accso.warenkorb.onion.domain.repository.WarenkorbRepository;
import de.hhu.accso.warenkorb.onion.domain.model.anzahl.Anzahl;
import de.hhu.accso.warenkorb.onion.domain.model.artikel.Artikel;
import de.hhu.accso.warenkorb.onion.domain.model.artikel.ArtikelID;
import de.hhu.accso.warenkorb.onion.domain.model.kunde.KundeID;
import de.hhu.accso.warenkorb.onion.domain.model.preis.Preis;
import de.hhu.accso.warenkorb.onion.domain.model.warenkorb.Warenkorb;
import de.hhu.accso.warenkorb.onion.domain.model.warenkorb.WarenkorbID;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class ApplicationService {
    private final WarenkorbRepository warenkorbRepository;
    private final ArtikelRepository artikelRepository;


    public ApplicationService(WarenkorbRepository warenkorbRepository, ArtikelRepository artikelRepository) {
        this.warenkorbRepository = warenkorbRepository;
        this.artikelRepository = artikelRepository;
    }

    public void erstelleWarenkorbFuerKunde(UUID warenkorbID, UUID kundeID, BigDecimal preis) {
        Preis maxEinkaufswert = new Preis(preis);
        Warenkorb warenkorb = new Warenkorb(
            new WarenkorbID(warenkorbID),
            new KundeID(kundeID),
            maxEinkaufswert);
        warenkorbRepository.speichere(warenkorb);
    }
    public Warenkorb getWarenkorb(UUID warenkorb) {
        WarenkorbID warenkorbID = new WarenkorbID(warenkorb);
        return warenkorbRepository.findeMit(warenkorbID);
    }

    public void legeArtikelInDenWarenkorb(UUID artikelId, int anzahl, UUID warenkorbID) {
        Warenkorb warenkorb = getWarenkorb(warenkorbID);
        Artikel artikel = artikelRepository.findeMit(new ArtikelID(artikelId));
        if (artikel == null) {
            throw new IllegalArgumentException("Produkt existiert nicht.");
        }
        warenkorb.fuegeHinzu(artikel, new Anzahl(anzahl));
        warenkorbRepository.speichere(warenkorb);
    }

    public void entferneArtikelAusDemWarenkorb(UUID artikelId, UUID warenkorbID) {
        Warenkorb warenkorb = getWarenkorb(warenkorbID);
        Artikel artikel = artikelRepository.findeMit(new ArtikelID(artikelId));
        warenkorb.entferne(artikel);
        warenkorbRepository.speichere(warenkorb);
    }


    public void reduziereAnzahlVonArtikelInDemWarenkorb(UUID artikelId, int anzahl, UUID warenkorbID) {
        Warenkorb warenkorb = getWarenkorb(warenkorbID);
        Artikel artikel = artikelRepository.findeMit(new ArtikelID(artikelId));
        warenkorb.reduziere(artikel, new Anzahl(anzahl));
        warenkorbRepository.speichere(warenkorb);
    }
}
