package Team5.onlinebookingsystem;

public class FlightTicketBuilder implements TicketBuilder {

    private Ticket ticket = new Ticket();

    @Override
    public void addBookingRef(String bookingRef) {
        ticket.bookingRef= bookingRef;
    }

    @Override
    public void addCustomerEmail(String customerEmail) {
        ticket.customerEmail=customerEmail;
    }

    @Override
    public void addFlightId(long flightId) {
        ticket.flightId=flightId;
    }

    @Override
    public void addSeatClass(String seatClass) {
        ticket.seatClass=seatClass;
    }

    @Override
    public void addMeal(String meal) {
        ticket.meal=meal;
    }

    @Override
    public void addLuggage(int luggage) {
        ticket.luggage=luggage;
    }

    @Override
    public void addInsurance(String insurance) {
        ticket.insurance=insurance;
    }

    @Override
    public void addAgeGroup(String ageGroup) {
        ticket.ageGroup=ageGroup;
    }

    @Override
    public Ticket getTicket(){
        return ticket;
    }
}
