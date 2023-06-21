package de.hhu.accso.onion.warenkorb.infrastructure.persistence.lager;

import java.util.UUID;

public record LagerbestandDTO(UUID lagerbestandID,
                              String artikelName,
                              UUID artikelID,
                              int reihennummer,
                              int regalnummer) {
}
