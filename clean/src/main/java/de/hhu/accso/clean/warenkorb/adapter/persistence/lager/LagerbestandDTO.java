package de.hhu.accso.clean.warenkorb.adapter.persistence.lager;

import java.util.UUID;

public record LagerbestandDTO(UUID lagerbestandID,
                              String artikelName,
                              UUID artikelID,
                              int reihennummer,
                              int regalnummer) {
}
