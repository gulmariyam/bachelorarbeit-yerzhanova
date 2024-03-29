package de.hhu.accso.onion.warenkorb.domain.model.lager.lagerbestand;

import de.hhu.accso.onion.warenkorb.domain.model.shared.anzahl.Anzahl;
import de.hhu.accso.onion.warenkorb.domain.model.artikel.ArtikelID;

public class Lagerbestand {
    private final LagerbestandID lagerbestandID;
    private final String artikelName;
    private final ArtikelID artikelID;
    private Anzahl anzahl;
    private final int reihennummer;
    private final int regalnummer;

    public Lagerbestand(LagerbestandID lagerbestandID, String artikelName, ArtikelID artikelID, Anzahl anzahl, int reihennummer, int regalnummer) {
        this.lagerbestandID = lagerbestandID;
        this.artikelName = artikelName;
        this.artikelID = artikelID;
        this.anzahl = anzahl;
        this.reihennummer = reihennummer;
        this.regalnummer = regalnummer;
        validiere();
    }

    public LagerbestandID getLagerbestandID() {
        return lagerbestandID;
    }

    public String getArtikelName() {
        return artikelName;
    }

    public ArtikelID getArtikelID() {
        return artikelID;
    }

    public Anzahl getAnzahl() {
        return anzahl;
    }

    public int getReihennummer() {
        return reihennummer;
    }

    public int getRegalnummer() {
        return regalnummer;
    }

    public void erhoeheUm(Anzahl anzahl) {
        this.anzahl = this.anzahl.erhoeheUm(anzahl);
    }

    public void reduziereUm(Anzahl anzahl) {
        this.anzahl = this.anzahl.reduziereUm(anzahl);
        validiere();
    }

    public void aendereReihennummer(int reihennummer){
        LagerbestandFactory.neuerBestand(lagerbestandID, artikelName, artikelID, anzahl, reihennummer, regalnummer);
    }

    public void aendereRegalnummer(int regalnummer){
        LagerbestandFactory.neuerBestand(lagerbestandID, artikelName, artikelID, anzahl, reihennummer, regalnummer);
    }

    private void validiere(){
        if(lagerbestandID == null){
            throw new IllegalArgumentException("LagerbestandID darf nicht null sein.");
        }
        if(artikelID == null){
            throw new IllegalArgumentException("ArtikelID darf nicht null sein.");
        }
        if(anzahl == null){
            throw new IllegalArgumentException("Anzahl darf nicht null sein.");
        }
        if(getAnzahl().anzahl() < 0) {
            throw new IllegalStateException("Die Anzahl darf nicht kleiner als 1 sein");
        }
    }
}
