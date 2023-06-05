package de.hhu.accso.warenkorb.clean.adapter.persistence.mapper;

import de.hhu.accso.warenkorb.clean.entities.warenkorb.Warenkorb;
import de.hhu.accso.warenkorb.clean.adapter.persistence.dto.WarenkorbDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WarenkorbDTOMapper {

    WarenkorbDTOMapper INSTANCE = Mappers.getMapper(WarenkorbDTOMapper.class);

    WarenkorbDTO vonWarenkorbZuDTO(Warenkorb warenkorb);
    Warenkorb vonDTOZuWarenkorb(WarenkorbDTO warenkorbDTO);
}
