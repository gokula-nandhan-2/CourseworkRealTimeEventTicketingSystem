package CLI.RealTimeEventTicketingSystem;

import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import com.google.gson.Gson;

public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;



    public Configuration(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }


    public Configuration(int totalTickets, int ticketReleaseRate) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
    }


    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public int getTotalTicket() {
        return totalTickets;
    }


    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }


    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }


    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }


    //save to json file
    public void saveFileToJson() {
        Gson gson = new Gson();
        try (FileWriter writeFile = new FileWriter("configuration.json")){
            gson.toJson(this, writeFile);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void saveFileToTextFile() {
        try (FileWriter writeToTextFile = new FileWriter("configuration.txt")) {
            writeToTextFile.write("totalTickets : " + totalTickets + "\n");
            writeToTextFile.write("ticketReleaseRate : " + ticketReleaseRate + "\n");
            writeToTextFile.write("customerRetrievalRate : " + customerRetrievalRate + "\n");
            writeToTextFile.write("maximumTicketCapacity : " + maxTicketCapacity + "\n");
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    //load details from json
    public Configuration loadFileFromJson() {
        Gson gson = new Gson();
        try(FileReader readFile = new FileReader("configuration.json")){
            return gson.fromJson(readFile, Configuration.class);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
