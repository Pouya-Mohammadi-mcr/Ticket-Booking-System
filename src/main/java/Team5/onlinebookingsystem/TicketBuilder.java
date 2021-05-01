package Team5.onlinebookingsystem;

public interface TicketBuilder {
    public abstract void addBookingRef(String bookingRef);
    public abstract void addFlightId(long flightId);
    public abstract void addSeatClass(String seatClass);
    public abstract void addMeal(String meal);
    public abstract void addLuggage(String luggage);
    public abstract void addInsurance(String insurance);
    public abstract void addAgeGroup(String ageGroup);
    public abstract void addPriceBought(String priceBought);

    public abstract Ticket getTicket();
}
