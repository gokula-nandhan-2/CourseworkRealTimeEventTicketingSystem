package CLI.RealTimeEventingSystem;

import java.time.LocalDateTime;

public class Vendor implements Runnable {
    private int totalTickets;
    private int ticketReleaseRate;
    private TicketPool ticketPool;

    public Vendor(Configuration totalTickets, Configuration ticketReleaseRate, TicketPool ticketPool) {
        this.totalTickets = totalTickets.getTotalTicket();
        this.ticketReleaseRate = ticketReleaseRate.getTicketReleaseRate();
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        for(int i = 0; i < totalTickets; i++) {
            Ticket ticket = new Ticket(i+1,"Kanguva",1000);
            ticket.setReleasedDateTime(LocalDateTime.now());
            ticketPool.addTicket(ticket);
            try{
                Thread.sleep(ticketReleaseRate * 1000);
            }catch(InterruptedException e){
                throw new RuntimeException(e);
            }
        }
    }
}

