package de.hhu.accso.warenkorb.onion.domain.model;

import de.hhu.accso.warenkorb.onion.domain.model.stereotypes.AggregateRoot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@AggregateRoot
public class Warenkorb {
    private final WarenkorbId warenkorbId;
    private final KundeId kundeId;
    private final List<Warenkorbzeile> warenkorbzeilen;
    private Preis gesamtPreis;
    private final Preis maxEinkaufswert;

    public Warenkorb(WarenkorbId warenkorbId, KundeId kundeId, Preis maxEinkaufswert) {
        this.warenkorbId = warenkorbId;
        this.kundeId = kundeId;
        this.warenkorbzeilen = new ArrayList<>();
        this.maxEinkaufswert = maxEinkaufswert;
        this.validiere();
    }

    public Warenkorb(WarenkorbId warenkorbId,
                     KundeId kundeId,
                     List<Warenkorbzeile> warenkorbzeilen,
                     Preis gesamtPreis,
                     Preis maxEinkaufswert) {
        this.warenkorbId = warenkorbId;
        this.kundeId = kundeId;
        this.warenkorbzeilen = new ArrayList<>(warenkorbzeilen);
        this.gesamtPreis = gesamtPreis;
        this.maxEinkaufswert = maxEinkaufswert;
        this.validiere();
    }
    //------Getter--------//
    public WarenkorbId getWarenkorbId() {
        return warenkorbId;
    }

    public KundeId getKundeId() {
        return kundeId;
    }

    public List<Warenkorbzeile> getWarenkorbzeilen() {
        return warenkorbzeilen;
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
        return warenkorbId.equals(warenkorb.warenkorbId) && kundeId.equals(warenkorb.kundeId) && warenkorbzeilen.equals(warenkorb.warenkorbzeilen) && gesamtPreis.equals(warenkorb.gesamtPreis) && maxEinkaufswert.equals(warenkorb.maxEinkaufswert);
    }

    @Override
    public int hashCode() {
        return Objects.hash(warenkorbId, kundeId, warenkorbzeilen, gesamtPreis, maxEinkaufswert);
    }

    @Override
    public String toString() {
        return "Warenkorb{" +
            "id=" + warenkorbId +
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

    public Warenkorbzeile findeZeileZu(ArtikelId artikelId) {
        return warenkorbzeilen.stream()
            .filter(a -> a.getArtikelId().equals(artikelId))
            .findFirst()
            .orElse(null);
    }

    public void fuegeHinzu(Artikel artikel, Anzahl anzahl) {
        Warenkorbzeile zeileMitArtikel = findeZeileZu(artikel.artikelId());
        if(zeileMitArtikel != null) {
            zeileMitArtikel.erhoeheUm(anzahl);
        } else {
            warenkorbzeilen.add(new Warenkorbzeile(
                UUID.randomUUID(),
                artikel.artikelId(),
                anzahl,
                artikel.preis(),
                zeileMitArtikel.getMaxArtikelAnzahl()));
        }
        Preis gesamtpreisVonArtikel = Preis.berechneGesamtpreis(artikel.preis(), anzahl);
        this.gesamtPreis = gesamtPreis.erhoeheUm(gesamtpreisVonArtikel);
        this.validiere();
    }

    public void reduziere(Artikel artikel, Anzahl anzahl) {
        Warenkorbzeile zeileMitArtikel = findeZeileZu(artikel.artikelId());
        if (zeileMitArtikel != null) {
            zeileMitArtikel.reduziereUm(anzahl);
            if (zeileMitArtikel.getAnzahl() == null) {
                warenkorbzeilen.remove(zeileMitArtikel);
            }
            Preis gesamtpreisVonArtikel = Preis.berechneGesamtpreis(artikel.preis(), anzahl);
            this.gesamtPreis = gesamtPreis.reduziereUm(gesamtpreisVonArtikel);
        }
    }

    public void entferne(Artikel artikel) {
        Warenkorbzeile zeileMitArtikel = findeZeileZu(artikel.artikelId());
        if (zeileMitArtikel != null) {
            Anzahl anzahlVonArtikel = zeileMitArtikel.getAnzahl();
            Preis gesamtpreisVonArtikel = Preis.berechneGesamtpreis(artikel.preis(), anzahlVonArtikel);
            warenkorbzeilen.remove(zeileMitArtikel);
            this.gesamtPreis = gesamtPreis.reduziereUm(gesamtpreisVonArtikel);
        }
    }

    private void validiere() {
        if (getGesamtPreis().istGroesserAls(maxEinkaufswert)) {
            throw new IllegalStateException("Maximaler Einkaufswert ist überschritten.");
        }
    }
}
