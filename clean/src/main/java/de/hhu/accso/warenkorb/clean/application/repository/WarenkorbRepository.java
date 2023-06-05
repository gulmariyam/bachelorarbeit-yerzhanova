package de.hhu.accso.warenkorb.clean.application.repository;

import de.hhu.accso.warenkorb.clean.entities.warenkorb.Warenkorb;
import de.hhu.accso.warenkorb.clean.entities.warenkorb.WarenkorbID;
import org.springframework.stereotype.Repository;

@Repository
public interface WarenkorbRepository {
    Warenkorb findeMit(WarenkorbID warenkorbId);
    void speichere(Warenkorb warenkorb);
    void entferne(WarenkorbID warenkorbId);
}
