package CLI.RealTimeEventingSystem;

import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import com.google.gson.Gson;

public class Configuration {
    private int totalTicket;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    public Configuration(int totalTicket, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        this.totalTicket = totalTicket;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public int getTotalTicket() {
        return totalTicket;
    }

    public void setTotalTicket(int totalTicket) {
        this.totalTicket = totalTicket;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    //save to json file
    public void saveFile() {
        Gson gson = new Gson();
        try (FileWriter writeFile = new FileWriter("configuration.txt")){
            gson.toJson(this, writeFile);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    //load details from json
    public Configuration loadFile() {
        Gson gson = new Gson();
        try(FileReader readFile = new FileReader("configuration.txt")){
            return gson.fromJson(readFile, Configuration.class);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }


}
