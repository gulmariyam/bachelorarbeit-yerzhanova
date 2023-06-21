package de.hhu.accso.onion.warenkorb.domain.repository;

import de.hhu.accso.onion.warenkorb.domain.model.warenkorb.Warenkorb;
import de.hhu.accso.onion.warenkorb.domain.model.warenkorb.WarenkorbID;
import org.springframework.stereotype.Repository;

@Repository
public interface WarenkorbRepository {
    Warenkorb findeMit(WarenkorbID warenkorbId);
    void speichere(Warenkorb warenkorb);
    void entferne(WarenkorbID warenkorbId);
}
