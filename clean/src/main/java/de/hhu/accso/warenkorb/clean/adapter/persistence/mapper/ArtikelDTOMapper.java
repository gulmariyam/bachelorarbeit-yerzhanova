package de.hhu.accso.warenkorb.clean.adapter.persistence.mapper;

import de.hhu.accso.warenkorb.clean.entity.artikel.Artikel;
import de.hhu.accso.warenkorb.clean.adapter.persistence.dto.ArtikelDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArtikelDTOMapper {
    ArtikelDTOMapper INSTANCE = Mappers.getMapper(ArtikelDTOMapper.class);

    ArtikelDTO vonArtikelZuDTO(Artikel artikel);
    Artikel vonDTOZuArtikel(ArtikelDTO artikelDTO);
}
