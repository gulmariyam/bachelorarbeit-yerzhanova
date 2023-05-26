package de.hhu.accso.warenkorb.clean.entity.factory;

import de.hhu.accso.warenkorb.clean.entity.anzahl.Anzahl;
import de.hhu.accso.warenkorb.clean.entity.artikel.Artikel;
import de.hhu.accso.warenkorb.clean.entity.warenkorbzeile.Warenkorbzeile;
import de.hhu.accso.warenkorb.clean.entity.warenkorbzeile.WarenkorbzeileID;

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
