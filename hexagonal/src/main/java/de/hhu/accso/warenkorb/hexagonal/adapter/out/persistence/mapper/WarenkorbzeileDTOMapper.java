package de.hhu.accso.warenkorb.hexagonal.adapter.out.persistence.mapper;

import de.hhu.accso.warenkorb.hexagonal.adapter.out.persistence.dto.WarenkorbzeileDTO;
import de.hhu.accso.warenkorb.hexagonal.domain.warenkorbzeile.Warenkorbzeile;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WarenkorbzeileDTOMapper {
    WarenkorbzeileDTOMapper INSTANCE = Mappers.getMapper(WarenkorbzeileDTOMapper.class);

    WarenkorbzeileDTO vonWarenkorbzeileZuDTO(Warenkorbzeile warenkorbzeile);
    Warenkorbzeile vonDTOZuWarenkorbzeile(WarenkorbzeileDTO warenkorbzeileDTO);
}
