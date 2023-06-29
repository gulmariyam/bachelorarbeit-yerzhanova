package de.hhu.accso.clean.warenkorb.adapter.persistence.artikel;

import de.hhu.accso.clean.warenkorb.entities.artikel.Artikel;
import de.hhu.accso.clean.warenkorb.entities.artikel.ArtikelID;
import de.hhu.accso.clean.warenkorb.application.artikel.ArtikelRepository;

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
