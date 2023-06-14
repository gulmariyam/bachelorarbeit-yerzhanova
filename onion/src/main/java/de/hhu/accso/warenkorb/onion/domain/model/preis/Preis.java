package de.hhu.accso.warenkorb.onion.domain.model.preis;

import java.math.BigDecimal;

public record Preis(BigDecimal betrag) {
    public Preis {
        if (betrag.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Betrag darf nicht negativ sein");
        }
    }

    public Preis erhoeheUm(Preis betrag) {
        return new Preis(this.betrag.add(betrag.betrag));
    }

    public Preis reduziereUm(Preis betrag) {
        return new Preis(this.betrag.subtract(betrag.betrag));
    }

    public boolean istGroesserAls(Preis maxEinkaufswert) {
        return betrag.compareTo(maxEinkaufswert.betrag) > 0;
    }
}
