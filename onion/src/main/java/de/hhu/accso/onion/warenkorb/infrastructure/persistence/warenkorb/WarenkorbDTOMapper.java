package de.hhu.accso.onion.warenkorb.infrastructure.persistence.warenkorb;

import de.hhu.accso.onion.warenkorb.domain.model.warenkorb.Warenkorb;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WarenkorbDTOMapper {

    WarenkorbDTOMapper INSTANCE = Mappers.getMapper(WarenkorbDTOMapper.class);

    WarenkorbDTO vonWarenkorbZuDTO(Warenkorb warenkorb);
    Warenkorb vonDTOZuWarenkorb(WarenkorbDTO warenkorbDTO);
}
