package de.hhu.accso.warenkorb.clean.entities.factory;

import de.hhu.accso.warenkorb.clean.entities.anzahl.Anzahl;
import de.hhu.accso.warenkorb.clean.entities.artikel.Artikel;
import de.hhu.accso.warenkorb.clean.entities.warenkorbzeile.Warenkorbzeile;
import de.hhu.accso.warenkorb.clean.entities.warenkorbzeile.WarenkorbzeileID;

import java.util.UUID;

public class WarenkorbzeileFactory {

    public static Warenkorbzeile neueWarenkorbzeileFuer(Artikel artikel, Anzahl anzahl) {
        return new Warenkorbzeile(
            new WarenkorbzeileID(UUID.randomUUID()),
            artikel.artikelID(),
            anzahl,
            artikel.preis(),
            new Anzahl(10));
    }
}
