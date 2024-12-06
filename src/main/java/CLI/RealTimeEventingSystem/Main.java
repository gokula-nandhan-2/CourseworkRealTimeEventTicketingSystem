package CLI.RealTimeEventingSystem;

import java.util.*;

public class Main {
    public static void main(String[] Args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("""
                      
                      ===============================================================
                                     REAL TIME TICKET EVENTING SYSTEM 
                      ===============================================================
                        
                      Welcome to the real time event ticketing system!
                      Please provide the following details to configure the system.
                                            
                      """);

        int totalTickets = 0;
        int ticketReleaseRate = 0;
        int customerRetrievalRate = 0;
        int maximumTicketCapacity = 0;
        int vendorCount = 0;
        int customerCount = 0;

        while(true){
            try{ System.out.print("Enter total tickets : ");
                totalTickets = scanner.nextInt();
                break;
            }catch(InputMismatchException e){
                System.out.println("Invalid input! Please try again.\n");
                scanner.nextLine();
            }
        }

        while(true){
            try{ System.out.print("Enter ticket release rate (in seconds) : ");
                ticketReleaseRate = scanner.nextInt();
                break;
            }catch(InputMismatchException e){
                System.out.println("Invalid input! Please try again.\n");
                scanner.nextLine();
            }
        }

        while(true){
            try{ System.out.print("Enter customer retrieval rate (in seconds) : ");
                customerRetrievalRate = scanner.nextInt();
                break;
            }catch(InputMismatchException e){
                System.out.println("Invalid input! Please try again.\n");
                scanner.nextLine();
            }
        }

        while(true){
            try{ System.out.print("Enter maximum ticket capacity : ");
                maximumTicketCapacity = scanner.nextInt();
                break;
            }catch(InputMismatchException e){
                System.out.println("Invalid input! Please try again.\n");
                scanner.nextLine();
            }
        }

        Configuration configureDetails = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maximumTicketCapacity);
        configureDetails.saveFileToJson();
        configureDetails.saveFileToTextFile();

        System.out.println("""
                \nConfiguration details added successfully!
                Please provide the following details of vendors and customers.
                """);

        while(true){
            try{ System.out.print("Enter vendor count : ");
                vendorCount = scanner.nextInt();
                break;
            }catch(InputMismatchException e){
                System.out.println("Invalid input! Please try again.\n");
                scanner.nextLine();
            }
        }

        while(true){
            try{ System.out.print("Enter customer count : ");
                customerCount = scanner.nextInt();
                break;
            }catch(InputMismatchException e){
                System.out.println("Invalid input! Please try again.\n");
                scanner.nextLine();
            }
        }

        System.out.println("\nVendor and Customer count added successfully!");








        Configuration loadedConfig = configureDetails.loadFileToJson();
        TicketPool ticketPool = new TicketPool(loadedConfig);

        Vendor[] vendors = new Vendor[vendorCount]; // Creating array of vendors
        for (int i = 0; i < vendors.length; i++) {
            Configuration tempConfig = new Configuration(totalTickets / vendors.length, ticketReleaseRate);
            vendors[i] = new Vendor(tempConfig, tempConfig, ticketPool);
            Thread vendorThread = new Thread(vendors[i], "Vendor ID : " + i);
            vendorThread.start();
        }

        Customer[] customers = new Customer[customerCount]; // Creating array of customers
        for (int i = 0; i < customers.length; i++) {
            Configuration tempConfig = new Configuration(customerRetrievalRate);
            customers[i] = new Customer(5,tempConfig, ticketPool); // Rerieve tickets from the pool
            Thread customerThread = new Thread(customers[i], "Customer ID : " + i);
            customerThread.start();
        }





    }
}
