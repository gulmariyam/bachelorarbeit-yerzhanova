package de.hhu.accso.warenkorb.onion.infrastructure.presentation.warenkorb;

import java.math.BigDecimal;

public record WarenkorbzeileModel(String ID,
                                  String artikel,
                                  int anzahl,
                                  BigDecimal preis,
                                  int maxArtikelAnzahl) {
}
