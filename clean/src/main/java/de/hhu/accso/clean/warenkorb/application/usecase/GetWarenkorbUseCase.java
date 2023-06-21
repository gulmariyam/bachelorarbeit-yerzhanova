package de.hhu.accso.clean.warenkorb.application.usecase;

import de.hhu.accso.clean.warenkorb.entities.warenkorb.Warenkorb;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface GetWarenkorbUseCase {
    Warenkorb getWarenkorb(UUID warenkorb);
}
