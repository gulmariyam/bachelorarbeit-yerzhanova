package de.hhu.accso.warenkorb.onion.infrastructure.web;

import de.hhu.accso.warenkorb.onion.application.services.ApplicationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/warenkorb")
public class WarnkorbController {
    private final ApplicationService applicationService;

    public WarnkorbController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }
}
