package de.hhu.accso.onion.warenkorb.domain.repository;

import de.hhu.accso.onion.warenkorb.domain.model.artikel.Artikel;
import de.hhu.accso.onion.warenkorb.domain.model.artikel.ArtikelID;
import org.springframework.stereotype.Repository;

// Repo in Domain Modell?
@Repository
public interface ArtikelRepository {
    Artikel findeMit(ArtikelID artikelId);
}
