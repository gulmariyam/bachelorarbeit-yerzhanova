package de.hhu.accso.warenkorb.onion.infrastructure.persistence.mapper;

import de.hhu.accso.warenkorb.onion.domain.model.warenkorb.Warenkorb;
import de.hhu.accso.warenkorb.onion.infrastructure.persistence.dto.WarenkorbDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WarenkorbDTOMapper {

    WarenkorbDTOMapper INSTANCE = Mappers.getMapper(WarenkorbDTOMapper.class);

    WarenkorbDTO vonWarenkorbZuDTO(Warenkorb warenkorb);
    Warenkorb vonDTOZuWarenkorb(WarenkorbDTO warenkorbDTO);
}
