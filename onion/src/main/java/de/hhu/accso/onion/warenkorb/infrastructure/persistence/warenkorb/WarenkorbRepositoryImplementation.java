package de.hhu.accso.onion.warenkorb.infrastructure.persistence.warenkorb;

import de.hhu.accso.onion.warenkorb.domain.model.warenkorb.Warenkorb;
import de.hhu.accso.onion.warenkorb.domain.model.warenkorb.WarenkorbID;
import de.hhu.accso.onion.warenkorb.domain.services.warenkorb.WarenkorbRepository;

public class WarenkorbRepositoryImplementation implements WarenkorbRepository {
    private final WarenkorbDAO warenkorbDAO;

    public WarenkorbRepositoryImplementation(WarenkorbDAO warenkorbDAO) {
        this.warenkorbDAO = warenkorbDAO;
    }

    @Override
    public Warenkorb findeMit(WarenkorbID warenkorbId) {
        WarenkorbDTO warenkorbDTO = warenkorbDAO.read(warenkorbId.warenkorbID());
        return WarenkorbDTOMapper.INSTANCE.vonDTOZuWarenkorb(warenkorbDTO);
    }

    @Override
    public void speichere(Warenkorb warenkorb) {
        WarenkorbDTO warenkorbDTO = WarenkorbDTOMapper.INSTANCE.vonWarenkorbZuDTO(warenkorb);
        warenkorbDAO.update(warenkorbDTO);

    }

    @Override
    public void entferne(WarenkorbID warenkorbId) {
        warenkorbDAO.delete(warenkorbId.warenkorbID());
    }
}
