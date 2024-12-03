package CLI.RealTimeEventingSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketPool {
    private List<Ticket> ticketPool = new ArrayList<>();
    private int maximumCapacity;


    public TicketPool(Configuration configMaxCapacity) {
        this.maximumCapacity = configMaxCapacity.getMaxTicketCapacity();
        this.ticketPool = Collections.synchronizedList(ticketPool);
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
        System.out.println("Ticket added to the pool");
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
        System.out.println("Ticket bought");
        return ticket;
    }



}
