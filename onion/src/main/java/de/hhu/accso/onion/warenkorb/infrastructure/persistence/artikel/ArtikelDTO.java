package de.hhu.accso.onion.warenkorb.infrastructure.persistence.artikel;

import java.math.BigDecimal;
import java.util.UUID;

public record ArtikelDTO(UUID artikelId,
                         String name,
                         BigDecimal preis) {
}
