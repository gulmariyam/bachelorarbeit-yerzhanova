package de.hhu.accso.warenkorb.clean.adapter.persistence.artikel;

import de.hhu.accso.warenkorb.clean.entities.artikel.Artikel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArtikelDTOMapper {
    ArtikelDTOMapper INSTANCE = Mappers.getMapper(ArtikelDTOMapper.class);

    ArtikelDTO vonArtikelZuDTO(Artikel artikel);
    Artikel vonDTOZuArtikel(ArtikelDTO artikelDTO);
}
