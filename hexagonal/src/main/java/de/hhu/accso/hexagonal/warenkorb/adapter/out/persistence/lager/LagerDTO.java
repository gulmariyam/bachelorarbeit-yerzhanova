package de.hhu.accso.hexagonal.warenkorb.adapter.out.persistence.lager;

import java.util.List;
import java.util.UUID;

public record LagerDTO(UUID lagerID,
                       List<LagerbestandDTO> lagerbestaende) {
}
