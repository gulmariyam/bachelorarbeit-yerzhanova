package de.hhu.accso.warenkorb.clean.adapter.presentation.mapper;

import de.hhu.accso.warenkorb.clean.entity.warenkorb.Warenkorb;
import de.hhu.accso.warenkorb.clean.adapter.presentation.models.WarenkorbModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WarenkorbModelMapper {

    WarenkorbModelMapper INSTANCE = Mappers.getMapper(WarenkorbModelMapper.class);

    WarenkorbModel vonWarenkorbZuModel(Warenkorb warenkorb);
    Warenkorb vonModelZuWarenkorb(WarenkorbModel warenkorbModel);
}
