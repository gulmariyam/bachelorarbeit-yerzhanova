package de.hhu.accso.warenkorb.hexagonal.domain.model.warenkorb;

import de.hhu.accso.warenkorb.hexagonal.domain.model.shared.anzahl.Anzahl;
import de.hhu.accso.warenkorb.hexagonal.domain.model.artikel.Artikel;
import de.hhu.accso.warenkorb.hexagonal.domain.model.warenkorb.Warenkorbzeile;
import de.hhu.accso.warenkorb.hexagonal.domain.model.warenkorb.WarenkorbzeileID;

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
