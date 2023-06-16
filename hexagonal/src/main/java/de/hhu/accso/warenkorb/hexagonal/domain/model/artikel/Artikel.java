package de.hhu.accso.warenkorb.hexagonal.domain.model.artikel;

import de.hhu.accso.warenkorb.hexagonal.domain.model.shared.preis.Preis;

public record Artikel(ArtikelID artikelID, String name, Preis preis) {
    public Artikel {
        if(artikelID == null) {
            throw new IllegalArgumentException("Artikel darf nicht null sein.");
        }
        if (preis == null) {
            throw new IllegalArgumentException("Preis darf nicht null sein.");
        }
    }

    public void aenderePreis(Preis preis) {
        new Artikel(artikelID, name, preis);
    }
}
