package de.hhu.accso.onion.warenkorb.infrastructure.persistence.artikel;

import de.hhu.accso.onion.warenkorb.domain.model.artikel.Artikel;
import de.hhu.accso.onion.warenkorb.domain.model.artikel.ArtikelID;
import de.hhu.accso.onion.warenkorb.domain.services.artikel.ArtikelRepository;

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
