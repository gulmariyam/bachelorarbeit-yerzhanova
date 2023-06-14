package de.hhu.accso.warenkorb.hexagonal.application.port.in;

import de.hhu.accso.warenkorb.hexagonal.domain.model.warenkorb.Warenkorb;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface GetWarenkorbUseCase {
    Warenkorb getWarenkorb(UUID warenkorb);
}
