package de.hhu.accso.hexagonal.warenkorb.application.port.out;

import de.hhu.accso.hexagonal.warenkorb.domain.model.artikel.Artikel;
import de.hhu.accso.hexagonal.warenkorb.domain.model.artikel.ArtikelID;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtikelRepository {
    Artikel findeMit(ArtikelID artikelId);
}
