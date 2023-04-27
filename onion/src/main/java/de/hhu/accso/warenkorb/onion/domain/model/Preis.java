package de.hhu.accso.warenkorb.onion.domain.model;

import java.math.BigDecimal;

public class Preis {
    private BigDecimal betrag;

    public Preis(BigDecimal betrag) {
        if (betrag.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Betrag darf nicht negativ sein");
        }
        this.betrag = betrag;
    }

    public BigDecimal getBetrag() {
        return this.betrag;
    }

    public static Preis berechneGesamtpreis(Preis einzelPreis, Anzahl anzahl) {
        BigDecimal gesamtBetrag = einzelPreis.getBetrag().multiply(new BigDecimal(anzahl.anzahl()));
        return new Preis(gesamtBetrag);
    }

    public Preis erhoeheUm(Preis betrag) {
        return new Preis(this.betrag.add(betrag.getBetrag()));
    }

    public Preis reduziereUm(Preis betrag) {
        if (betrag.getBetrag().compareTo(this.betrag) > 0) {
            throw new IllegalArgumentException("Betrag darf nicht größer sein als der aktuelle Preis");
        }
        return new Preis(this.betrag.subtract(betrag.getBetrag()));
    }

    public boolean istGroesserAls(Preis maxEinkaufswert) {
        return betrag.compareTo(maxEinkaufswert.getBetrag()) > 0;
    }
}
