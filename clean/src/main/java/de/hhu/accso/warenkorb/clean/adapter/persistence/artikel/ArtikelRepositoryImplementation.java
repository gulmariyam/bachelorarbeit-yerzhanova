package de.hhu.accso.warenkorb.clean.adapter.persistence.artikel;

import de.hhu.accso.warenkorb.clean.application.repository.ArtikelRepository;
import de.hhu.accso.warenkorb.clean.entities.artikel.Artikel;
import de.hhu.accso.warenkorb.clean.entities.artikel.ArtikelID;

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
