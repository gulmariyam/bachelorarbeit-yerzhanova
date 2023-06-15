package de.hhu.accso.warenkorb.clean.application.usecase;

import de.hhu.accso.warenkorb.clean.entities.warenkorb.Warenkorb;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface GetWarenkorbUseCase {
    Warenkorb getWarenkorb(UUID warenkorb);
}
