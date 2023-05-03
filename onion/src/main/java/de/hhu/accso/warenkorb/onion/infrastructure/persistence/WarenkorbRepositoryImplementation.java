package de.hhu.accso.warenkorb.onion.infrastructure.persistence;

import de.hhu.accso.warenkorb.onion.application.repository.WarenkorbRepository;
import de.hhu.accso.warenkorb.onion.domain.model.Warenkorb;
import de.hhu.accso.warenkorb.onion.domain.model.WarenkorbId;
import de.hhu.accso.warenkorb.onion.infrastructure.dao.WarenkorbDAO;
import de.hhu.accso.warenkorb.onion.infrastructure.dto.WarenkorbDTO;

public class WarenkorbRepositoryImplementation implements WarenkorbRepository {
    private final WarenkorbDAO warenkorbDAO;

    public WarenkorbRepositoryImplementation(WarenkorbDAO warenkorbDAO) {
        this.warenkorbDAO = warenkorbDAO;
    }

    @Override
    public Warenkorb findeMit(WarenkorbId warenkorbId) {
        WarenkorbDTO warenkorbDTO = warenkorbDAO.read(warenkorbId.warenkorbId());
        return WarenkorbDTO.vonDTOZuWarenkorb(warenkorbDTO);
    }

    @Override
    public void speichere(Warenkorb warenkorb) {
        WarenkorbDTO warenkorbDTO = WarenkorbDTO.vonWarenkorbZuDTO(warenkorb);
        warenkorbDAO.create(warenkorbDTO);

    }

    @Override
    public void entferne(WarenkorbId warenkorbId) {
        warenkorbDAO.delete(warenkorbId.warenkorbId());
    }
}
