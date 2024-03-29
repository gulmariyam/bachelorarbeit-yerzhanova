package de.hhu.accso.onion.warenkorb.domain.model.lager;

import de.hhu.accso.onion.warenkorb.domain.model.lager.lagerbestand.Lagerbestand;
import de.hhu.accso.onion.warenkorb.domain.model.shared.anzahl.Anzahl;
import de.hhu.accso.onion.warenkorb.domain.model.artikel.ArtikelID;

import java.util.ArrayList;
import java.util.List;

public class Lager {
    private final LagerID lagerID;
    private final List<Lagerbestand> lagerbestaende;

    public Lager(LagerID lagerID, List<Lagerbestand> lagerbestaende) {
        this.lagerID = lagerID;
        this.lagerbestaende = new ArrayList<>();
        validiere();
    }

    public LagerID getLagerID() {
        return lagerID;
    }

    public List<Lagerbestand> getLagerbestaende() {
        return lagerbestaende;
    }

    public Lagerbestand findeBestandZu(ArtikelID artikelID) {
        return lagerbestaende.stream()
            .filter(a -> a.getArtikelID().equals(artikelID))
            .findFirst()
            .orElse(null);
    }

    public void fuegeHinzu(ArtikelID artikelID, Anzahl anzahl) {
        Lagerbestand lagerbestandMitArtikel = findeBestandZu(artikelID);
        if(lagerbestandMitArtikel != null) {
            lagerbestandMitArtikel.erhoeheUm(anzahl);
        }
    }

    public void reduziere(ArtikelID artikelID, Anzahl anzahl) {
        Lagerbestand lagerbestandMitArtikel = findeBestandZu(artikelID);
        if (lagerbestandMitArtikel != null) {
            lagerbestandMitArtikel.reduziereUm(anzahl);
        }
    }

    private void validiere() {
        if (lagerID == null) {
            throw new IllegalArgumentException("Lager darf nicht null sein.");
        }
    }
}
