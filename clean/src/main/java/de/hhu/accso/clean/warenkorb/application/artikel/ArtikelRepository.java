package de.hhu.accso.clean.warenkorb.application.artikel;

import de.hhu.accso.clean.warenkorb.entities.artikel.Artikel;
import de.hhu.accso.clean.warenkorb.entities.artikel.ArtikelID;
import org.springframework.stereotype.Repository;


@Repository
public interface ArtikelRepository {
    Artikel findeMit(ArtikelID artikelId);
}
