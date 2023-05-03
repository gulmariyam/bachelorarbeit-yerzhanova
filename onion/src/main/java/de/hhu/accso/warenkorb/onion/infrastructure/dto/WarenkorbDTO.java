package de.hhu.accso.warenkorb.onion.infrastructure.dto;

import de.hhu.accso.warenkorb.onion.domain.model.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record WarenkorbDTO(UUID warenkorbId,
                           UUID kundeId,
                           List<WarenkorbzeileDTO> warenkorbzeilen,
                           BigDecimal gesamtPreis,
                           BigDecimal maxEinkaufswert) {
    public static WarenkorbDTO vonWarenkorbZuDTO(Warenkorb warenkorb) {
        List<WarenkorbzeileDTO> warenkorbzeileDTOs = warenkorb.getWarenkorbzeilen().stream()
            .map(WarenkorbzeileDTO::vonWarenkorbzeileZuDTO)
            .toList();
        return new WarenkorbDTO(
            warenkorb.getWarenkorbId().warenkorbId(),
            warenkorb.getKundeId().kundeId(),
            warenkorbzeileDTOs,
            warenkorb.getGesamtPreis().betrag(),
            warenkorb.getMaxEinkaufswert().betrag());
    }

    public static Warenkorb vonDTOZuWarenkorb(WarenkorbDTO warenkorbDTO) {
        List<Warenkorbzeile> warenkorbzeilen = warenkorbDTO.warenkorbzeilen.stream()
            .map(WarenkorbzeileDTO::vonDTOZuWarenkorbzeile)
            .toList();
        return new Warenkorb(
            new WarenkorbId(warenkorbDTO.warenkorbId),
            new KundeId(warenkorbDTO.kundeId),
            warenkorbzeilen,
            new Preis(warenkorbDTO.gesamtPreis),
            new Preis(warenkorbDTO.maxEinkaufswert)
        );
    }
}
