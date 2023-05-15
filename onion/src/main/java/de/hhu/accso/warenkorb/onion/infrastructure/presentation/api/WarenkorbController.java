package de.hhu.accso.warenkorb.onion.infrastructure.presentation.api;

import de.hhu.accso.warenkorb.onion.application.services.ApplicationService;
import de.hhu.accso.warenkorb.onion.infrastructure.presentation.mapper.WarenkorbModelMapper;
import de.hhu.accso.warenkorb.onion.infrastructure.presentation.models.WarenkorbModel;
import de.hhu.accso.warenkorb.onion.infrastructure.presentation.models.WarenkorbzeileModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/warenkorb")
@SessionAttributes("warenkorbID")
public class WarenkorbController {
    private final ApplicationService applicationService;

    public WarenkorbController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/neuerWarenkorb")
    public ResponseEntity<String> erstelleWarenkorbFuerKunde(HttpSession session,
                                                             @RequestParam String warenkorbID,
                                                             @RequestParam String kundeID,
                                                             @RequestParam BigDecimal maxEinkaufswert) {
        try {
            UUID warenkorb = UUID.fromString(warenkorbID);
            UUID kunde = UUID.fromString(kundeID);
            applicationService.erstelleWarenkorbFuerKunde(warenkorb, kunde, maxEinkaufswert);
            session.setAttribute("warenkorbID", warenkorb);
            return new ResponseEntity<>("Der Warenkorb wurde erfolgreich erstellt", HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>("Fehler beim Erstellen des Warenkorbs", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/artikel")
    public ResponseEntity<List<WarenkorbzeileModel>> warenkorbAnzeigen(@ModelAttribute("warenkorbID") String warenkorbID) {
        WarenkorbModel warenkorb = WarenkorbModelMapper.INSTANCE
            .vonWarenkorbZuModel(applicationService.getWarenkorb(UUID.fromString(warenkorbID)));
        List<WarenkorbzeileModel> warenkorbzeilenModel =
            warenkorb.warenkorbzeilenModel();

        return new ResponseEntity<>(warenkorbzeilenModel, HttpStatus.OK);
    }

    @PostMapping("/neuerArtikel/{artikelID}")
    public ResponseEntity<String> artikelInDenWarenkorbLegen(@ModelAttribute("warenkorbID") String warenkorbID,
                                                             @PathVariable String artikelID) {
        try {
            UUID warenkorb = UUID.fromString(warenkorbID);
            UUID artikel = UUID.fromString(artikelID);
            applicationService.legeArtikelInDenWarenkorb(artikel, 1, warenkorb);
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
            applicationService.entferneArtikelAusDemWarenkorb(artikel, warenkorb);
            return new ResponseEntity<>("Der Artikel wurde erfolgreich aus dem Warenkorb entfernt", HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>("Der Artikel konnte nicht entfernt werden", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/reduziereAnzahl/{artikelID}")
    public ResponseEntity<String> reduziereAnzahlVonArtikelInDemWarenkorb(@ModelAttribute("warenkorbID") String warenkorbID,
                                                                          @PathVariable String artikelID) {
        try {
            UUID warenkorb = UUID.fromString(warenkorbID);
            UUID artikel = UUID.fromString(artikelID);
            applicationService.reduziereAnzahlVonArtikelInDemWarenkorb(artikel, 1, warenkorb);
            return new ResponseEntity<>("Die Anzahl von Artikel wurde erfolgreich reduziert", HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>("Die Anzahl von Artikel konnte nicht reduziert werden", HttpStatus.NOT_FOUND);
        }
    }
}
