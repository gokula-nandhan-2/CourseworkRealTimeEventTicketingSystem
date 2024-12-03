package CLI.RealTimeEventingSystem;

import java.util.Date;

public class Ticket {
    private int ticketID;
    private String eventName;
    private int ticketPrice;

    public Ticket(int ticketID, String eventName, int ticketPrice) {
        this.ticketID = ticketID;
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
    }

    public int getTicketID() {
        return ticketID;
    }

    public String getEventName() {
        return eventName;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

}
