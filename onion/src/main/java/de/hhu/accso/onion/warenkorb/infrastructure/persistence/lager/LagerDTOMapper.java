package de.hhu.accso.onion.warenkorb.infrastructure.persistence.lager;

import de.hhu.accso.onion.warenkorb.domain.model.lager.Lager;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LagerDTOMapper {
    LagerDTOMapper INSTANCE = Mappers.getMapper(LagerDTOMapper.class);

    LagerDTO vonLagerlZuDTO(Lager lager);
    Lager vonDTOZuLager(LagerDTO lagerDTO);
}
