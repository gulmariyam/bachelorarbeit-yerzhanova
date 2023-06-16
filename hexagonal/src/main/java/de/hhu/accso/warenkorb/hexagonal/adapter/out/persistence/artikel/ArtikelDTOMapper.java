package de.hhu.accso.warenkorb.hexagonal.adapter.out.persistence.artikel;

import de.hhu.accso.warenkorb.hexagonal.domain.model.artikel.Artikel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArtikelDTOMapper {
    ArtikelDTOMapper INSTANCE = Mappers.getMapper(ArtikelDTOMapper.class);

    ArtikelDTO vonArtikelZuDTO(Artikel artikel);
    Artikel vonDTOZuArtikel(ArtikelDTO artikelDTO);
}
