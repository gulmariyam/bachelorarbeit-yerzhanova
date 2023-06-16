package de.hhu.accso.warenkorb.onion.infrastructure.persistence.lager;

import de.hhu.accso.warenkorb.onion.infrastructure.persistence.lager.LagerbestandDTO;

import java.util.List;
import java.util.UUID;

public record LagerDTO(UUID lagerID,
                       List<LagerbestandDTO> lagerbestaende) {
}
