package de.hhu.accso.warenkorb.onion.infrastructure.persistence.dto;

import java.util.UUID;

public record LagerbestandDTO(UUID lagerbestandID,
                              String artikelName,
                              UUID artikelID,
                              int reihennummer,
                              int regalnummer) {
}
