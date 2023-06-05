package de.hhu.accso.warenkorb.hexagonal.application.port.out;

import de.hhu.accso.warenkorb.hexagonal.domain.artikel.Artikel;
import de.hhu.accso.warenkorb.hexagonal.domain.artikel.ArtikelID;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtikelRepository {
    Artikel findeMit(ArtikelID artikelId);
}
