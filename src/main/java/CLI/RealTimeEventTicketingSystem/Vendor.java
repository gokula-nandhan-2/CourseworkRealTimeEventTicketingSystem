package CLI.RealTimeEventTicketingSystem;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Vendor implements Runnable {
    private int totalTickets;
    private int ticketReleaseRate;
    private TicketPool ticketPool;
    private static final AtomicInteger ticketIdCounter = new AtomicInteger(0); // Thread-safe ticket ID generator
    private volatile boolean running = true;


    public Vendor(Configuration totalTickets, Configuration ticketReleaseRate, TicketPool ticketPool) {
        this.totalTickets = totalTickets.getTotalTicket();
        this.ticketReleaseRate = ticketReleaseRate.getTicketReleaseRate();
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        for(int i = 0; i < totalTickets && running; i++) {
            int ticketId = ticketIdCounter.incrementAndGet();
            Ticket ticket = new Ticket(ticketId,"Kanguva",1000);
            ticket.setReleasedDateTime(LocalDateTime.now());
            ticketPool.addTicket(ticket);
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









