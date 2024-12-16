package coursework.oop.RealTimeEventTicketingSystem.controller;

import coursework.oop.RealTimeEventTicketingSystem.model.Configuration;
import coursework.oop.RealTimeEventTicketingSystem.model.SimulationStatus;
import coursework.oop.RealTimeEventTicketingSystem.service.SimulationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/simulation")
@CrossOrigin(origins = "http://localhost:4200")

public class SimulationController {

    @Autowired
    private SimulationService simulationService;

    @Autowired
    private ApplicationContext context;



    @PostMapping("/configure")
    public String configureSimulation(@RequestBody Configuration config) {
        return simulationService.configureSimulation(config);
    }


    @PostMapping("/start")
    public String startSimulation() {
        return simulationService.startSimulation();
    }

    @PostMapping("/stop")
    public String stopSimulation() {
        SpringApplication.exit(context, () -> 0);
        return simulationService.stopSimulation();
    }

    @GetMapping("/status")
    public SimulationStatus getSimulationStatus() {
        return simulationService.getSimulationStatus();
    }
}




