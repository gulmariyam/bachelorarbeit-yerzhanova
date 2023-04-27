package de.hhu.accso.warenkorb.onion.domain.model;

import java.util.UUID;

public record Artikel(UUID id, String name, Preis preis) {

    public void aenderePreis(Preis preis) {
        if (preis == null) {
            throw new IllegalArgumentException("Preis darf nicht null sein");
        }
        new Artikel(id, name, preis);
    }
}
