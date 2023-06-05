package de.hhu.accso.warenkorb.clean.entities.warenkorb;

import de.hhu.accso.warenkorb.clean.entities.anzahl.Anzahl;
import de.hhu.accso.warenkorb.clean.entities.artikel.Artikel;
import de.hhu.accso.warenkorb.clean.entities.artikel.ArtikelID;
import de.hhu.accso.warenkorb.clean.entities.kunde.KundeID;
import de.hhu.accso.warenkorb.clean.entities.preis.Preis;
import de.hhu.accso.warenkorb.clean.entities.stereotypes.AggregateRoot;
import de.hhu.accso.warenkorb.clean.entities.warenkorbzeile.Warenkorbzeile;
import de.hhu.accso.warenkorb.clean.entities.factory.WarenkorbzeileFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AggregateRoot
public class Warenkorb {
    private final WarenkorbID warenkorbId;
    private final KundeID kundeID;
    private final List<Warenkorbzeile> warenkorbzeilen;
    private Preis gesamtPreis;
    private final Preis maxEinkaufswert;

    public Warenkorb(WarenkorbID warenkorbId, KundeID kundeID, Preis maxEinkaufswert) {
        this.warenkorbId = warenkorbId;
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
        this.warenkorbId = warenkorbId;
        this.kundeID = kundeID;
        this.warenkorbzeilen = new ArrayList<>(warenkorbzeilen);
        this.gesamtPreis = gesamtPreis;
        this.maxEinkaufswert = maxEinkaufswert;
        this.validiere();
    }
    //------Getter--------//
    public WarenkorbID getWarenkorbId() {
        return warenkorbId;
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

    //------equals, hashcode, toString--------//
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warenkorb warenkorb = (Warenkorb) o;
        return warenkorbId.equals(warenkorb.warenkorbId) && kundeID.equals(warenkorb.kundeID) && warenkorbzeilen.equals(warenkorb.warenkorbzeilen) && gesamtPreis.equals(warenkorb.gesamtPreis) && maxEinkaufswert.equals(warenkorb.maxEinkaufswert);
    }

    @Override
    public int hashCode() {
        return Objects.hash(warenkorbId, kundeID, warenkorbzeilen, gesamtPreis, maxEinkaufswert);
    }

    @Override
    public String toString() {
        return "Warenkorb{" +
            "id=" + warenkorbId +
            ", kundeID=" + kundeID +
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

    private Warenkorbzeile findeZeileZu(ArtikelID artikelId) {
        return warenkorbzeilen.stream()
            .filter(a -> a.getArtikelId().equals(artikelId))
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
        if (getGesamtPreis().istGroesserAls(maxEinkaufswert)) {
            throw new IllegalStateException("Maximaler Einkaufswert ist überschritten.");
        }
    }
}
