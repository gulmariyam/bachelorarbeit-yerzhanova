package de.hhu.accso.onion.warenkorb.infrastructure.persistence.warenkorb;

import java.math.BigDecimal;
import java.util.UUID;

public record WarenkorbzeileDTO(UUID warenkorbzeileId,
                                UUID artikelId,
                                int anzahl,
                                BigDecimal preis,
                                int maxArtikelAnzahl ) {
}
