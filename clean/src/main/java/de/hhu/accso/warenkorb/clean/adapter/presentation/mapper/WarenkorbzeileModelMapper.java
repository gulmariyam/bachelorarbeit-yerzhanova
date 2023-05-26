package de.hhu.accso.warenkorb.clean.adapter.presentation.mapper;

import de.hhu.accso.warenkorb.clean.entity.warenkorbzeile.Warenkorbzeile;
import de.hhu.accso.warenkorb.clean.adapter.presentation.models.WarenkorbzeileModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WarenkorbzeileModelMapper {

    WarenkorbzeileModelMapper INSTANCE = Mappers.getMapper(WarenkorbzeileModelMapper.class);

    WarenkorbzeileModel vonWarenkorbzeileZuModel(Warenkorbzeile warenkorbzeile);
    Warenkorbzeile vonModelZuWarenkorbzeile(WarenkorbzeileModel warenkorbzeileModel);
}
