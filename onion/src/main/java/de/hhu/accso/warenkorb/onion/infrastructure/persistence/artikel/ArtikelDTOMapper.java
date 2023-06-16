package de.hhu.accso.warenkorb.onion.infrastructure.persistence.artikel;

import de.hhu.accso.warenkorb.onion.domain.model.artikel.Artikel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArtikelDTOMapper {
    ArtikelDTOMapper INSTANCE = Mappers.getMapper(ArtikelDTOMapper.class);

    ArtikelDTO vonArtikelZuDTO(Artikel artikel);
    Artikel vonDTOZuArtikel(ArtikelDTO artikelDTO);
}
