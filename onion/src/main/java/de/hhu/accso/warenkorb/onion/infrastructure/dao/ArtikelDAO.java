package de.hhu.accso.warenkorb.onion.infrastructure.dao;

import de.hhu.accso.warenkorb.onion.infrastructure.dto.ArtikelDTO;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ArtikelDAO {
    private static final ArtikelDAO instance = new ArtikelDAO();

    private final Map<UUID, ArtikelDTO> repo = new HashMap<>();

    public static ArtikelDAO getInstance() {
        return instance;
    }

    public void create(ArtikelDTO artikelDTO) {
        this.repo.put(artikelDTO.artikelId(), artikelDTO);
    }

    public ArtikelDTO read(UUID id) {
        return this.repo.get(id);
    }

    public void update(ArtikelDTO artikelDTO) {
        if(exists(artikelDTO.artikelId())) {
            this.repo.put(artikelDTO.artikelId(), artikelDTO);
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
