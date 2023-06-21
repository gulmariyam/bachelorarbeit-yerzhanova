package de.hhu.accso.hexagonal.warenkorb.application.port.out;

import de.hhu.accso.hexagonal.warenkorb.domain.model.warenkorb.Warenkorb;
import de.hhu.accso.hexagonal.warenkorb.domain.model.warenkorb.WarenkorbID;
import org.springframework.stereotype.Repository;

@Repository
public interface WarenkorbRepository {
    Warenkorb findeMit(WarenkorbID warenkorbId);
    void speichere(Warenkorb warenkorb);
    void entferne(WarenkorbID warenkorbId);
}
