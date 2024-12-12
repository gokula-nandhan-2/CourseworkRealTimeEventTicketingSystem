package coursework.oop.RealTimeEventTicketingSystem.model;


import static coursework.oop.RealTimeEventTicketingSystem.model.TicketPool.ticketPool;

public class SimulationStatus {
    private boolean isRunning;
    private int totalTicketsAdded;
    private int remainingTicketsToAdd;
    private int ticketPoolSize;
    private int remainingTicketPoolSize;
    private int totalTickets;
    private int maxTicketCapacity;

    public SimulationStatus() {

    }

    public SimulationStatus(int totalTickets,int maxTicketCapacity) {
        this.totalTickets = totalTickets;
        this.totalTicketsAdded = 0;
        this.remainingTicketsToAdd = totalTickets;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public SimulationStatus(boolean isRunning, int totalTicketsAdded, int remainingTicketsToAdd, int ticketPoolSize, int remainingTicketPoolSize) {
        this.isRunning = isRunning;
        this.totalTicketsAdded = totalTicketsAdded;
        this.remainingTicketsToAdd = remainingTicketsToAdd;
        this.ticketPoolSize = ticketPoolSize;
        this.remainingTicketPoolSize = remainingTicketPoolSize;
    }

    // Getters and setters
    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public int getTotalTicketsAdded() {
        return totalTicketsAdded;
    }

    public void setTotalTicketsAdded(int totalTicketsAdded) {
        this.totalTicketsAdded = totalTicketsAdded;
        this.remainingTicketsToAdd = totalTickets - totalTicketsAdded;
    }

    public int getRemainingTicketsToAdd() {
        return remainingTicketsToAdd;
    }

    public void setRemainingTicketsToAdd(int remainingTicketsToAdd) {
        this.remainingTicketsToAdd = remainingTicketsToAdd;
    }

    public int getTicketPoolSize() {
        return ticketPoolSize;
    }

    public void setTicketPoolSize(int ticketPoolSize) {
        this.ticketPoolSize = ticketPool.size();
    }

    public int getRemainingTicketPoolSize() {
        return remainingTicketPoolSize;
    }

    public void setRemainingTicketPoolSize(int remainingTicketPoolSize) {
        this.remainingTicketPoolSize = remainingTicketPoolSize;
        this.remainingTicketPoolSize = maxTicketCapacity - ticketPoolSize;
    }

    @Override
    public String toString() {
        return "SimulationStatus{" +
                "isRunning=" + isRunning +
                ", totalTicketsAdded=" + totalTicketsAdded +
                ", remainingTicketsToAdd=" + remainingTicketsToAdd +
                ", ticketPoolSize=" + ticketPoolSize +
                ", remainingTicketPoolSize=" + remainingTicketPoolSize +
                '}';
    }
}


