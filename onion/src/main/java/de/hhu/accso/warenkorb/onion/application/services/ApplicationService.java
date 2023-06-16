package de.hhu.accso.warenkorb.onion.application.services;

import de.hhu.accso.warenkorb.onion.domain.model.shared.anzahl.Anzahl;
import de.hhu.accso.warenkorb.onion.domain.model.artikel.Artikel;
import de.hhu.accso.warenkorb.onion.domain.model.artikel.ArtikelID;
import de.hhu.accso.warenkorb.onion.domain.model.kunde.KundeID;
import de.hhu.accso.warenkorb.onion.domain.model.lager.Lager;
import de.hhu.accso.warenkorb.onion.domain.model.shared.preis.Preis;
import de.hhu.accso.warenkorb.onion.domain.model.warenkorb.Warenkorb;
import de.hhu.accso.warenkorb.onion.domain.model.warenkorb.WarenkorbID;
import de.hhu.accso.warenkorb.onion.domain.repository.ArtikelRepository;
import de.hhu.accso.warenkorb.onion.domain.repository.LagerRepository;
import de.hhu.accso.warenkorb.onion.domain.repository.WarenkorbRepository;
import de.hhu.accso.warenkorb.onion.domain.services.DomainService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class ApplicationService {
    private final DomainService domainService;
    private final WarenkorbRepository warenkorbRepository;
    private final ArtikelRepository artikelRepository;
    private final LagerRepository lagerRepository;


    public ApplicationService(DomainService domainService,
                              WarenkorbRepository warenkorbRepository,
                              ArtikelRepository artikelRepository,
                              LagerRepository lagerRepository) {
        this.domainService = domainService;
        this.warenkorbRepository = warenkorbRepository;
        this.artikelRepository = artikelRepository;
        this.lagerRepository = lagerRepository;
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

    public Artikel getArtikel(UUID artikel) {
        ArtikelID artikelID = new ArtikelID(artikel);
        return artikelRepository.findeMit(artikelID);
    }

    public void legeArtikelInDenWarenkorb(UUID artikelId, int anzahl, UUID warenkorbID) {
        Warenkorb warenkorb = getWarenkorb(warenkorbID);
        Artikel artikel = getArtikel(artikelId);
        Lager lager = lagerRepository.findeMit(artikelId);

        domainService.legeArtikelInDenWarenkorb(warenkorb, artikel, new Anzahl(anzahl), lager);

        warenkorbRepository.speichere(warenkorb);
        lagerRepository.speichere(lager);
    }

    public void entferneArtikelAusDemWarenkorb(UUID artikelId, UUID warenkorbID) {
        Warenkorb warenkorb = getWarenkorb(warenkorbID);
        Artikel artikel = getArtikel(artikelId);
        Lager lager = lagerRepository.findeMit(artikelId);

        domainService.entferneArtikelAusDemWarenkorb(warenkorb, artikel, lager);

        warenkorbRepository.speichere(warenkorb);
        lagerRepository.speichere(lager);
    }


    public void reduziereAnzahlVonArtikelInDemWarenkorb(UUID artikelId, int anzahl, UUID warenkorbID) {
        Warenkorb warenkorb = getWarenkorb(warenkorbID);
        Artikel artikel = getArtikel(artikelId);
        Lager lager = lagerRepository.findeMit(artikelId);

        domainService.reduziereAnzahlVonArtikelInDemWarenkorb(warenkorb, artikel, new Anzahl(anzahl), lager);

        warenkorbRepository.speichere(warenkorb);
        lagerRepository.speichere(lager);
    }
}
