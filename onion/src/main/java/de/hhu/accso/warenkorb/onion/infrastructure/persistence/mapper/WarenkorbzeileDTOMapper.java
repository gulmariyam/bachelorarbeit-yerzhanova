package de.hhu.accso.warenkorb.onion.infrastructure.persistence.mapper;

import de.hhu.accso.warenkorb.onion.domain.model.warenkorbzeile.Warenkorbzeile;
import de.hhu.accso.warenkorb.onion.infrastructure.persistence.dto.WarenkorbzeileDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WarenkorbzeileDTOMapper {
    WarenkorbzeileDTOMapper INSTANCE = Mappers.getMapper(WarenkorbzeileDTOMapper.class);

    WarenkorbzeileDTO vonWarenkorbzeileZuDTO(Warenkorbzeile warenkorbzeile);
    Warenkorbzeile vonDTOZuWarenkorbzeile(WarenkorbzeileDTO warenkorbzeileDTO);
}
