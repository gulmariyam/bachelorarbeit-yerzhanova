package de.hhu.accso.warenkorb.onion.domain.model;

import javax.validation.constraints.Min;

public record Anzahl(@Min(value = 0, message = "Die Anzahl darf nicht negativ sein") int anzahl) {
    public Anzahl erhoeheUmEins() {
        return new Anzahl(this.anzahl + 1);
    }

    public Anzahl reduziereUmEins() {
        return new Anzahl(this.anzahl - 1);
    }

    public boolean istGroesserAls(Anzahl maxArtikelAnzahl){
        return anzahl > maxArtikelAnzahl.anzahl();
    }
}
