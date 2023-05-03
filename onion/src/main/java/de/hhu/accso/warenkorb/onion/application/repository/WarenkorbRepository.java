package de.hhu.accso.warenkorb.onion.application.repository;

import de.hhu.accso.warenkorb.onion.domain.model.Warenkorb;
import de.hhu.accso.warenkorb.onion.domain.model.WarenkorbId;
import org.springframework.stereotype.Repository;

@Repository
public interface WarenkorbRepository {
    Warenkorb findeMit(WarenkorbId warenkorbId);
    void speichere(Warenkorb warenkorb);
    void entferne(WarenkorbId warenkorbId);
}
