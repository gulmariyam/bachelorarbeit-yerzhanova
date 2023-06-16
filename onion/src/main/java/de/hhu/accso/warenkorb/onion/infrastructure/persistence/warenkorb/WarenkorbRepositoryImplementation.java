package de.hhu.accso.warenkorb.onion.infrastructure.persistence.warenkorb;

import de.hhu.accso.warenkorb.onion.domain.repository.WarenkorbRepository;
import de.hhu.accso.warenkorb.onion.domain.model.warenkorb.Warenkorb;
import de.hhu.accso.warenkorb.onion.domain.model.warenkorb.WarenkorbID;

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