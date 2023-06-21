package de.hhu.accso.hexagonal.warenkorb.domain.model.warenkorb;

import de.hhu.accso.hexagonal.warenkorb.domain.model.artikel.Artikel;
import de.hhu.accso.hexagonal.warenkorb.domain.model.shared.anzahl.Anzahl;

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
