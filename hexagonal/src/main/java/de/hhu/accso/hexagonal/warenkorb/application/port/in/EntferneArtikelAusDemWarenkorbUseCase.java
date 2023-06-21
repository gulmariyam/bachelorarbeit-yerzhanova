package de.hhu.accso.hexagonal.warenkorb.application.port.in;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface EntferneArtikelAusDemWarenkorbUseCase {
    void entferneArtikelAusDemWarenkorb(UUID artikelId, UUID warenkorbID);
}
