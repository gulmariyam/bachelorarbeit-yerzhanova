package de.hhu.accso.warenkorb.onion.infrastructure.persistence.dto;

import java.util.List;
import java.util.UUID;

public record LagerDTO(UUID lagerID,
                       List<LagerbestandDTO> lagerbestaende) {
}
