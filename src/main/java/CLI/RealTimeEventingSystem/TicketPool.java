package CLI.RealTimeEventingSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketPool {
    private List<Ticket> ticketPool;
    private int maximumCapacity;




    public TicketPool(Configuration configMaxCapacity) {
        this.maximumCapacity = configMaxCapacity.getMaxTicketCapacity();
        this.ticketPool = Collections.synchronizedList(new ArrayList<>());
    }







    public synchronized void addTicket(Ticket ticket){
        while(ticketPool.size() >= maximumCapacity){
            try{
                wait();
            }catch(InterruptedException e){
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }

        }
        ticketPool.add(ticket);
        notifyAll();
        System.out.println("\nTicket released by vendor successfully!");
        System.out.println("Ticket released by(VendorID) : " +Thread.currentThread().getName());
        System.out.println("Released Ticket Details : "+ ticket);
        System.out.println("Current size of ticket pool : " + ticketPool.size());
    }

    public synchronized Ticket buyTicket(){
        while(ticketPool.isEmpty()){
            try{
                wait();
            }catch(InterruptedException e){
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }

        Ticket ticket = ticketPool.remove(0);
        notifyAll();
        System.out.println("\nTicket purchased purchased successfully!");
        System.out.println("Ticket purchased by (CustomerID) :" +Thread.currentThread().getName());
        System.out.println("Purchased Ticket Details : "+ ticket);
        System.out.println("Current ticket pool size : " + ticketPool.size());
        return ticket;
    }
}
