package de.hhu.accso.hexagonal.warenkorb.application.port.in;

import de.hhu.accso.hexagonal.warenkorb.domain.model.warenkorb.Warenkorb;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface GetWarenkorbUseCase {
    Warenkorb getWarenkorb(UUID warenkorb);
}
