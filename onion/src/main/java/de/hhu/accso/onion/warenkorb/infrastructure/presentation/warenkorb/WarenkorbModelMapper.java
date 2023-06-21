package de.hhu.accso.onion.warenkorb.infrastructure.presentation.warenkorb;

import de.hhu.accso.onion.warenkorb.domain.model.warenkorb.Warenkorb;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WarenkorbModelMapper {

    WarenkorbModelMapper INSTANCE = Mappers.getMapper(WarenkorbModelMapper.class);

    WarenkorbModel vonWarenkorbZuModel(Warenkorb warenkorb);
    Warenkorb vonModelZuWarenkorb(WarenkorbModel warenkorbModel);
}
