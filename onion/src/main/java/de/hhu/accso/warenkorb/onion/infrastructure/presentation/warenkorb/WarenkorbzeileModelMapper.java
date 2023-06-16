package de.hhu.accso.warenkorb.onion.infrastructure.presentation.warenkorb;

import de.hhu.accso.warenkorb.onion.domain.model.warenkorb.Warenkorbzeile;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WarenkorbzeileModelMapper {

    WarenkorbzeileModelMapper INSTANCE = Mappers.getMapper(WarenkorbzeileModelMapper.class);

    WarenkorbzeileModel vonWarenkorbzeileZuModel(Warenkorbzeile warenkorbzeile);
    Warenkorbzeile vonModelZuWarenkorbzeile(WarenkorbzeileModel warenkorbzeileModel);
}
