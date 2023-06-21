package de.hhu.accso.clean.warenkorb.application.usecase;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ReduziereAnzahlVonArtikelInDemWarenkorbUseCase {
    void reduziereAnzahlVonArtikelInDemWarenkorb(UUID artikelId, int anzahl, UUID warenkorbID);
}
