package de.hhu.accso.warenkorb.clean.adapter.persistence.warenkorb;

import de.hhu.accso.warenkorb.clean.entities.warenkorb.Warenkorb;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WarenkorbDTOMapper {

    WarenkorbDTOMapper INSTANCE = Mappers.getMapper(WarenkorbDTOMapper.class);

    WarenkorbDTO vonWarenkorbZuDTO(Warenkorb warenkorb);
    Warenkorb vonDTOZuWarenkorb(WarenkorbDTO warenkorbDTO);
}
