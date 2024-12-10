package coursework.oop.RealTimeEventTicketingSystem.controller;

import coursework.oop.RealTimeEventTicketingSystem.configuration.Configuration;
import coursework.oop.RealTimeEventTicketingSystem.service.TicketingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticketing")
public class TicketingController {
    private final TicketingService ticketingService;

    public TicketingController(TicketingService ticketingService) {
        this.ticketingService = ticketingService;
    }

    @PostMapping("/configure")
    public ResponseEntity<String> configureSystem(@RequestBody Configuration config) {
        ticketingService.configure(config);
        return ResponseEntity.ok("Configuration set successfully.");
    }

    @PostMapping("/start")
    public ResponseEntity<String> startTicketingSystem() {
        ticketingService.startProgram();
        return ResponseEntity.ok("Ticketing system started.");
    }

    @PostMapping("/stop")
    public ResponseEntity<String> stopTicketingSystem() {
        ticketingService.stopProgram();
        return ResponseEntity.ok("Ticketing system stopped.");
    }
}
