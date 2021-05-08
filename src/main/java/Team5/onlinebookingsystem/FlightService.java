package Team5.onlinebookingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            if(date.equals("alldates")){
                List<Flight> allFlights = repo.getAllConnectingFlightsAllDates(from,tickets);
                matchedFlights.addAll(allFlights);
            }else{
                List<Flight> allFlights = repo.getAllConnectingFlights(from,date,tickets);
                matchedFlights.addAll(allFlights);
            }
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
        if (matchedFlights.size() == 0) {
            // Todo: ------------------ Remove? ---------------------------
            System.out.print("NO RESULTS");
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
        // Todo:------------------- Rename List ------------------------
        List<Flight> listOfAirports = repo.findByOrigin(keyword);
        List<String> suggestions = new ArrayList<>();
        for (Flight listOfAirport : listOfAirports) {
            suggestions.add(listOfAirport.getFrom());
        }
        Set<String> uniqueAirports = new HashSet<>(suggestions);
        return new ArrayList<>(uniqueAirports);
    }

    //    Service for fetching airport-city names -- ck
    public List<String> fetchDestinationAirports(String keyword,String origin){
        // Todo: ------------------- Rename List ------------------------
        List<Flight> listOfAirports = repo.findByDestination(keyword,origin);
        List<String> suggestions = new ArrayList<>();

        for (Flight listOfAirport : listOfAirports) {
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
//        repo.save(flight);
    }

    // ToDo:----------------------------- Review ----------------------------------
    public  List<Boolean> validation(boolean isValidationRequired, Ticket ticketInfo, Customer customerInfo,
                                     boolean wrongBookingRef, boolean wrongEmail){
        List<Boolean> val = new ArrayList<>();
        if(isValidationRequired){
            if(ticketInfo==null){
                wrongBookingRef=true;
            }
            if(customerInfo==null){
                wrongEmail=true;
            }
        }else{
            if(ticketInfo==null){
                wrongBookingRef=true;
            }
            if(customerInfo==null){
                wrongEmail=true;
            }
        }
        val.add(wrongBookingRef);
        val.add(wrongEmail);
        return val;
    }
    public Flight getFlightInfoIfTicketExists(Ticket ticketInfo){
        if(ticketInfo==null){
            return null;
        }else{
            return this.fetchById(ticketInfo.flightId);
        }
    }

}
