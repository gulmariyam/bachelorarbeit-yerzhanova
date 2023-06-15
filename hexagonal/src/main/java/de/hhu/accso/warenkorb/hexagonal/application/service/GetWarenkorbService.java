package de.hhu.accso.warenkorb.hexagonal.application.service;

import de.hhu.accso.warenkorb.hexagonal.application.port.in.GetWarenkorbUseCase;
import de.hhu.accso.warenkorb.hexagonal.application.port.out.WarenkorbRepository;
import de.hhu.accso.warenkorb.hexagonal.domain.model.warenkorb.Warenkorb;
import de.hhu.accso.warenkorb.hexagonal.domain.model.warenkorb.WarenkorbID;

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
