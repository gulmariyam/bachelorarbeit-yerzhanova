package de.hhu.accso.warenkorb.clean.application.interactor;

import de.hhu.accso.warenkorb.clean.application.repository.LagerRepository;
import de.hhu.accso.warenkorb.clean.application.usecase.*;
import de.hhu.accso.warenkorb.clean.entities.shared.anzahl.Anzahl;
import de.hhu.accso.warenkorb.clean.entities.artikel.Artikel;
import de.hhu.accso.warenkorb.clean.entities.artikel.ArtikelID;
import de.hhu.accso.warenkorb.clean.entities.kunde.KundeID;
import de.hhu.accso.warenkorb.clean.entities.lager.Lager;
import de.hhu.accso.warenkorb.clean.entities.shared.preis.Preis;
import de.hhu.accso.warenkorb.clean.entities.warenkorb.Warenkorb;
import de.hhu.accso.warenkorb.clean.entities.warenkorb.WarenkorbID;
import de.hhu.accso.warenkorb.clean.application.repository.ArtikelRepository;
import de.hhu.accso.warenkorb.clean.application.repository.WarenkorbRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class WarenkorbUseCasesInteractor implements
    GetWarenkorbUseCase,
    ErstelleWarenkorbFuerKundeUseCase,
    LegeArtikelInDenWarenkorbUseCase,
    ReduziereAnzahlVonArtikelInDemWarenkorbUseCase,
    EntferneArtikelAusDemWarenkorbUseCase {
    private final WarenkorbRepository warenkorbRepository;
    private final ArtikelRepository artikelRepository;
    private final LagerRepository lagerRepository;

    public WarenkorbUseCasesInteractor(WarenkorbRepository warenkorbRepository,
                                       ArtikelRepository artikelRepository,
                                       LagerRepository lagerRepository) {
        this.warenkorbRepository = warenkorbRepository;
        this.artikelRepository = artikelRepository;
        this.lagerRepository = lagerRepository;
    }

    @Override
    public void erstelleWarenkorbFuerKunde(UUID warenkorbID, UUID kundeID, BigDecimal preis) {
        Preis maxEinkaufswert = new Preis(preis);
        Warenkorb warenkorb = new Warenkorb(
            new WarenkorbID(warenkorbID),
            new KundeID(kundeID),
            maxEinkaufswert);
        warenkorbRepository.speichere(warenkorb);
    }

    @Override
    public Warenkorb getWarenkorb(UUID warenkorb) {
        WarenkorbID warenkorbID = new WarenkorbID(warenkorb);
        return warenkorbRepository.findeMit(warenkorbID);
    }

    @Override
    public void legeArtikelInDenWarenkorb(UUID artikelId, int anzahl, UUID warenkorbID) {
        Warenkorb warenkorb =  getWarenkorb(warenkorbID);
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

    @Override
    public void reduziereAnzahlVonArtikelInDemWarenkorb(UUID artikelId, int anzahl, UUID warenkorbID) {
        Warenkorb warenkorb =  getWarenkorb(warenkorbID);
        Artikel artikel = artikelRepository.findeMit(new ArtikelID(artikelId));
        Lager lager = lagerRepository.findeMit(artikelId);

        warenkorb.reduziere(artikel, new Anzahl(anzahl));
        lager.fuegeHinzu(artikel.artikelID(), new Anzahl(anzahl));

        warenkorbRepository.speichere(warenkorb);
        lagerRepository.speichere(lager);
    }
}
