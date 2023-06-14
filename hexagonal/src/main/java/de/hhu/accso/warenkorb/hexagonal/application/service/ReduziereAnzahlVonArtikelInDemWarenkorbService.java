package de.hhu.accso.warenkorb.hexagonal.application.service;

import de.hhu.accso.warenkorb.hexagonal.application.port.in.ReduziereAnzahlVonArtikelInDemWarenkorbUseCase;
import de.hhu.accso.warenkorb.hexagonal.application.port.out.ArtikelRepository;
import de.hhu.accso.warenkorb.hexagonal.application.port.out.LagerRepository;
import de.hhu.accso.warenkorb.hexagonal.application.port.out.WarenkorbRepository;
import de.hhu.accso.warenkorb.hexagonal.domain.model.anzahl.Anzahl;
import de.hhu.accso.warenkorb.hexagonal.domain.model.artikel.Artikel;
import de.hhu.accso.warenkorb.hexagonal.domain.model.artikel.ArtikelID;
import de.hhu.accso.warenkorb.hexagonal.domain.model.lager.Lager;
import de.hhu.accso.warenkorb.hexagonal.domain.model.warenkorb.Warenkorb;
import de.hhu.accso.warenkorb.hexagonal.domain.model.warenkorb.WarenkorbID;

import java.util.UUID;

public class ReduziereAnzahlVonArtikelInDemWarenkorbService implements ReduziereAnzahlVonArtikelInDemWarenkorbUseCase {
    private final WarenkorbRepository warenkorbRepository;
    private final ArtikelRepository artikelRepository;
    private final LagerRepository lagerRepository;


    public ReduziereAnzahlVonArtikelInDemWarenkorbService(WarenkorbRepository warenkorbRepository,
                            ArtikelRepository artikelRepository,
                            LagerRepository lagerRepository) {
        this.warenkorbRepository = warenkorbRepository;
        this.artikelRepository = artikelRepository;
        this.lagerRepository = lagerRepository;
    }

    public void reduziereAnzahlVonArtikelInDemWarenkorb(UUID artikelId, int anzahl, UUID warenkorbID) {
        Warenkorb warenkorb =  warenkorbRepository.findeMit(new WarenkorbID(warenkorbID));
        Artikel artikel = artikelRepository.findeMit(new ArtikelID(artikelId));
        Lager lager = lagerRepository.findeMit(artikelId);

        warenkorb.reduziere(artikel, new Anzahl(anzahl));
        lager.fuegeHinzu(artikel.artikelID(), new Anzahl(anzahl));

        warenkorbRepository.speichere(warenkorb);
        lagerRepository.speichere(lager);
    }
}