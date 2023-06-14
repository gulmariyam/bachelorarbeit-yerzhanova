package de.hhu.accso.warenkorb.hexagonal.adapter.in.web.models;

import java.math.BigDecimal;

public record WarenkorbzeileModel(String ID,
                                  String artikel,
                                  int anzahl,
                                  BigDecimal preis,
                                  int maxArtikelAnzahl) {
}