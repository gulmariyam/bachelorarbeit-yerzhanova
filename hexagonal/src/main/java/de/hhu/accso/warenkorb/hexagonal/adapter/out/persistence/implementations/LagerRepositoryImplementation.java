package de.hhu.accso.warenkorb.hexagonal.adapter.out.persistence.implementations;

import de.hhu.accso.warenkorb.hexagonal.adapter.out.persistence.dao.LagerDAO;
import de.hhu.accso.warenkorb.hexagonal.adapter.out.persistence.dto.LagerDTO;
import de.hhu.accso.warenkorb.hexagonal.adapter.out.persistence.mapper.LagerDTOMapper;
import de.hhu.accso.warenkorb.hexagonal.application.port.out.LagerRepository;
import de.hhu.accso.warenkorb.hexagonal.domain.model.lager.Lager;
import de.hhu.accso.warenkorb.hexagonal.domain.model.lager.LagerID;

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
