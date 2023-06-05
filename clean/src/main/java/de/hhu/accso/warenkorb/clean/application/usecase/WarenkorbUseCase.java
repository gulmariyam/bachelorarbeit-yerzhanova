package de.hhu.accso.warenkorb.clean.application.usecase;

import de.hhu.accso.warenkorb.clean.entities.warenkorb.Warenkorb;

import java.math.BigDecimal;
import java.util.UUID;

public interface WarenkorbUseCase {
    Warenkorb getWarenkorb(UUID warenkorb);
    void erstelleWarenkorbFuerKunde(UUID warenkorbID, UUID kundeID, BigDecimal preis);
    void legeArtikelInDenWarenkorb(UUID artikelId, int anzahl, UUID warenkorbID);
    void entferneArtikelAusDemWarenkorb(UUID artikelId, UUID warenkorbID);
    void reduziereAnzahlVonArtikelInDemWarenkorb(UUID artikelId, int anzahl, UUID warenkorbID);
}
