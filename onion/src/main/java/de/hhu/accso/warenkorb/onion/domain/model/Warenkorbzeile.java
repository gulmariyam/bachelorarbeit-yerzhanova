package de.hhu.accso.warenkorb.onion.domain.model;

import java.util.UUID;

import static de.hhu.accso.warenkorb.onion.domain.model.Preis.*;

public class Warenkorbzeile {
    private final UUID id;
    private final Artikel artikel;
    private Anzahl anzahl;
    private Preis gesamtPreis;

    private final Anzahl maxArtikelAnzahl;

    public Warenkorbzeile(UUID id, Artikel artikel, Anzahl anzahl, Preis gesamtPreis, Anzahl maxArtikelAnzahl) {
        this.id = id;
        this.artikel = artikel;
        this.anzahl = anzahl;
        this.gesamtPreis = gesamtPreis;
        this.maxArtikelAnzahl = maxArtikelAnzahl;
        validiere();
    }

    public UUID getId() {
        return id;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public Anzahl getAnzahl() {
        return anzahl;
    }

    public Anzahl getMaxArtikelAnzahl() {
        return maxArtikelAnzahl;
    }

    public Preis getGesamtPreis() {
        return gesamtPreis;
    }

    public void erhoeheUmEins() {
        this.anzahl = this.anzahl.erhoeheUmEins();
        this.gesamtPreis = berechneGesamtpreis(artikel.preis(), anzahl);
    }

    public void reduziereUmEins() {
        if (this.anzahl.anzahl() > 1) {
            this.anzahl = this.anzahl.reduziereUmEins();
            this.gesamtPreis = berechneGesamtpreis(artikel.preis(), anzahl);
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

