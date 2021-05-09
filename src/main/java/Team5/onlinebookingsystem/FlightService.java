package Team5.onlinebookingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class FlightService {

    @Autowired
    protected FlightRepository repo;


    public List<Flight> listAll() {
        return repo.findAll();
    }

    public void save(Flight flight) {
        repo.save(flight);
    }

    public Flight get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }

    public List<Flight> find(String from, String to, String date, long tickets){
        List<Flight> matchedFlights = new ArrayList<>();
        if(to.equals("anywhere") ){
            List<Flight> allFlights;
            if(date.equals("alldates")){
                allFlights = repo.getAllConnectingFlightsAllDates(from, tickets);
            }else{
                allFlights = repo.getAllConnectingFlights(from, date, tickets);
            }
            matchedFlights.addAll(allFlights);
        }else if(date.equals("alldates")){
            List<Flight> allFlights = repo.getFlightsAllDates(from,to,tickets);
            matchedFlights.addAll(allFlights);
        }else{
            List<Flight> allFlights = listAll();
            for (Flight allFlight : allFlights) {
                if (allFlight.getFrom().equals(from) && allFlight.getTo().equals(to) && allFlight.getDate().equals(date) && allFlight.getAvailableSeats() >= tickets) {
                    matchedFlights.add(allFlight);
                }
            }
        }
        matchedFlights.removeIf(flightEntry -> flightEntry.getDepartureTime().equals("NA"));
        matchedFlights.removeIf(flightEntry -> flightEntry.getPrice().equals("NA"));
        return matchedFlights;
    }

    public List<Flight> sort(SortingStrategy sortingStrategy, List<Flight> flightList) {
        SortingContext sortingContext = new SortingContext();
        sortingContext.setStrategy(sortingStrategy);
        sortingContext.sortFlights(flightList);
        return flightList;
    }
//    Service for fetching airport-city names -- ck
    public List<String> fetchOriginAirports(String keyword){
        List<Flight> listOfFlightsFromKeyword = repo.findByOrigin(keyword);
        List<String> suggestions = new ArrayList<>();
        for (Flight listOfAirport : listOfFlightsFromKeyword) {
            suggestions.add(listOfAirport.getFrom());
        }
        Set<String> uniqueAirports = new HashSet<>(suggestions);
        return new ArrayList<>(uniqueAirports);
    }

    //    Service for fetching airport-city names -- ck
    public List<String> fetchDestinationAirports(String keyword,String origin){
        List<Flight> listOfMatchedFlights = repo.findByDestination(keyword,origin);
        List<String> suggestions = new ArrayList<>();

        for (Flight listOfAirport : listOfMatchedFlights) {
            suggestions.add(listOfAirport.getTo());
        }
        Set<String> uniqueAirports = new HashSet<>(suggestions);
        return new ArrayList<>(uniqueAirports);
    }

    //    Service for fetching the flight information by ID -- ck
    public Flight fetchById(long id){
        List<Flight> theFlight;
        theFlight = repo.getFlightById(id);
        if (theFlight.size() == 0) {
            return null;
        }
        return theFlight.get(0);
    }

    public void decreaseCapacity(long id, long decrementValue) {
        Flight flight = get(id);
        long seats = flight.getAvailableSeats()-decrementValue ;
        repo.updateSeats(seats,id);
    }

    public  Map<String, Boolean> validation(Ticket ticketInfo, Customer customerInfo,
                                     boolean wrongBookingRef, boolean wrongEmail){
        Map<String, Boolean> validationData = new HashMap<>();

        if(ticketInfo==null){
            wrongBookingRef=true;
        }
        if(customerInfo==null){
            wrongEmail=true;
        }
        validationData.put("wrongBookingRef", wrongBookingRef);
        validationData.put("wrongEmail", wrongEmail);
        return validationData;
    }
    public Flight getFlightInfoIfTicketExists(Ticket ticketInfo){
        if(ticketInfo==null){
            return null;
        }else{
            return this.fetchById(ticketInfo.flightId);
        }
    }

}
