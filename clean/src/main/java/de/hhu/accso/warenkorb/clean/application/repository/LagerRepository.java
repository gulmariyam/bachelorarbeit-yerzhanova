package de.hhu.accso.warenkorb.clean.application.repository;

import de.hhu.accso.warenkorb.clean.entities.lager.Lager;
import de.hhu.accso.warenkorb.clean.entities.lager.LagerID;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LagerRepository {
    Lager findeMit(UUID artikelID);
    void speichere(Lager lager);
    void entferne(LagerID lagerID);
}
