package de.hhu.accso.onion.warenkorb.infrastructure.persistence.lager;

import de.hhu.accso.onion.warenkorb.domain.model.lager.Lager;
import de.hhu.accso.onion.warenkorb.domain.model.lager.LagerID;
import de.hhu.accso.onion.warenkorb.domain.services.lager.LagerRepository;

import java.util.UUID;

public class LagerRepositoryImplementation implements LagerRepository {
    private final LagerDAO lagerDAO;

    public LagerRepositoryImplementation(LagerDAO lagerDAO) {
        this.lagerDAO = lagerDAO;
    }

    @Override
    public Lager findeMit(UUID artikelID) {
        LagerDTO lagerDTO = lagerDAO.readByArtikelID(artikelID);
        return LagerDTOMapper.INSTANCE.vonDTOZuLager(lagerDTO);
    }

    @Override
    public void speichere(Lager lager) {
        LagerDTO lagerDTO = LagerDTOMapper.INSTANCE.vonLagerlZuDTO(lager);
        lagerDAO.update(lagerDTO);
    }

    @Override
    public void entferne(LagerID lagerID) {
        lagerDAO.delete(lagerID.lagerID());

    }
}
