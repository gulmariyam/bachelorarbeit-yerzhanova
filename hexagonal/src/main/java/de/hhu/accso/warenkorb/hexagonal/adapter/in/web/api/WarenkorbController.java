package de.hhu.accso.warenkorb.hexagonal.adapter.in.web.api;

import de.hhu.accso.warenkorb.hexagonal.adapter.in.web.mapper.WarenkorbModelMapper;
import de.hhu.accso.warenkorb.hexagonal.adapter.in.web.models.WarenkorbModel;
import de.hhu.accso.warenkorb.hexagonal.application.port.in.WarenkorbUseCase;
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
    private final WarenkorbUseCase warenkorbUseCase;

    public WarenkorbController(WarenkorbUseCase applicationService) {
        this.warenkorbUseCase = applicationService;
    }

    @PostMapping("/neuerWarenkorb")
    public ResponseEntity<String> erstelleWarenkorbFuerKunde(HttpSession session,
                                                             @RequestParam String warenkorbID,
                                                             @RequestParam String kundeID,
                                                             @RequestParam BigDecimal maxEinkaufswert) {
        try {
            UUID warenkorb = UUID.fromString(warenkorbID);
            UUID kunde = UUID.fromString(kundeID);
            warenkorbUseCase.erstelleWarenkorbFuerKunde(warenkorb, kunde, maxEinkaufswert);
            session.setAttribute("warenkorbID", warenkorb);
            return new ResponseEntity<>("Der Warenkorb wurde erfolgreich erstellt", HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>("Fehler beim Erstellen des Warenkorbs", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<WarenkorbModel> warenkorbAnzeigen(@ModelAttribute("warenkorbID") String warenkorbID) {
        WarenkorbModel warenkorb = WarenkorbModelMapper.INSTANCE
            .vonWarenkorbZuModel(warenkorbUseCase.getWarenkorb(UUID.fromString(warenkorbID)));

        return new ResponseEntity<>(warenkorb, HttpStatus.OK);
    }

    @PostMapping("/fuegeHinzuArtikel/{artikelID}/{anzahl}")
    public ResponseEntity<String> artikelInDenWarenkorbLegen(@ModelAttribute("warenkorbID") String warenkorbID,
                                                             @PathVariable String artikelID,
                                                             @PathVariable int anzahl) {
        try {
            UUID warenkorb = UUID.fromString(warenkorbID);
            UUID artikel = UUID.fromString(artikelID);
            warenkorbUseCase.legeArtikelInDenWarenkorb(artikel, anzahl, warenkorb);
            return new ResponseEntity<>("Der Artikel wurde erfolgreich in den Warenkorb gelegt", HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>("Der Artikel ist nicht verfügbar", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/entferneArtikel/{artikelID}")
    public ResponseEntity<String> entferneArtikelAusDemWarenkorb(@ModelAttribute("warenkorbID") String warenkorbID,
                                                                 @PathVariable String artikelID) {
        try {
            UUID warenkorb = UUID.fromString(warenkorbID);
            UUID artikel = UUID.fromString(artikelID);
            warenkorbUseCase.entferneArtikelAusDemWarenkorb(artikel, warenkorb);
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
            warenkorbUseCase.reduziereAnzahlVonArtikelInDemWarenkorb(artikel, anzahl, warenkorb);
            return new ResponseEntity<>("Die Anzahl von Artikel wurde erfolgreich reduziert", HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>("Die Anzahl von Artikel konnte nicht reduziert werden", HttpStatus.NOT_FOUND);
        }
    }
}
