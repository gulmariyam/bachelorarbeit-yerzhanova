package de.hhu.accso.onion.warenkorb.domain.model.lager.lagerbestand;

import de.hhu.accso.onion.warenkorb.domain.model.artikel.ArtikelID;
import de.hhu.accso.onion.warenkorb.domain.model.shared.anzahl.Anzahl;

public class LagerbestandFactory {
    public static Lagerbestand neuerBestand(LagerbestandID lagerbestandID, String artikelName, ArtikelID artikelID, Anzahl anzahl, int reihennummer, int regalnummer) {
        return new Lagerbestand(
            lagerbestandID,
            artikelName,
            artikelID,
            anzahl,
            reihennummer,
            regalnummer
        );
    }
}
