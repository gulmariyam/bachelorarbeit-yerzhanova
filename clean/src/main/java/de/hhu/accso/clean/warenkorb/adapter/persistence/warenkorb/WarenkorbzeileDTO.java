package de.hhu.accso.clean.warenkorb.adapter.persistence.warenkorb;

import java.math.BigDecimal;
import java.util.UUID;

public record WarenkorbzeileDTO(UUID warenkorbzeileId,
                                UUID artikelId,
                                int anzahl,
                                BigDecimal preis,
                                int maxArtikelAnzahl ) {
}
