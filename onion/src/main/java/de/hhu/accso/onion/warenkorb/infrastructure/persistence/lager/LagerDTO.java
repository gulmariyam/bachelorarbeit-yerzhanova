package de.hhu.accso.onion.warenkorb.infrastructure.persistence.lager;

import java.util.List;
import java.util.UUID;

public record LagerDTO(UUID lagerID,
                       List<LagerbestandDTO> lagerbestaende) {
}
