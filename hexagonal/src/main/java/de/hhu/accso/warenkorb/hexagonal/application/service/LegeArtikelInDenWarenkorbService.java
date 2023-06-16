package de.hhu.accso.warenkorb.hexagonal.application.service;

import de.hhu.accso.warenkorb.hexagonal.application.port.in.LegeArtikelInDenWarenkorbUseCase;
import de.hhu.accso.warenkorb.hexagonal.application.port.out.ArtikelRepository;
import de.hhu.accso.warenkorb.hexagonal.application.port.out.LagerRepository;
import de.hhu.accso.warenkorb.hexagonal.application.port.out.WarenkorbRepository;
import de.hhu.accso.warenkorb.hexagonal.domain.model.shared.anzahl.Anzahl;
import de.hhu.accso.warenkorb.hexagonal.domain.model.artikel.Artikel;
import de.hhu.accso.warenkorb.hexagonal.domain.model.artikel.ArtikelID;
import de.hhu.accso.warenkorb.hexagonal.domain.model.lager.Lager;
import de.hhu.accso.warenkorb.hexagonal.domain.model.warenkorb.Warenkorb;
import de.hhu.accso.warenkorb.hexagonal.domain.model.warenkorb.WarenkorbID;

import java.util.UUID;

public class LegeArtikelInDenWarenkorbService implements LegeArtikelInDenWarenkorbUseCase {
    private final WarenkorbRepository warenkorbRepository;
    private final ArtikelRepository artikelRepository;
    private final LagerRepository lagerRepository;


    public LegeArtikelInDenWarenkorbService(WarenkorbRepository warenkorbRepository,
                            ArtikelRepository artikelRepository,
                            LagerRepository lagerRepository) {
        this.warenkorbRepository = warenkorbRepository;
        this.artikelRepository = artikelRepository;
        this.lagerRepository = lagerRepository;
    }

    @Override
    public void legeArtikelInDenWarenkorb(UUID artikelId, int anzahl, UUID warenkorbID) {
        Warenkorb warenkorb =  warenkorbRepository.findeMit(new WarenkorbID(warenkorbID));
        Artikel artikel = artikelRepository.findeMit(new ArtikelID(artikelId));
        Lager lager = lagerRepository.findeMit(artikelId);

        if (artikel == null) {
            throw new IllegalArgumentException("Produkt existiert nicht.");
        }

        if(lager.findeBestandZu(artikel.artikelID()).getAnzahl().istGroesserAls(new Anzahl(anzahl))) {
            throw new IllegalArgumentException("Begrenzte Anzahl von Produkten.");
        }

        warenkorb.fuegeHinzu(artikel, new Anzahl(anzahl));
        lager.reduziere(artikel.artikelID(), new Anzahl(anzahl));

        warenkorbRepository.speichere(warenkorb);
        lagerRepository.speichere(lager);
    }

}
