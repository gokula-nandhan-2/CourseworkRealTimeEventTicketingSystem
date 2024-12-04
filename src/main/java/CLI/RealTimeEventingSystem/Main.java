package CLI.RealTimeEventingSystem;

import java.util.*;

public class Main {
    public static void main(String[] Args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter total tickets :");
        int totalTickets = scanner.nextInt();

        System.out.println("Enter ticket release rate : ");
        int ticketReleaseRate = scanner.nextInt();

        System.out.println("Enter customer retrieval rate : ");
        int customerRetrievalRate = scanner.nextInt();

        System.out.println("Enter maximum ticket capacity : ");
        int maximumTicketCapacity = scanner.nextInt();

        Configuration configureDetails = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maximumTicketCapacity);
        configureDetails.saveFile();

        Configuration loadedConfig = configureDetails.loadFile();


        //System.out.println("\n"+configureDetails.loadFile().getTotalTicket());
        //System.out.println(configureDetails.loadFile().getTicketReleaseRate());
        //System.out.println(configureDetails.loadFile().getCustomerRetrievalRate());
        //System.out.println(configureDetails.loadFile().getMaxTicketCapacity());

        TicketPool ticketPool = new TicketPool(loadedConfig);

        Vendor[] vendors = new Vendor[10]; // Creating array of vendors
        for (int i = 0; i < vendors.length; i++) {
            Configuration tempConfig = new Configuration(totalTickets / vendors.length, ticketReleaseRate, 0, 0);
            vendors[i] = new Vendor(tempConfig, tempConfig, ticketPool);
            Thread vendorThread = new Thread(vendors[i], "Vendor ID-" + i);
            vendorThread.start();
        }

        Customer[] customers = new Customer[10]; // Creating array of customers
        for (int i = 0; i < customers.length; i++) {
            Configuration tempConfig = new Configuration(0, 0, customerRetrievalRate, 0);
            customers[i] = new Customer(5,tempConfig, ticketPool); // Rerieve tickets from the pool
            Thread customerThread = new Thread(customers[i], "Customer ID-" + i);
            customerThread.start();
        }

    }
}
