package de.hhu.accso.clean.warenkorb.application.lager;

import de.hhu.accso.clean.warenkorb.entities.lager.Lager;
import de.hhu.accso.clean.warenkorb.entities.lager.LagerID;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LagerRepository {
    Lager findeMit(UUID artikelID);
    void speichere(Lager lager);
    void entferne(LagerID lagerID);
}
