package de.hhu.accso.warenkorb.onion.infrastructure.persistence.mapper;

import de.hhu.accso.warenkorb.onion.domain.model.lager.Lager;
import de.hhu.accso.warenkorb.onion.infrastructure.persistence.dto.LagerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LagerDTOMapper {
    LagerDTOMapper INSTANCE = Mappers.getMapper(LagerDTOMapper.class);

    LagerDTO vonLagerlZuDTO(Lager lager);
    Lager vonDTOZuLager(LagerDTO lagerDTO);
}