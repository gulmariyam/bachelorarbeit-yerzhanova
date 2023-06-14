package de.hhu.accso.warenkorb.hexagonal.domain.model.warenkorb;

import de.hhu.accso.warenkorb.hexagonal.domain.model.anzahl.Anzahl;
import de.hhu.accso.warenkorb.hexagonal.domain.model.artikel.Artikel;
import de.hhu.accso.warenkorb.hexagonal.domain.model.artikel.ArtikelID;
import de.hhu.accso.warenkorb.hexagonal.domain.factory.WarenkorbzeileFactory;
import de.hhu.accso.warenkorb.hexagonal.domain.model.kunde.KundeID;
import de.hhu.accso.warenkorb.hexagonal.domain.model.preis.Preis;
import de.hhu.accso.warenkorb.hexagonal.domain.stereotypes.AggregateRoot;
import de.hhu.accso.warenkorb.hexagonal.domain.model.warenkorbzeile.Warenkorbzeile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AggregateRoot
public class Warenkorb {
    private final WarenkorbID warenkorbID;
    private final KundeID kundeID;
    private final List<Warenkorbzeile> warenkorbzeilen;
    private Preis gesamtPreis;
    private final Preis maxEinkaufswert;

    public Warenkorb(WarenkorbID warenkorbId, KundeID kundeID, Preis maxEinkaufswert) {
        this.warenkorbID = warenkorbId;
        this.kundeID = kundeID;
        this.warenkorbzeilen = new ArrayList<>();
        this.gesamtPreis = new Preis(new BigDecimal(0));
        this.maxEinkaufswert = maxEinkaufswert;
        this.validiere();
    }

    public Warenkorb(WarenkorbID warenkorbId,
                     KundeID kundeID,
                     List<Warenkorbzeile> warenkorbzeilen,
                     Preis gesamtPreis,
                     Preis maxEinkaufswert) {
        this.warenkorbID = warenkorbId;
        this.kundeID = kundeID;
        this.warenkorbzeilen = new ArrayList<>(warenkorbzeilen);
        this.gesamtPreis = gesamtPreis;
        this.maxEinkaufswert = maxEinkaufswert;
        this.validiere();
    }

    public WarenkorbID getWarenkorbID() {
        return warenkorbID;
    }

    public KundeID getKundeID() {
        return kundeID;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warenkorb warenkorb = (Warenkorb) o;
        return warenkorbID.equals(warenkorb.warenkorbID) && kundeID.equals(warenkorb.kundeID) && warenkorbzeilen.equals(warenkorb.warenkorbzeilen) && gesamtPreis.equals(warenkorb.gesamtPreis) && maxEinkaufswert.equals(warenkorb.maxEinkaufswert);
    }

    @Override
    public int hashCode() {
        return Objects.hash(warenkorbID, kundeID, warenkorbzeilen, gesamtPreis, maxEinkaufswert);
    }

    @Override
    public String toString() {
        return "Warenkorb{" +
            "id=" + warenkorbID +
            ", kundeID=" + kundeID +
            ", warenkorbzeilen=" + warenkorbzeilen +
            ", gesamtPreis=" + gesamtPreis +
            ", maxEinkaufswert=" + maxEinkaufswert +
            '}';
    }

    public Warenkorbzeile findeZeileZu(ArtikelID artikelId) {
        return warenkorbzeilen.stream()
            .filter(a -> a.getArtikelID().equals(artikelId))
            .findFirst()
            .orElse(null);
    }

    public void fuegeHinzu(Artikel artikel, Anzahl anzahl) {
        Warenkorbzeile zeileMitArtikel = findeZeileZu(artikel.artikelID());

        if(zeileMitArtikel != null) {
            zeileMitArtikel.erhoeheUm(anzahl);
        } else {
            warenkorbzeilen.add(WarenkorbzeileFactory.neueWarenkorbzeileFuer(artikel, anzahl));
        }
        // Berechnung des Gesammtpreise ggf. auf Basis des Istzustandes der Warenkorbzeilen.
        // Der GesamtPreis könnte auch einfach ein getter mit entpsrechender Funktion sein.
        // Nur ein Idee.
        Preis gesamtpreisVonArtikel = berechneGesamtpreis(artikel.preis(), anzahl);
        this.gesamtPreis = gesamtPreis.erhoeheUm(gesamtpreisVonArtikel);
        this.validiere();
    }

    public void reduziere(Artikel artikel, Anzahl anzahl) {
        Warenkorbzeile zeileMitArtikel = findeZeileZu(artikel.artikelID());
        if (zeileMitArtikel != null) {
            zeileMitArtikel.reduziereUm(anzahl);
            if (zeileMitArtikel.getAnzahl() == null) {
                warenkorbzeilen.remove(zeileMitArtikel);
            }
            Preis gesamtpreisVonArtikel = berechneGesamtpreis(artikel.preis(), anzahl);
            this.gesamtPreis = gesamtPreis.reduziereUm(gesamtpreisVonArtikel);
        }
        validiere();
    }

    public void entferne(Artikel artikel) {
        Warenkorbzeile zeileMitArtikel = findeZeileZu(artikel.artikelID());
        if (zeileMitArtikel != null) {
            Anzahl anzahlVonArtikel = zeileMitArtikel.getAnzahl();
            Preis gesamtpreisVonArtikel = berechneGesamtpreis(artikel.preis(), anzahlVonArtikel);
            warenkorbzeilen.remove(zeileMitArtikel);
            this.gesamtPreis = gesamtPreis.reduziereUm(gesamtpreisVonArtikel);
        }
        validiere();
    }

    private static Preis berechneGesamtpreis(Preis einzelPreis, Anzahl anzahl) {
        BigDecimal gesamtBetrag = einzelPreis.betrag().multiply(new BigDecimal(anzahl.anzahl()));
        return new Preis(gesamtBetrag);
    }

    private void validiere() {
        if (warenkorbID == null) {
            throw new IllegalArgumentException("WarenkorbzeileID darf nicht null sein.");
        }
        if (kundeID == null) {
            throw new IllegalArgumentException("KundeID darf nicht null sein.");
        }
        if (gesamtPreis == null) {
            throw new IllegalArgumentException("Gesamt Preis darf nicht null sein.");
        }
        if (maxEinkaufswert == null) {
            throw new IllegalArgumentException("Maximaler Einkaufswert darf nicht null sein.");
        }
        if (getGesamtPreis().istGroesserAls(maxEinkaufswert)) {
            throw new IllegalStateException("Maximaler Einkaufswert ist überschritten.");
        }
    }
}
