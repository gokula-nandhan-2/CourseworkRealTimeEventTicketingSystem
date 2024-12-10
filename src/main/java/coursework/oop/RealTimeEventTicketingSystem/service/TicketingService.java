package coursework.oop.RealTimeEventTicketingSystem.service;

import coursework.oop.RealTimeEventTicketingSystem.configuration.Configuration;
import coursework.oop.RealTimeEventTicketingSystem.entity.Customer;
import coursework.oop.RealTimeEventTicketingSystem.entity.TicketPool;
import coursework.oop.RealTimeEventTicketingSystem.entity.Vendor;
import org.springframework.stereotype.Service;

@Service
public class TicketingService {
    private Vendor vendor;
    private Customer customer;
    private TicketPool ticketPool;
    private Thread vendorThread;
    private Thread customerThread;

    public void configure(Configuration config) {
        ticketPool = new TicketPool(config);
        vendor = new Vendor(config, config, ticketPool);
        customer = new Customer(config.getTotalTickets(), config, ticketPool);
    }

    public void startProgram() {
        vendorThread = new Thread(vendor);
        customerThread = new Thread(customer);
        vendorThread.start();
        customerThread.start();
    }

    public void stopProgram() {
        if (vendor != null) vendor.stopThread();
        if (customer != null) customer.stopThread();

        try {
            vendorThread.join();
            customerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

