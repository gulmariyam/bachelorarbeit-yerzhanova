package de.hhu.accso.warenkorb.clean.application.interactor;

import de.hhu.accso.warenkorb.clean.application.usecase.WarenkorbUseCase;
import de.hhu.accso.warenkorb.clean.entities.anzahl.Anzahl;
import de.hhu.accso.warenkorb.clean.entities.artikel.Artikel;
import de.hhu.accso.warenkorb.clean.entities.artikel.ArtikelID;
import de.hhu.accso.warenkorb.clean.entities.kunde.KundeID;
import de.hhu.accso.warenkorb.clean.entities.preis.Preis;
import de.hhu.accso.warenkorb.clean.entities.warenkorb.Warenkorb;
import de.hhu.accso.warenkorb.clean.entities.warenkorb.WarenkorbID;
import de.hhu.accso.warenkorb.clean.application.repository.ArtikelRepository;
import de.hhu.accso.warenkorb.clean.application.repository.WarenkorbRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class WarenkorbUseCaseInteractor implements WarenkorbUseCase {
    private final WarenkorbRepository warenkorbRepository;
    private final ArtikelRepository artikelRepository;


    public WarenkorbUseCaseInteractor(WarenkorbRepository warenkorbRepository, ArtikelRepository artikelRepository) {
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