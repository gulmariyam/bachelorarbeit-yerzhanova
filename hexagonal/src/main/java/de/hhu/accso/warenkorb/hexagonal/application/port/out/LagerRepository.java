package de.hhu.accso.warenkorb.hexagonal.application.port.out;

import de.hhu.accso.warenkorb.hexagonal.domain.model.lager.Lager;
import de.hhu.accso.warenkorb.hexagonal.domain.model.lager.LagerID;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LagerRepository {
    Lager findeMit(UUID artikelID);
    void speichere(Lager lager);
    void entferne(LagerID lagerID);
}
