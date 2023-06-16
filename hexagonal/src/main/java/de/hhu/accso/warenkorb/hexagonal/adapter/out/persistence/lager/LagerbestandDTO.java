package de.hhu.accso.warenkorb.hexagonal.adapter.out.persistence.lager;

import java.util.UUID;

public record LagerbestandDTO(UUID lagerbestandID,
                              String artikelName,
                              UUID artikelID,
                              int reihennummer,
                              int regalnummer) {
}
