package de.hhu.accso.clean.warenkorb.adapter.persistence.artikel;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ArtikelDAO {
    private static final ArtikelDAO instance = new ArtikelDAO();

    private final Map<UUID, ArtikelDTO> repo = new HashMap<>();

    public static ArtikelDAO getInstance() {
        return instance;
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
