package de.hhu.accso.warenkorb.hexagonal.adapter.out.persistence.mapper;

import de.hhu.accso.warenkorb.hexagonal.adapter.out.persistence.dto.LagerDTO;
import de.hhu.accso.warenkorb.hexagonal.domain.model.lager.Lager;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LagerDTOMapper {
    LagerDTOMapper INSTANCE = Mappers.getMapper(LagerDTOMapper.class);

    LagerDTO vonLagerlZuDTO(Lager lager);
    Lager vonDTOZuLager(LagerDTO lagerDTO);
}
