package de.hhu.accso.warenkorb.onion.domain.model.lager;

import de.hhu.accso.warenkorb.onion.domain.model.shared.anzahl.Anzahl;
import de.hhu.accso.warenkorb.onion.domain.model.artikel.ArtikelID;

import java.util.UUID;

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
