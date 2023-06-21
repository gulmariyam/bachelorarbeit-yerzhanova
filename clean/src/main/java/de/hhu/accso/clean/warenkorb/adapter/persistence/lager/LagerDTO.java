package de.hhu.accso.clean.warenkorb.adapter.persistence.lager;

import java.util.List;
import java.util.UUID;

public record LagerDTO(UUID lagerID,
                       List<LagerbestandDTO> lagerbestaende) {
}
