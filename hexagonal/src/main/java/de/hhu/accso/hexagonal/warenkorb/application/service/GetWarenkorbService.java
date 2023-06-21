package de.hhu.accso.hexagonal.warenkorb.application.service;

import de.hhu.accso.hexagonal.warenkorb.domain.model.warenkorb.Warenkorb;
import de.hhu.accso.hexagonal.warenkorb.domain.model.warenkorb.WarenkorbID;
import de.hhu.accso.hexagonal.warenkorb.application.port.in.GetWarenkorbUseCase;
import de.hhu.accso.hexagonal.warenkorb.application.port.out.WarenkorbRepository;

import java.util.UUID;

public class GetWarenkorbService implements GetWarenkorbUseCase {
    private final WarenkorbRepository warenkorbRepository;

    public GetWarenkorbService(WarenkorbRepository warenkorbRepository) {
        this.warenkorbRepository = warenkorbRepository;
    }

    @Override
    public Warenkorb getWarenkorb(UUID warenkorb) {
        WarenkorbID warenkorbID = new WarenkorbID(warenkorb);
        return warenkorbRepository.findeMit(warenkorbID);
    }
}
