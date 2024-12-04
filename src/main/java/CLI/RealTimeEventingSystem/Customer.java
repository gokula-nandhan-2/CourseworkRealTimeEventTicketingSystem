package CLI.RealTimeEventingSystem;

public class Customer implements Runnable {
    private int customerRetrievelRate;
    private int quantity;
    private TicketPool ticketPool;

    public Customer(int quantity, Configuration customerRetrieveRate, TicketPool ticketPool) {
        this.quantity = quantity;
        this.customerRetrievelRate = customerRetrieveRate.getCustomerRetrievalRate();
        this.ticketPool = ticketPool;
    }

    @Override
    public void run(){
        for(int i = 0; i < quantity; i++){
            Ticket ticket = ticketPool.buyTicket();
            try{
                Thread.sleep(customerRetrievelRate * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
