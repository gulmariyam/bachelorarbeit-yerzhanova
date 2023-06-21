package de.hhu.accso.onion.warenkorb.infrastructure.persistence.warenkorb;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record WarenkorbDTO(UUID warenkorbId,
                           UUID kundeId,
                           List<WarenkorbzeileDTO> warenkorbzeilen,
                           BigDecimal gesamtPreis,
                           BigDecimal maxEinkaufswert) {
}
