package de.hhu.accso.warenkorb.onion.domain.factory;

import de.hhu.accso.warenkorb.onion.domain.model.anzahl.Anzahl;
import de.hhu.accso.warenkorb.onion.domain.model.artikel.ArtikelID;
import de.hhu.accso.warenkorb.onion.domain.model.lagerbestand.Lagerbestand;
import de.hhu.accso.warenkorb.onion.domain.model.lagerbestand.LagerbestandID;

import java.util.UUID;

public class LagerbestandFactory {
    public static Lagerbestand neuerBestand(String artikelName, ArtikelID artikelID, Anzahl anzahl, int reihennummer, int regalnummer) {
        return new Lagerbestand(
            new LagerbestandID(UUID.randomUUID()),
            artikelName,
            artikelID,
            anzahl,
            reihennummer,
            regalnummer
        );
    }
}
