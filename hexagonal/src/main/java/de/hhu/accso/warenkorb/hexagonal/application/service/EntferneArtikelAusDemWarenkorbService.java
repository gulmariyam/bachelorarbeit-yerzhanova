package de.hhu.accso.warenkorb.hexagonal.application.service;

import de.hhu.accso.warenkorb.hexagonal.application.port.in.EntferneArtikelAusDemWarenkorbUseCase;
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

public class EntferneArtikelAusDemWarenkorbService implements EntferneArtikelAusDemWarenkorbUseCase {
    private final WarenkorbRepository warenkorbRepository;
    private final ArtikelRepository artikelRepository;
    private final LagerRepository lagerRepository;


    public EntferneArtikelAusDemWarenkorbService(WarenkorbRepository warenkorbRepository,
                            ArtikelRepository artikelRepository,
                            LagerRepository lagerRepository) {
        this.warenkorbRepository = warenkorbRepository;
        this.artikelRepository = artikelRepository;
        this.lagerRepository = lagerRepository;
    }

    @Override
    public void entferneArtikelAusDemWarenkorb(UUID artikelId, UUID warenkorbID) {
        Warenkorb warenkorb =  warenkorbRepository.findeMit(new WarenkorbID(warenkorbID));
        Artikel artikel = artikelRepository.findeMit(new ArtikelID(artikelId));
        Lager lager = lagerRepository.findeMit(artikelId);

        Anzahl anzahl = warenkorb.findeZeileZu(artikel.artikelID()).getAnzahl();

        warenkorb.entferne(artikel);
        lager.fuegeHinzu(artikel.artikelID(), anzahl);

        warenkorbRepository.speichere(warenkorb);
        lagerRepository.speichere(lager);
    }
}
