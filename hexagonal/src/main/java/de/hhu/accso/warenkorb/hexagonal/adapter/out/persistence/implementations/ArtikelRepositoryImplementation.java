package de.hhu.accso.warenkorb.hexagonal.adapter.out.persistence.implementations;

import de.hhu.accso.warenkorb.hexagonal.adapter.out.persistence.dao.ArtikelDAO;
import de.hhu.accso.warenkorb.hexagonal.adapter.out.persistence.dto.ArtikelDTO;
import de.hhu.accso.warenkorb.hexagonal.adapter.out.persistence.mapper.ArtikelDTOMapper;
import de.hhu.accso.warenkorb.hexagonal.application.port.out.ArtikelRepository;
import de.hhu.accso.warenkorb.hexagonal.domain.model.artikel.Artikel;
import de.hhu.accso.warenkorb.hexagonal.domain.model.artikel.ArtikelID;

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
