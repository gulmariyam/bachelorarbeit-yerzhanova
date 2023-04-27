package de.hhu.accso.warenkorb.onion.domain.model;

import de.hhu.accso.warenkorb.onion.domain.model.stereotypes.AggregateRoot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@AggregateRoot
public class Warenkorb {
    private final WarenkorbId id;
    private final KundeId kundeId;
    private final List<Warenkorbzeile> warenkorbzeilen;
    private Preis gesamtPreis;
    private final Preis maxEinkaufswert;

    public Warenkorb(UUID id, KundeId kundeId, Preis maxEinkaufswert) {
        this.id = new WarenkorbId(id);
        this.kundeId = kundeId;
        this.warenkorbzeilen = new ArrayList<>();
        this.maxEinkaufswert = maxEinkaufswert;
        this.validiere();
    }
    //------Getter--------//
    public WarenkorbId getId() {
        return id;
    }

    public KundeId getKundeId() {
        return kundeId;
    }

    public Preis getGesamtPreis() {
        return gesamtPreis;
    }

    public Preis getMaxEinkaufswert() {
        return maxEinkaufswert;
    }

    //------equals, hashcode, toString--------//
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warenkorb warenkorb = (Warenkorb) o;
        return id.equals(warenkorb.id) && kundeId.equals(warenkorb.kundeId) && warenkorbzeilen.equals(warenkorb.warenkorbzeilen) && gesamtPreis.equals(warenkorb.gesamtPreis) && maxEinkaufswert.equals(warenkorb.maxEinkaufswert);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, kundeId, warenkorbzeilen, gesamtPreis, maxEinkaufswert);
    }

    @Override
    public String toString() {
        return "Warenkorb{" +
            "id=" + id +
            ", kundeId=" + kundeId +
            ", warenkorbzeilen=" + warenkorbzeilen +
            ", gesamtPreis=" + gesamtPreis +
            ", maxEinkaufswert=" + maxEinkaufswert +
            '}';
    }

    // Domain Methods //
    // artikel hinzufügen
    // artikel anzahl erhöhen -> gesamtpreis ändern
    // artikel anzahl reduzieren -> gesamtpreis ändern
    // artikel löschen -> gesamtpreis ändern

    public Warenkorbzeile findeZeileZu(Artikel artikel) {
        return warenkorbzeilen.stream()
            .filter(a -> a.getArtikel().equals(artikel))
            .findFirst()
            .orElse(null);
    }

    public void fuegeHinzu(Artikel artikel) {
        Warenkorbzeile zeileMitArtikel = findeZeileZu(artikel);
        if(zeileMitArtikel != null) {
            zeileMitArtikel.erhoeheUmEins();
        } else {
            warenkorbzeilen.add(new Warenkorbzeile(UUID.randomUUID(), artikel, new Anzahl(1), artikel.preis(), zeileMitArtikel.getMaxArtikelAnzahl()));
        }
        this.gesamtPreis = gesamtPreis.erhoeheUm(artikel.preis());
        this.validiere();
    }

    public void reduziere(Artikel artikel) {
        Warenkorbzeile zeileMitArtikel = findeZeileZu(artikel);
        if (zeileMitArtikel != null) {
            zeileMitArtikel.reduziereUmEins();
            if (zeileMitArtikel.getAnzahl() == null) {
                warenkorbzeilen.remove(zeileMitArtikel);
            }
            this.gesamtPreis = gesamtPreis.reduziereUm(artikel.preis());
        }
    }

    public void entferne(Artikel artikel) {
        Warenkorbzeile zeileMitArtikel = findeZeileZu(artikel);
        if (zeileMitArtikel != null) {
            warenkorbzeilen.remove(zeileMitArtikel);
            this.gesamtPreis = gesamtPreis.reduziereUm(zeileMitArtikel.getGesamtPreis());
        }
    }

    private void validiere() {
        if (getGesamtPreis().istGroesserAls(maxEinkaufswert)) {
            throw new IllegalStateException("Maximaler Einkaufswert ist überschritten.");
        }
    }
}
