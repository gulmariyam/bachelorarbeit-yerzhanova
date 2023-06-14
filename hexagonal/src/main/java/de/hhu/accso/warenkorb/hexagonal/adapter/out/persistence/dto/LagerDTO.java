package de.hhu.accso.warenkorb.hexagonal.adapter.out.persistence.dto;

import java.util.List;
import java.util.UUID;

public record LagerDTO(UUID lagerID,
                       List<LagerbestandDTO> lagerbestaende) {
}
