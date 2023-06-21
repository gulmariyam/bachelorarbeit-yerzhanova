package de.hhu.accso.clean.warenkorb.adapter.presentation.warenkorb;

import java.math.BigDecimal;

public record WarenkorbzeileModel(String ID,
                                  String artikel,
                                  int anzahl,
                                  BigDecimal preis,
                                  int maxArtikelAnzahl) {
}
