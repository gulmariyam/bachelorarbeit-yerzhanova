package de.hhu.accso.clean.warenkorb.application.usecase;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface EntferneArtikelAusDemWarenkorbUseCase {
    void entferneArtikelAusDemWarenkorb(UUID artikelId, UUID warenkorbID);
}
