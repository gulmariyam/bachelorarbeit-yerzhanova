package de.hhu.accso.warenkorb.hexagonal.adapter.out.persistence.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record WarenkorbzeileDTO(UUID warenkorbzeileId,
                                UUID artikelId,
                                int anzahl,
                                BigDecimal preis,
                                int maxArtikelAnzahl ) {
}
