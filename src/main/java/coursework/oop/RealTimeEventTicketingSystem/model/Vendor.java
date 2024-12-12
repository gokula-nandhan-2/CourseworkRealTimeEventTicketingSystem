package coursework.oop.RealTimeEventTicketingSystem.model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Vendor implements Runnable {
    private int totalTickets;
    private int ticketReleaseRate;
    private TicketPool ticketPool;
    private static final AtomicInteger ticketIdCounter = new AtomicInteger(0); // Thread-safe ticket ID generator
    private volatile boolean running = true;
    private SimulationStatus simulationStatus;


    public Vendor(Configuration totalTickets, Configuration ticketReleaseRate, TicketPool ticketPool, SimulationStatus simulationStatus) {
        this.totalTickets = totalTickets.getTotalTickets();
        this.ticketReleaseRate = ticketReleaseRate.getTicketReleaseRate();
        this.ticketPool = ticketPool;
        this.simulationStatus = simulationStatus;
    }

    @Override
    public void run() {
        for(int i = 0; i < totalTickets && running; i++) {
            int ticketId = ticketIdCounter.incrementAndGet();
            Ticket ticket = new Ticket(ticketId,"Event Name",1000);
            ticket.setReleasedDateTime(LocalDateTime.now());
            ticketPool.addTicket(ticket);
            simulationStatus.setTotalTicketsAdded(simulationStatus.getTotalTicketsAdded() + 1);
            try{
                Thread.sleep(ticketReleaseRate * 1000);
            }catch(InterruptedException e){
                if (!running)
                    break;
            }
        }
    }

    public void stopThread() {
        this.running = false; // Set the flag to false to stop the thread execution
    }
}









