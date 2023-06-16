package de.hhu.accso.warenkorb.clean.adapter.persistence.lager;

import de.hhu.accso.warenkorb.clean.entities.lager.Lager;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LagerDTOMapper {
    LagerDTOMapper INSTANCE = Mappers.getMapper(LagerDTOMapper.class);

    LagerDTO vonLagerlZuDTO(Lager lager);
    Lager vonDTOZuLager(LagerDTO lagerDTO);
}
