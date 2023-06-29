package de.hhu.accso.onion.warenkorb.domain.model.warenkorb.warenkorbzeile;

import de.hhu.accso.onion.warenkorb.domain.model.shared.anzahl.Anzahl;
import de.hhu.accso.onion.warenkorb.domain.model.artikel.Artikel;

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
