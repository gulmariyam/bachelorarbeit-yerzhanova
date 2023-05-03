package de.hhu.accso.warenkorb.onion.application.services;

import de.hhu.accso.warenkorb.onion.application.repository.ArtikelRepository;
import de.hhu.accso.warenkorb.onion.application.repository.WarenkorbRepository;
import de.hhu.accso.warenkorb.onion.domain.model.*;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    private Warenkorb warenkorb;
    private final WarenkorbRepository warenkorbRepository;
    private final ArtikelRepository artikelRepository;


    public ApplicationService(Warenkorb warenkorb, WarenkorbRepository warenkorbRepository, ArtikelRepository artikelRepository) {
        this.warenkorb = warenkorb;
        this.warenkorbRepository = warenkorbRepository;
        this.artikelRepository = artikelRepository;
    }

    public Warenkorb getWarenkorb(WarenkorbId warenkorbId) {
        return warenkorbRepository.findeMit(warenkorbId);
    }

    public void legeArtikelInDenWarenkorb(ArtikelId artikelId, Anzahl anzahl) {
        Artikel artikel = artikelRepository.findeMit(artikelId);
        if (artikel == null) {
            throw new IllegalArgumentException("Produkt existiert nicht.");
        }
        warenkorb.fuegeHinzu(artikel, anzahl);
        warenkorbRepository.speichere(warenkorb);
    }

    public void entferneArtikelAusDemWarenkorb(ArtikelId artikelId) {
        Artikel artikel = artikelRepository.findeMit(artikelId);
        warenkorb.entferne(artikel);
        warenkorbRepository.speichere(warenkorb);
    }

    public void reduziereAnzahlVonArtikelInDemWarenkorb(ArtikelId artikelId, Anzahl anzahl) {
        Artikel artikel = artikelRepository.findeMit(artikelId);
        Anzahl aktuelleAnzahl = warenkorb.findeZeileZu(artikelId).getAnzahl();
        Anzahl neueAnzahl = aktuelleAnzahl.reduziereUm(anzahl);
        if (neueAnzahl.anzahl() == 0) {
            warenkorb.entferne(artikel);
        } else {
            warenkorb.reduziere(artikel, anzahl);
        }
        warenkorbRepository.speichere(warenkorb);
    }
}
