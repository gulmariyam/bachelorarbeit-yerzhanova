package de.hhu.accso.warenkorb.onion.infrastructure.presentation.warenkorb;

import java.math.BigDecimal;
import java.util.List;

public record WarenkorbModel(String warenkorbId,
                             String kundeID,
                             List<WarenkorbzeileModel> warenkorbzeilenModel,
                             BigDecimal preis,
                             BigDecimal maxEinkaufswert) {
}
