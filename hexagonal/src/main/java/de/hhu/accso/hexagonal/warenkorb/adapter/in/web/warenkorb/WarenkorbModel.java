package de.hhu.accso.hexagonal.warenkorb.adapter.in.web.warenkorb;

import java.math.BigDecimal;
import java.util.List;

public record WarenkorbModel(String warenkorbId,
                             String kundeID,
                             List<WarenkorbzeileModel> warenkorbzeilenModel,
                             BigDecimal preis,
                             BigDecimal maxEinkaufswert) {
}
