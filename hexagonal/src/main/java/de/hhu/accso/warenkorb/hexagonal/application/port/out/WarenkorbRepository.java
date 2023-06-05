package de.hhu.accso.warenkorb.hexagonal.application.port.out;

import de.hhu.accso.warenkorb.hexagonal.domain.warenkorb.Warenkorb;
import de.hhu.accso.warenkorb.hexagonal.domain.warenkorb.WarenkorbID;
import org.springframework.stereotype.Repository;

@Repository
public interface WarenkorbRepository {
    Warenkorb findeMit(WarenkorbID warenkorbId);
    void speichere(Warenkorb warenkorb);
    void entferne(WarenkorbID warenkorbId);
}
