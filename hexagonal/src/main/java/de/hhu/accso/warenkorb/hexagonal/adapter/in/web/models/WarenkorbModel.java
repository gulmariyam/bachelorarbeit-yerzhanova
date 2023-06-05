package de.hhu.accso.warenkorb.hexagonal.adapter.in.web.models;

import java.math.BigDecimal;
import java.util.List;

public record WarenkorbModel(String warenkorbId,
                             String kundeID,
                             List<WarenkorbzeileModel> warenkorbzeilenModel,
                             BigDecimal preis,
                             BigDecimal maxEinkaufswert) {
}
