package coursework.oop.RealTimeEventTicketingSystem;

import coursework.oop.RealTimeEventTicketingSystem.configuration.Configuration;
import coursework.oop.RealTimeEventTicketingSystem.entity.Customer;
import coursework.oop.RealTimeEventTicketingSystem.entity.TicketPool;
import coursework.oop.RealTimeEventTicketingSystem.entity.Vendor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.InputMismatchException;
import java.util.Scanner;


@SpringBootApplication
public class RealTimeEventTicketingSystemApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RealTimeEventTicketingSystemApplication.class, args);
	}

	private static boolean isRunning = false;
	private static Vendor[] vendors;
	private static Customer[] customers;
	private static TicketPool ticketPool;




	private static void startSimulation(int totalTickets, int vendorCount, int ticketReleaseRate, int customerCount, int customerRetrievalRate) {
		isRunning = true;

		// Initialize and start Vendor threads

		for (int i = 1; i <= vendorCount; i++) {
			Configuration tempConfig = new Configuration(totalTickets / vendorCount, ticketReleaseRate);
			vendors[i - 1] = new Vendor(tempConfig, tempConfig,ticketPool);
			Thread vendorThread = new Thread(vendors[i - 1], "Vendor ID : " + i);
			vendorThread.start();
		}

		// Initialize and start Customer threads
		for (int i = 1; i <= customerCount; i++) {
			Configuration tempConfig = new Configuration(customerRetrievalRate);
			customers[i - 1] = new Customer(5, tempConfig,ticketPool);  // Retrieve tickets from the pool
			Thread customerThread = new Thread(customers[i - 1], "Customer ID : " + i);
			customerThread.start();
		}

		System.out.println("Simulation started!");
	}


	// Stop the simulation
	private static void stopSimulation() {
		isRunning = false;

		// Stop Vendor threads
		for (int i = 0; i < vendors.length; i++) {
			vendors[i].stopThread();  // Assuming a stopThread method is implemented in Vendor
		}

		// Stop Customer threads
		for (int i = 0; i < customers.length; i++) {
			customers[i].stopThread();  // Assuming a stopThread method is implemented in Customer
		}

		System.out.println("Simulation stopped!");
		System.exit(0);  // Exit the program after stopping the simulation
	}

	@Override
	public void run(String... args){

		Scanner scanner = new Scanner(System.in);

		System.out.print("""
                
                ==================================================================================================
                                               - REAL TIME EVENT TICKETING SYSTEM - 
                ==================================================================================================
                
                Welcome to the real time event ticketing system!
                Please provide the following details to configure the system.
                
                """);

		int totalTickets = 0;
		int ticketReleaseRate = 0;
		int customerRetrievalRate = 0;
		int maximumTicketCapacity = 0;
		int vendorCount = 0;
		int customerCount = 0;

		while (true) {
			try {
				System.out.print("Enter total tickets of the event : ");
				totalTickets = scanner.nextInt();
				if (totalTickets >= 1) {
					break;
				} else {
					System.out.println("Total tickets must be at least 1. Please try again.\n");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input! Please try again.\n");
				scanner.nextLine();
			}
		}

		while (true) {
			try {
				System.out.print("Enter ticket release rate (in seconds) : ");
				ticketReleaseRate = scanner.nextInt();
				if (ticketReleaseRate >= 1 && ticketReleaseRate <= 10) {
					break;
				} else {
					System.out.println("Ticket release rate must be at least 1. Please try again.\n");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input! Please try again.\n");
				scanner.nextLine();
			}
		}

		while (true) {
			try {
				System.out.print("Enter customer retrieval rate (in seconds) : ");
				customerRetrievalRate = scanner.nextInt();
				if (customerRetrievalRate >= 1 && customerRetrievalRate <= 10) {
					break;
				} else {
					System.out.println("Customer retrieval rate must be at least 1. Please try again.\n");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input! Please try again.\n");
				scanner.nextLine();
			}
		}

		while (true) {
			try {
				System.out.print("Enter maximum ticket capacity of the ticket pool : ");
				maximumTicketCapacity = scanner.nextInt();
				if (maximumTicketCapacity >= 1 && maximumTicketCapacity <= totalTickets) {
					break;
				} else {
					System.out.println("Maximum ticket capacity must be at least 1. Please try again.\n");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input! Please try again.\n");
				scanner.nextLine();
			}
		}

		Configuration configureDetails = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maximumTicketCapacity);
		configureDetails.saveFileToJson();
		configureDetails.saveFileToTextFile();

		System.out.println("""
                \nConfiguration details added successfully!
                
                Please provide the following additional details.
                """);

		while (true) {
			try {
				System.out.print("Number of vendors : ");
				vendorCount = scanner.nextInt();
				if (vendorCount >= 1) {
					break; //
				} else {
					System.out.println("Number of vendors must be at least 1. Please try again.\n");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input! Please try again.\n");
				scanner.nextLine();
			}
		}

		while (true) {
			try {
				System.out.print("Number of customers : ");
				customerCount = scanner.nextInt();
				if (customerCount >= 1) {
					break;
				} else {
					System.out.println("Number of customers must be at least 1. Please try again.\n");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input! Please try again.\n");
				scanner.nextLine();
			}
		}

		System.out.println("\nVendor and Customer count added successfully!");


		Configuration loadedConfig = configureDetails.loadFileFromJson();
		ticketPool= new TicketPool(loadedConfig);




		vendors = new Vendor[vendorCount];
		customers = new Customer[customerCount];



		// Start/Stop menu
		while (true) {
			System.out.println("\n---------REMINDER : PLEASE ENTER '2' TO STOP THE SIMULATION AND EXIT AFTER STARTING THE PROGRAM---------");
			System.out.print("\nEnter Number 1 to start the simulation : ");
			int choice = scanner.nextInt();

			switch (choice) {
				case 1:
					if (!isRunning) {
						startSimulation(totalTickets, vendorCount, ticketReleaseRate, customerCount, customerRetrievalRate);
					} else {
						System.out.println("Simulation is already running.");
					}
					break;
				case 2:
					if (isRunning) {
						stopSimulation();
					}
					System.out.println("Exiting program.");
					scanner.close();
					System.exit(0);
					return;
				default:
					System.out.println("Invalid choice. Please try again.");
			}
		}

	}

}
