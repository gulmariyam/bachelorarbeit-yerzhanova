package de.hhu.accso.clean.warenkorb.adapter.persistence.lager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LagerDAO {
    private static final LagerDAO instance = new LagerDAO();

    private final Map<UUID, LagerDTO> repo = new HashMap<>();

    public static LagerDAO getInstance() {
        return instance;
    }

    public LagerDTO read(UUID id) {
        return this.repo.get(id);
    }

    public LagerDTO readByArtikelID(UUID artikelID) {
        for (LagerDTO lager : repo.values()) {
            for (LagerbestandDTO lagerbestandDTO : lager.lagerbestaende()) {
                if (lagerbestandDTO.artikelID().equals(artikelID)) {
                    return lager;
                }
            }
        }
        return null;
    }
    public void update(LagerDTO lagerDTO) {
        if(exists(lagerDTO.lagerID())) {
            this.repo.put(lagerDTO.lagerID(), lagerDTO);
        } else {
            throw new IllegalArgumentException("Update konnte nicht ausgeführt werden.");
        }
    }

    public void delete(UUID id) {
        this.repo.remove(id);
    }

    public boolean exists(UUID id) {
        return read(id) != null;
    }
}
