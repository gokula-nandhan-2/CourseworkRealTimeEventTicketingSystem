
package coursework.oop.RealTimeEventTicketingSystem.service;

import coursework.oop.RealTimeEventTicketingSystem.model.*;
import org.springframework.stereotype.Service;


@Service
public class SimulationService {

    private Vendor[] vendors;
    private Customer[] customers;
    private TicketPool ticketPool;
    private boolean isRunning = false;
    private final SimulationStatus status = new SimulationStatus();

    public String configureSimulation(Configuration config) {
        int vendorCount = config.getVendorCount();
        int customerCount = config.getCustomerCount();

        ticketPool = new TicketPool(config);
        vendors = new Vendor[vendorCount];
        customers = new Customer[customerCount];

        for (int i = 0; i < vendorCount; i++) {
            vendors[i] = new Vendor(config, config, ticketPool, status);
        }

        for (int i = 0; i < customerCount; i++) {
            customers[i] = new Customer(20, config, ticketPool);
        }

        status.setRemainingTicketsToAdd(config.getTotalTickets());
        status.setRemainingTicketPoolSize(config.getMaxTicketCapacity());
        return "Simulation configured successfully!";
    }

    public String startSimulation() {
        if (isRunning) {
            return "Simulation is already running!";
        }
        isRunning = true;
        status.setRunning(true);

        for (Vendor vendor : vendors) {
            new Thread(vendor).start();
        }
        for (Customer customer : customers) {
            new Thread(customer).start();
        }
        return "Simulation started!";
    }

    public String stopSimulation() {
        if (!isRunning) {
            return "Simulation is not running!";
        }
        isRunning = false;
        status.setRunning(false);

        for (Vendor vendor : vendors) {
            vendor.stopThread();
        }
        for (Customer customer : customers) {
            customer.stopThread();
        }
        return "Simulation stopped!";
    }

    public SimulationStatus getSimulationStatus() {
        status.setTicketPoolSize(ticketPool.getTicketPoolSize());
        status.setRemainingTicketPoolSize(ticketPool.getRemainingCapacity());
        return status;
    }
}
