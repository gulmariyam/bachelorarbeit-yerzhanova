package de.hhu.accso.warenkorb.onion.domain.model;

public record Artikel(ArtikelId artikelId, String name, Preis preis) {

    public void aenderePreis(Preis preis) {
        if (preis == null) {
            throw new IllegalArgumentException("Preis darf nicht null sein");
        }
        new Artikel(artikelId, name, preis);
    }
}
