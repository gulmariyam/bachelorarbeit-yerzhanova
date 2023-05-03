package de.hhu.accso.warenkorb.onion.infrastructure.persistence;

import de.hhu.accso.warenkorb.onion.application.repository.ArtikelRepository;
import de.hhu.accso.warenkorb.onion.domain.model.Artikel;
import de.hhu.accso.warenkorb.onion.domain.model.ArtikelId;
import de.hhu.accso.warenkorb.onion.infrastructure.dao.ArtikelDAO;
import de.hhu.accso.warenkorb.onion.infrastructure.dto.ArtikelDTO;

public class ArtikelRepositoryImplementation implements ArtikelRepository {

    private final ArtikelDAO artikelDAO;

    public ArtikelRepositoryImplementation(ArtikelDAO artikelDAO) {
        this.artikelDAO = artikelDAO;
    }

    @Override
    public Artikel findeMit(ArtikelId artikelId) {
        ArtikelDTO artikelDTO = artikelDAO.read(artikelId.artikelId());
        return ArtikelDTO.vonDTOZuArtikel(artikelDTO);
    }
}
