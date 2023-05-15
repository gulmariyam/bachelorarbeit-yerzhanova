package de.hhu.accso.warenkorb.onion.domain.model.preis;

import de.hhu.accso.warenkorb.onion.domain.model.anzahl.Anzahl;

import java.math.BigDecimal;

public record Preis(BigDecimal betrag) {
    public Preis {
        if (betrag.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Betrag darf nicht negativ sein");
        }
    }

    public static Preis berechneGesamtpreis(Preis einzelPreis, Anzahl anzahl) {
        BigDecimal gesamtBetrag = einzelPreis.betrag.multiply(new BigDecimal(anzahl.anzahl()));
        return new Preis(gesamtBetrag);
    }

    public Preis erhoeheUm(Preis betrag) {
        return new Preis(this.betrag.add(betrag.betrag));
    }

    public Preis reduziereUm(Preis betrag) {
        if (betrag.betrag.compareTo(this.betrag) > 0) {
            throw new IllegalArgumentException("Betrag darf nicht größer sein als der aktuelle Preis");
        }
        return new Preis(this.betrag.subtract(betrag.betrag));
    }

    public boolean istGroesserAls(Preis maxEinkaufswert) {
        return betrag.compareTo(maxEinkaufswert.betrag) > 0;
    }
}