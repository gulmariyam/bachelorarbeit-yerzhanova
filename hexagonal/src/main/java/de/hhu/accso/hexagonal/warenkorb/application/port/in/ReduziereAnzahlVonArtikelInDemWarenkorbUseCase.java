package de.hhu.accso.hexagonal.warenkorb.application.port.in;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ReduziereAnzahlVonArtikelInDemWarenkorbUseCase {
    void reduziereAnzahlVonArtikelInDemWarenkorb(UUID artikelId, int anzahl, UUID warenkorbID);
}
