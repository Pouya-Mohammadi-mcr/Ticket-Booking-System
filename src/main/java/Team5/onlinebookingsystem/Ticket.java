package Team5.onlinebookingsystem;

public class Ticket {

    public String bookingRef;
    public String customerEmail;
    public long flightId;
    public String seatClass;
    public String meal;
    public String priceBought;
    public int luggage;
    public String insurance;
    public String ageGroup;

   @Override
    public String toString(){
       return "Ticket=" ;
   }
}
