package de.hhu.accso.warenkorb.clean.entities.warenkorbzeile;

import de.hhu.accso.warenkorb.clean.entities.anzahl.Anzahl;
import de.hhu.accso.warenkorb.clean.entities.artikel.ArtikelID;
import de.hhu.accso.warenkorb.clean.entities.preis.Preis;

public class Warenkorbzeile {
    private final WarenkorbzeileID warenkorbzeileID;
    private final ArtikelID artikelID;
    private Anzahl anzahl;
    private final Preis preis;

    private final Anzahl maxArtikelAnzahl;

    public Warenkorbzeile(WarenkorbzeileID id, ArtikelID artikelId, Anzahl anzahl, Preis preis, Anzahl maxArtikelAnzahl) {
        this.warenkorbzeileID = id;
        this.artikelID = artikelId;
        this.anzahl = anzahl;
        this.preis = preis;
        this.maxArtikelAnzahl = maxArtikelAnzahl;
        validiere();
    }

    public WarenkorbzeileID getWarenkorbzeileID() {
        return warenkorbzeileID;
    }

    public ArtikelID getArtikelID() {
        return artikelID;
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
        if (warenkorbzeileID == null) {
            throw new IllegalArgumentException("WarenkorbzeileID darf nicht null sein.");
        }
        if (artikelID == null) {
            throw new IllegalArgumentException("ArtikelID darf nicht null sein.");
        }
        if (anzahl == null) {
            throw new IllegalArgumentException("Anzahl darf nicht null sein.");
        }
        if (preis == null) {
            throw new IllegalArgumentException("Preis darf nicht null sein.");
        }
        if (maxArtikelAnzahl == null) {
            throw new IllegalArgumentException("Maximale Artikel Anzahl darf nicht null sein.");
        }
        if(getAnzahl().anzahl() < 0) {
            throw new IllegalStateException("Die Anzahl darf nicht kleiner als 1 sein");
        }
        if(getAnzahl().istGroesserAls(maxArtikelAnzahl)) {
            throw new IllegalStateException("Maximale Anzahl von Artikel ist überschritten.");
        }
    }
}

