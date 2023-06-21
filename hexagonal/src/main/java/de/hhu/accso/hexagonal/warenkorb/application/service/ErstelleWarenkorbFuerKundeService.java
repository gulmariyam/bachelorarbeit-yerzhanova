package de.hhu.accso.hexagonal.warenkorb.application.service;

import de.hhu.accso.hexagonal.warenkorb.application.port.in.ErstelleWarenkorbFuerKundeUseCase;
import de.hhu.accso.hexagonal.warenkorb.application.port.out.WarenkorbRepository;
import de.hhu.accso.hexagonal.warenkorb.domain.model.kunde.KundeID;
import de.hhu.accso.hexagonal.warenkorb.domain.model.shared.preis.Preis;
import de.hhu.accso.hexagonal.warenkorb.domain.model.warenkorb.Warenkorb;
import de.hhu.accso.hexagonal.warenkorb.domain.model.warenkorb.WarenkorbID;

import java.math.BigDecimal;
import java.util.UUID;

public class ErstelleWarenkorbFuerKundeService implements ErstelleWarenkorbFuerKundeUseCase {
    private final WarenkorbRepository warenkorbRepository;

    public ErstelleWarenkorbFuerKundeService(WarenkorbRepository warenkorbRepository) {
        this.warenkorbRepository = warenkorbRepository;
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
}
