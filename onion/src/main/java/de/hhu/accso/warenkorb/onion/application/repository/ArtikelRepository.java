package de.hhu.accso.warenkorb.onion.application.repository;

import de.hhu.accso.warenkorb.onion.domain.model.Artikel;
import de.hhu.accso.warenkorb.onion.domain.model.ArtikelId;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtikelRepository {
    Artikel findeMit(ArtikelId artikelId);
}
