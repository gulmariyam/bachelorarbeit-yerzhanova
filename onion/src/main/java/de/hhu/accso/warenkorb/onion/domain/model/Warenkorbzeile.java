package de.hhu.accso.warenkorb.onion.domain.model;

import java.util.UUID;

public class Warenkorbzeile {
    private final UUID warenkorbzeileId;
    private final ArtikelId artikelId;
    private Anzahl anzahl;
    private final Preis preis;

    private final Anzahl maxArtikelAnzahl;

    public Warenkorbzeile(UUID id, ArtikelId artikelId, Anzahl anzahl, Preis preis, Anzahl maxArtikelAnzahl) {
        this.warenkorbzeileId = id;
        this.artikelId = artikelId;
        this.anzahl = anzahl;
        this.preis = preis;
        this.maxArtikelAnzahl = maxArtikelAnzahl;
        validiere();
    }

    public UUID getWarenkorbzeileId() {
        return warenkorbzeileId;
    }

    public ArtikelId getArtikelId() {
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
    }

    public void reduziereUm(Anzahl anzahl) {
        if (this.anzahl.anzahl() > 0) {
            this.anzahl = this.anzahl.reduziereUm(anzahl);
        } else {
            throw new IllegalStateException("Die Anzahl darf nicht kleiner als 1 sein");
        }
    }

    private void validiere(){
        if(getAnzahl().istGroesserAls(maxArtikelAnzahl)) {
            throw new IllegalStateException("Maximale Anzahl von Artikel ist überschritten.");
        }
    }
}

