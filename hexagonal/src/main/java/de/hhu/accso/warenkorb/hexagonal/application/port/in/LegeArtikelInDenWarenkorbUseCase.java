package de.hhu.accso.warenkorb.hexagonal.application.port.in;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface LegeArtikelInDenWarenkorbUseCase {
    void legeArtikelInDenWarenkorb(UUID artikelId, int anzahl, UUID warenkorbID);
}
