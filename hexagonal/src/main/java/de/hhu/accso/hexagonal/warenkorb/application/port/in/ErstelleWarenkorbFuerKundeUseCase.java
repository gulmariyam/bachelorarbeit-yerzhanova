package de.hhu.accso.hexagonal.warenkorb.application.port.in;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public interface ErstelleWarenkorbFuerKundeUseCase {
    void erstelleWarenkorbFuerKunde(UUID warenkorbID, UUID kundeID, BigDecimal preis);
}
