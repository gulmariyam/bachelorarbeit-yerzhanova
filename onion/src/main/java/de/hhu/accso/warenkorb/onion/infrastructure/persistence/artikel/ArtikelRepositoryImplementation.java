package de.hhu.accso.warenkorb.onion.infrastructure.persistence.artikel;

import de.hhu.accso.warenkorb.onion.domain.repository.ArtikelRepository;
import de.hhu.accso.warenkorb.onion.domain.model.artikel.Artikel;
import de.hhu.accso.warenkorb.onion.domain.model.artikel.ArtikelID;

public class ArtikelRepositoryImplementation implements ArtikelRepository {

    private final ArtikelDAO artikelDAO;

    public ArtikelRepositoryImplementation(ArtikelDAO artikelDAO) {
        this.artikelDAO = artikelDAO;
    }

    @Override
    public Artikel findeMit(ArtikelID artikelId) {
        ArtikelDTO artikelDTO = artikelDAO.read(artikelId.artikelId());
        return ArtikelDTOMapper.INSTANCE.vonDTOZuArtikel(artikelDTO);
    }
}
