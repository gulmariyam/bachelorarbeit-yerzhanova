package de.hhu.accso.warenkorb.clean.adapter.persistence.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ArtikelDTO(UUID artikelId,
                         String name,
                         BigDecimal preis) {
}
