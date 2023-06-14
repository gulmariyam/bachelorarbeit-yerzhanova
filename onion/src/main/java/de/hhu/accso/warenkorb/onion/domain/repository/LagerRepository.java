package de.hhu.accso.warenkorb.onion.domain.repository;

import de.hhu.accso.warenkorb.onion.domain.model.lager.Lager;
import de.hhu.accso.warenkorb.onion.domain.model.lager.LagerID;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LagerRepository {
    Lager findeMit(UUID artikelID);
    void speichere(Lager lager);
    void entferne(LagerID lagerID);
}
