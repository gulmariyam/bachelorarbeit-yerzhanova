package de.hhu.accso.warenkorb.clean.application.repository;

import de.hhu.accso.warenkorb.clean.entity.artikel.Artikel;
import de.hhu.accso.warenkorb.clean.entity.artikel.ArtikelID;
import org.springframework.stereotype.Repository;


@Repository
public interface ArtikelRepository {
    Artikel findeMit(ArtikelID artikelId);
}
