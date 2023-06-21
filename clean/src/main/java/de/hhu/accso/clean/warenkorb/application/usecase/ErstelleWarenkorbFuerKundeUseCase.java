package de.hhu.accso.clean.warenkorb.application.usecase;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public interface ErstelleWarenkorbFuerKundeUseCase {
    void erstelleWarenkorbFuerKunde(UUID warenkorbID, UUID kundeID, BigDecimal preis);
}
