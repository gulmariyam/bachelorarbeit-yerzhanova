package de.hhu.accso.warenkorb.clean.application.repository;

import de.hhu.accso.warenkorb.clean.entity.warenkorb.Warenkorb;
import de.hhu.accso.warenkorb.clean.entity.warenkorb.WarenkorbID;
import org.springframework.stereotype.Repository;

@Repository
public interface WarenkorbRepository {
    Warenkorb findeMit(WarenkorbID warenkorbId);
    void speichere(Warenkorb warenkorb);
    void entferne(WarenkorbID warenkorbId);
}
