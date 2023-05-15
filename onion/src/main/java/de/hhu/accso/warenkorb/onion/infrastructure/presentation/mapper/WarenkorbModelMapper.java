package de.hhu.accso.warenkorb.onion.infrastructure.presentation.mapper;

import de.hhu.accso.warenkorb.onion.domain.model.warenkorb.Warenkorb;
import de.hhu.accso.warenkorb.onion.infrastructure.presentation.models.WarenkorbModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WarenkorbModelMapper {

    WarenkorbModelMapper INSTANCE = Mappers.getMapper(WarenkorbModelMapper.class);

    WarenkorbModel vonWarenkorbZuModel(Warenkorb warenkorb);
    Warenkorb vonModelZuWarenkorb(WarenkorbModel warenkorbModel);
}
