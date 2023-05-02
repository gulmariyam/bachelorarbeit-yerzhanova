package de.hhu.accso.warenkorb.onion.domain.model;

import javax.validation.constraints.Min;

public record Anzahl(@Min(value = 0, message = "Die Anzahl darf nicht negativ sein") int anzahl) {
    public Anzahl erhoeheUm(Anzahl anzahl) {
        return new Anzahl(this.anzahl + anzahl.anzahl);
    }

    public Anzahl reduziereUm(Anzahl anzahl) {
        return new Anzahl(this.anzahl - anzahl.anzahl);
    }

    public boolean istGroesserAls(Anzahl maxArtikelAnzahl){
        return anzahl > maxArtikelAnzahl.anzahl();
    }
}
