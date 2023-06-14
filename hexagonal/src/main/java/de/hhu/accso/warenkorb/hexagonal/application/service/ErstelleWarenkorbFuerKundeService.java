package de.hhu.accso.warenkorb.hexagonal.application.service;

import de.hhu.accso.warenkorb.hexagonal.application.port.in.ErstelleWarenkorbFuerKundeUseCase;
import de.hhu.accso.warenkorb.hexagonal.application.port.out.WarenkorbRepository;
import de.hhu.accso.warenkorb.hexagonal.domain.model.kunde.KundeID;
import de.hhu.accso.warenkorb.hexagonal.domain.model.preis.Preis;
import de.hhu.accso.warenkorb.hexagonal.domain.model.warenkorb.Warenkorb;
import de.hhu.accso.warenkorb.hexagonal.domain.model.warenkorb.WarenkorbID;

import java.math.BigDecimal;
import java.util.UUID;

public class ErstelleWarenkorbFuerKundeService implements ErstelleWarenkorbFuerKundeUseCase {
    private final WarenkorbRepository warenkorbRepository;

    public ErstelleWarenkorbFuerKundeService(WarenkorbRepository warenkorbRepository) {
        this.warenkorbRepository = warenkorbRepository;
    }

    public void erstelleWarenkorbFuerKunde(UUID warenkorbID, UUID kundeID, BigDecimal preis) {
        Preis maxEinkaufswert = new Preis(preis);
        Warenkorb warenkorb = new Warenkorb(
            new WarenkorbID(warenkorbID),
            new KundeID(kundeID),
            maxEinkaufswert);
        warenkorbRepository.speichere(warenkorb);
    }
}
