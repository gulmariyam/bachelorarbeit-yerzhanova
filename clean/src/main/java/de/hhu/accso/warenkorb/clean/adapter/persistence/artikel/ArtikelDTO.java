package de.hhu.accso.warenkorb.clean.adapter.persistence.artikel;

import java.math.BigDecimal;
import java.util.UUID;

public record ArtikelDTO(UUID artikelId,
                         String name,
                         BigDecimal preis) {
}
