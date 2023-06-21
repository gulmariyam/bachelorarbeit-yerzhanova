package de.hhu.accso.clean.warenkorb.adapter.presentation.api;

import de.hhu.accso.clean.warenkorb.adapter.presentation.warenkorb.WarenkorbModel;
import de.hhu.accso.clean.warenkorb.adapter.presentation.warenkorb.WarenkorbModelMapper;
import de.hhu.accso.clean.warenkorb.application.usecase.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/warenkorb")
@SessionAttributes("warenkorbID")
public class WarenkorbController {
    private final GetWarenkorbUseCase getWarenkorbUseCase;
    private final ErstelleWarenkorbFuerKundeUseCase erstelleWarenkorbFuerKundeUseCase;
    private final LegeArtikelInDenWarenkorbUseCase legeArtikelInDenWarenkorbUseCase;
    private final ReduziereAnzahlVonArtikelInDemWarenkorbUseCase reduziereAnzahlVonArtikelInDemWarenkorbUseCase;
    private final EntferneArtikelAusDemWarenkorbUseCase entferneArtikelAusDemWarenkorbUseCase;

    public WarenkorbController(GetWarenkorbUseCase getWarenkorbUseCase,
                               ErstelleWarenkorbFuerKundeUseCase erstelleWarenkorbFuerKundeUseCase,
                               LegeArtikelInDenWarenkorbUseCase legeArtikelInDenWarenkorbUseCase,
                               ReduziereAnzahlVonArtikelInDemWarenkorbUseCase reduziereAnzahlVonArtikelInDemWarenkorbUseCase,
                               EntferneArtikelAusDemWarenkorbUseCase entferneArtikelAusDemWarenkorbUseCase) {
        this.getWarenkorbUseCase = getWarenkorbUseCase;
        this.erstelleWarenkorbFuerKundeUseCase = erstelleWarenkorbFuerKundeUseCase;
        this.legeArtikelInDenWarenkorbUseCase = legeArtikelInDenWarenkorbUseCase;
        this.reduziereAnzahlVonArtikelInDemWarenkorbUseCase = reduziereAnzahlVonArtikelInDemWarenkorbUseCase;
        this.entferneArtikelAusDemWarenkorbUseCase = entferneArtikelAusDemWarenkorbUseCase;
    }

    @PostMapping("/neuerWarenkorb")
    public ResponseEntity<String> erstelleWarenkorbFuerKunde(HttpSession session,
                                                             @RequestParam String warenkorbID,
                                                             @RequestParam String kundeID,
                                                             @RequestParam BigDecimal maxEinkaufswert) {
        try {
            UUID warenkorb = UUID.fromString(warenkorbID);
            UUID kunde = UUID.fromString(kundeID);
            erstelleWarenkorbFuerKundeUseCase.erstelleWarenkorbFuerKunde(warenkorb, kunde, maxEinkaufswert);
            session.setAttribute("warenkorbID", warenkorb);
            return new ResponseEntity<>("Der Warenkorb wurde erfolgreich erstellt", HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>("Fehler beim Erstellen des Warenkorbs", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<WarenkorbModel> warenkorbAnzeigen(@ModelAttribute("warenkorbID") String warenkorbID) {
        WarenkorbModel warenkorb = WarenkorbModelMapper.INSTANCE
            .vonWarenkorbZuModel(getWarenkorbUseCase.getWarenkorb(UUID.fromString(warenkorbID)));
        return new ResponseEntity<>(warenkorb, HttpStatus.OK);
    }

    @PostMapping("/fuegeHinzuArtikel/{artikelID}/{anzahl}")
    public ResponseEntity<String> artikelInDenWarenkorbLegen(@ModelAttribute("warenkorbID") String warenkorbID,
                                                             @PathVariable String artikelID,
                                                             @PathVariable int anzahl) {
        try {
            UUID warenkorb = UUID.fromString(warenkorbID);
            UUID artikel = UUID.fromString(artikelID);
            legeArtikelInDenWarenkorbUseCase.legeArtikelInDenWarenkorb(artikel, anzahl, warenkorb);
            return new ResponseEntity<>("Der Artikel wurde erfolgreich in den Warenkorb gelegt", HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            //Wird hier nicht die Message aus dem Application- bzw. Domain-Layer verschluckt?
            //Ggf. über eine Diverenzierung mit unterschiedlichen Typen nachdenken. Aber nur wenn Zeit ist.
            return new ResponseEntity<>("Der Artikel ist nicht verfügbar", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/entferneArtikel/{artikelID}")
    public ResponseEntity<String> entferneArtikelAusDemWarenkorb(@ModelAttribute("warenkorbID") String warenkorbID,
                                                                 @PathVariable String artikelID) {
        try {
            UUID warenkorb = UUID.fromString(warenkorbID);
            UUID artikel = UUID.fromString(artikelID);
            entferneArtikelAusDemWarenkorbUseCase.entferneArtikelAusDemWarenkorb(artikel, warenkorb);
            return new ResponseEntity<>("Der Artikel wurde erfolgreich aus dem Warenkorb entfernt", HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>("Der Artikel konnte nicht entfernt werden", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/reduziereAnzahlFuer/{artikelID}/{anzahl}")
    public ResponseEntity<String> reduziereAnzahlVonArtikelInDemWarenkorb(@ModelAttribute("warenkorbID") String warenkorbID,
                                                                          @PathVariable String artikelID,
                                                                          @PathVariable int anzahl) {
        try {
            UUID warenkorb = UUID.fromString(warenkorbID);
            UUID artikel = UUID.fromString(artikelID);
            reduziereAnzahlVonArtikelInDemWarenkorbUseCase.reduziereAnzahlVonArtikelInDemWarenkorb(artikel, anzahl, warenkorb);
            return new ResponseEntity<>("Die Anzahl von Artikel wurde erfolgreich reduziert", HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>("Die Anzahl von Artikel konnte nicht reduziert werden", HttpStatus.NOT_FOUND);
        }
    }
}
