package de.hhu.accso.hexagonal.warenkorb.adapter.out.persistence.artikel;

import de.hhu.accso.hexagonal.warenkorb.application.port.out.ArtikelRepository;
import de.hhu.accso.hexagonal.warenkorb.domain.model.artikel.Artikel;
import de.hhu.accso.hexagonal.warenkorb.domain.model.artikel.ArtikelID;

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
