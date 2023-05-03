package de.hhu.accso.warenkorb.onion.infrastructure.dto;

import de.hhu.accso.warenkorb.onion.domain.model.*;

import java.math.BigDecimal;
import java.util.UUID;

public record WarenkorbzeileDTO(UUID warenkorbzeileId,
                                UUID artikelId,
                                int anzahl,
                                BigDecimal preis,
                                int maxArtikelAnzahl ) {
    public static WarenkorbzeileDTO vonWarenkorbzeileZuDTO(Warenkorbzeile warenkorbzeile) {
        return new WarenkorbzeileDTO(
            warenkorbzeile.getWarenkorbzeileId(),
            warenkorbzeile.getArtikelId().artikelId(),
            warenkorbzeile.getAnzahl().anzahl(),
            warenkorbzeile.getPreis().betrag(),
            warenkorbzeile.getMaxArtikelAnzahl().anzahl());
    }

    public static Warenkorbzeile vonDTOZuWarenkorbzeile(WarenkorbzeileDTO warenkorbzeileDTO) {
        return new Warenkorbzeile(
            warenkorbzeileDTO.warenkorbzeileId,
            new ArtikelId(warenkorbzeileDTO.artikelId),
            new Anzahl(warenkorbzeileDTO.anzahl),
            new Preis(warenkorbzeileDTO.preis),
            new Anzahl(warenkorbzeileDTO.anzahl)
        );
    }
}
