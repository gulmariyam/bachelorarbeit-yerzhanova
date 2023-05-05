package de.hhu.accso.warenkorb.onion.domain.model.warenkorbzeile;

import de.hhu.accso.warenkorb.onion.domain.model.anzahl.Anzahl;
import de.hhu.accso.warenkorb.onion.domain.model.artikel.ArtikelID;
import de.hhu.accso.warenkorb.onion.domain.model.preis.Preis;

public class Warenkorbzeile {
    private final WarenkorbzeileID warenkorbzeileId;
    private final ArtikelID artikelId;
    private Anzahl anzahl;
    private final Preis preis;

    private final Anzahl maxArtikelAnzahl;

    public Warenkorbzeile(WarenkorbzeileID id, ArtikelID artikelId, Anzahl anzahl, Preis preis, Anzahl maxArtikelAnzahl) {
        this.warenkorbzeileId = id;
        this.artikelId = artikelId;
        this.anzahl = anzahl;
        this.preis = preis;
        this.maxArtikelAnzahl = maxArtikelAnzahl;
        validiere();
    }

    public WarenkorbzeileID getWarenkorbzeileId() {
        return warenkorbzeileId;
    }

    public ArtikelID getArtikelId() {
        return artikelId;
    }

    public Anzahl getAnzahl() {
        return anzahl;
    }

    public Anzahl getMaxArtikelAnzahl() {
        return maxArtikelAnzahl;
    }

    public Preis getPreis() {
        return preis;
    }

    public void erhoeheUm(Anzahl anzahl) {
        this.anzahl = this.anzahl.erhoeheUm(anzahl);
        validiere();
    }

    public void reduziereUm(Anzahl anzahl) {
        if (this.anzahl.anzahl() > 0) {
            this.anzahl = this.anzahl.reduziereUm(anzahl);
        } else {
            throw new IllegalStateException("Die Anzahl darf nicht kleiner als 1 sein");
        }
        validiere();
    }

    private void validiere(){
        if(getAnzahl().istGroesserAls(maxArtikelAnzahl)) {
            throw new IllegalStateException("Maximale Anzahl von Artikel ist überschritten.");
        }
    }
}

