package de.hhu.accso.warenkorb.hexagonal.adapter.out.persistence.mapper;

import de.hhu.accso.warenkorb.hexagonal.adapter.out.persistence.dto.WarenkorbDTO;
import de.hhu.accso.warenkorb.hexagonal.domain.model.warenkorb.Warenkorb;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WarenkorbDTOMapper {

    WarenkorbDTOMapper INSTANCE = Mappers.getMapper(WarenkorbDTOMapper.class);

    WarenkorbDTO vonWarenkorbZuDTO(Warenkorb warenkorb);
    Warenkorb vonDTOZuWarenkorb(WarenkorbDTO warenkorbDTO);
}
