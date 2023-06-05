package de.hhu.accso.warenkorb.hexagonal.adapter.out.persistence.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ArtikelDTO(UUID artikelId,
                         String name,
                         BigDecimal preis) {
}
