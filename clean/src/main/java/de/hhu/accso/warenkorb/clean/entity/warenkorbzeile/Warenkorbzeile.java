package de.hhu.accso.warenkorb.clean.entity.warenkorbzeile;

import de.hhu.accso.warenkorb.clean.entity.anzahl.Anzahl;
import de.hhu.accso.warenkorb.clean.entity.artikel.ArtikelID;
import de.hhu.accso.warenkorb.clean.entity.preis.Preis;

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
        this.anzahl = this.anzahl.reduziereUm(anzahl);
        validiere();
    }

    private void validiere(){
        if(getAnzahl().anzahl() < 0) {
            throw new IllegalStateException("Die Anzahl darf nicht kleiner als 1 sein");
        }
        if(getAnzahl().istGroesserAls(maxArtikelAnzahl)) {
            throw new IllegalStateException("Maximale Anzahl von Artikel ist überschritten.");
        }
    }
}

