package de.hhu.accso.warenkorb.hexagonal.adapter.in.web.mapper;

import de.hhu.accso.warenkorb.hexagonal.adapter.in.web.models.WarenkorbModel;
import de.hhu.accso.warenkorb.hexagonal.domain.warenkorb.Warenkorb;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WarenkorbModelMapper {

    WarenkorbModelMapper INSTANCE = Mappers.getMapper(WarenkorbModelMapper.class);

    WarenkorbModel vonWarenkorbZuModel(Warenkorb warenkorb);
    Warenkorb vonModelZuWarenkorb(WarenkorbModel warenkorbModel);
}
