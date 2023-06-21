package de.hhu.accso.clean.warenkorb.application.repository;

import de.hhu.accso.clean.warenkorb.entities.warenkorb.Warenkorb;
import de.hhu.accso.clean.warenkorb.entities.warenkorb.WarenkorbID;
import org.springframework.stereotype.Repository;

@Repository
public interface WarenkorbRepository {
    Warenkorb findeMit(WarenkorbID warenkorbId);
    void speichere(Warenkorb warenkorb);
    void entferne(WarenkorbID warenkorbId);
}
