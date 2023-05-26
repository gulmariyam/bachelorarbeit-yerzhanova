package de.hhu.accso.warenkorb.clean.adapter.persistence.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record WarenkorbzeileDTO(UUID warenkorbzeileId,
                                UUID artikelId,
                                int anzahl,
                                BigDecimal preis,
                                int maxArtikelAnzahl ) {
}
