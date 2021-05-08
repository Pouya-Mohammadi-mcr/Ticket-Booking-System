package Team5.onlinebookingsystem;


public class TicketDirector {


    public Ticket makeTicket(TicketBuilder ticketBuilder, TicketService tService, String seatClass, String meal, String luggage, String priceBought, String insurance, String ageGroup, long flightID){

        /// building new random non existent booking refs - ck
        String bookingRef = tService.buildRandomTicketRef();
        while (tService.findByTicketRef(bookingRef).equals("yes")) {
            bookingRef = tService.buildRandomTicketRef();
        }
        //adding mandatory parameters
        ticketBuilder.addBookingRef(bookingRef);
        ticketBuilder.addSeatClass(seatClass);
        ticketBuilder.addMeal(meal);
        ticketBuilder.addPriceBought(priceBought);
        ticketBuilder.addAgeGroup(ageGroup);
        ticketBuilder.addFlightId(flightID);

        //adding optional parameters
        if (insurance.equals("yes")) {
            ticketBuilder.addInsurance(insurance);
        }
        if (!"".equals(luggage) && !"0".equals(luggage)) {
            ticketBuilder.addLuggage(luggage);
        }

        return ticketBuilder.getTicket();
    }
}
