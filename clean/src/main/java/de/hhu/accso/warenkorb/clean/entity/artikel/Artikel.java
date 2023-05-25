package de.hhu.accso.warenkorb.clean.entity.artikel;

import de.hhu.accso.warenkorb.clean.entity.preis.Preis;

public record Artikel(ArtikelID artikelID, String name, Preis preis) {
    public Artikel {
        if (preis == null) {
            throw new IllegalArgumentException("Preis darf nicht null sein.");
        }
    }

    public void aenderePreis(Preis preis) {
        new Artikel(artikelID, name, preis);
    }
}
