package de.hhu.accso.warenkorb.hexagonal.adapter.out.persistence.implementations;

import de.hhu.accso.warenkorb.hexagonal.adapter.out.persistence.dao.WarenkorbDAO;
import de.hhu.accso.warenkorb.hexagonal.adapter.out.persistence.dto.WarenkorbDTO;
import de.hhu.accso.warenkorb.hexagonal.adapter.out.persistence.mapper.WarenkorbDTOMapper;
import de.hhu.accso.warenkorb.hexagonal.application.port.out.WarenkorbRepository;
import de.hhu.accso.warenkorb.hexagonal.domain.warenkorb.Warenkorb;
import de.hhu.accso.warenkorb.hexagonal.domain.warenkorb.WarenkorbID;

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
