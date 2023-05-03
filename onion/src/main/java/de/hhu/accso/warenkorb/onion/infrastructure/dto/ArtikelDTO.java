package de.hhu.accso.warenkorb.onion.infrastructure.dto;

import de.hhu.accso.warenkorb.onion.domain.model.Artikel;
import de.hhu.accso.warenkorb.onion.domain.model.ArtikelId;
import de.hhu.accso.warenkorb.onion.domain.model.Preis;

import java.math.BigDecimal;
import java.util.UUID;

public record ArtikelDTO(UUID artikelId,
                         String name,
                         BigDecimal preis) {
    public static ArtikelDTO vonArtikelZuDTO(Artikel artikel) {
        return new ArtikelDTO(artikel.artikelId().artikelId(), artikel.name(), artikel.preis().betrag());
    }

    public static Artikel vonDTOZuArtikel(ArtikelDTO artikelDTO) {
        return new Artikel(
            new ArtikelId(artikelDTO.artikelId),
            artikelDTO.name,
            new Preis(artikelDTO.preis));
    }
}
