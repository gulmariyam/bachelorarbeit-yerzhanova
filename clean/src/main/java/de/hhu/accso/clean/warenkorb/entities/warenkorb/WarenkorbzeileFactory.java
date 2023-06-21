package de.hhu.accso.clean.warenkorb.entities.warenkorb;

import de.hhu.accso.clean.warenkorb.entities.artikel.Artikel;
import de.hhu.accso.clean.warenkorb.entities.shared.anzahl.Anzahl;

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
