package de.hhu.accso.warenkorb.clean.adapter.persistence.lager;

import java.util.List;
import java.util.UUID;

public record LagerDTO(UUID lagerID,
                       List<LagerbestandDTO> lagerbestaende) {
}
