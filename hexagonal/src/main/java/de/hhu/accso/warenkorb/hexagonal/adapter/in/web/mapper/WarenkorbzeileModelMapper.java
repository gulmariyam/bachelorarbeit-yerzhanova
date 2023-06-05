package de.hhu.accso.warenkorb.hexagonal.adapter.in.web.mapper;

import de.hhu.accso.warenkorb.hexagonal.adapter.in.web.models.WarenkorbzeileModel;
import de.hhu.accso.warenkorb.hexagonal.domain.warenkorbzeile.Warenkorbzeile;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WarenkorbzeileModelMapper {

    WarenkorbzeileModelMapper INSTANCE = Mappers.getMapper(WarenkorbzeileModelMapper.class);

    WarenkorbzeileModel vonWarenkorbzeileZuModel(Warenkorbzeile warenkorbzeile);
    Warenkorbzeile vonModelZuWarenkorbzeile(WarenkorbzeileModel warenkorbzeileModel);
}
