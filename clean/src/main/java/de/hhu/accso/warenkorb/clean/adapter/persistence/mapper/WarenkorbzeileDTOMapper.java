package de.hhu.accso.warenkorb.clean.adapter.persistence.mapper;

import de.hhu.accso.warenkorb.clean.entity.warenkorbzeile.Warenkorbzeile;
import de.hhu.accso.warenkorb.clean.adapter.persistence.dto.WarenkorbzeileDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WarenkorbzeileDTOMapper {
    WarenkorbzeileDTOMapper INSTANCE = Mappers.getMapper(WarenkorbzeileDTOMapper.class);

    WarenkorbzeileDTO vonWarenkorbzeileZuDTO(Warenkorbzeile warenkorbzeile);
    Warenkorbzeile vonDTOZuWarenkorbzeile(WarenkorbzeileDTO warenkorbzeileDTO);
}
