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
    private FlightRepository repo;


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

    public List<Flight> find(String from, String to, String date){
        List<Flight> matchedFlights = new ArrayList<Flight>();
        if(to.equals("anywhere") ){
            if(date.equals("alldates")){
                List<Flight> allFlights = repo.getAllConnectingFlightsAllDates(from);
                for (int i=0 ; i<allFlights.size(); i++){
                    matchedFlights.add(allFlights.get(i));
                }
            }else{
                List<Flight> allFlights = repo.getAllConnectingFlights(from,date);
                for (int i=0 ; i<allFlights.size(); i++){
                    matchedFlights.add(allFlights.get(i));
                }
            }
        }else if(date.equals("alldates")){
            List<Flight> allFlights = repo.getFlightsAllDates(from,to);
            for (int i=0 ; i<allFlights.size(); i++){
                matchedFlights.add(allFlights.get(i));
            }
        }else{
            List<Flight> allFlights = listAll();
            for (int i=0 ; i<allFlights.size(); i++){
                if ( allFlights.get(i).getFrom().equals(from)  && allFlights.get(i).getTo().equals(to)  && allFlights.get(i).getDate().equals(date) ) {
                    matchedFlights.add(allFlights.get(i));
                }
            }
        }
        if (matchedFlights.size() == 0) {
            System.out.print("NO RESULTS");
        }
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
        List<Flight> listOfAirports = repo.findByOrigin(keyword);
        List<String> suggestions = new ArrayList<String>();
        for (int i=0 ; i<listOfAirports.size(); i++){
            suggestions.add(listOfAirports.get(i).getFrom());
		}
        Set<String> uniqueAirports = new HashSet<String>(suggestions);
        List<String> uniqueSuggestions = new ArrayList<String>();
        uniqueSuggestions.addAll(uniqueAirports);
        return uniqueSuggestions;
    }

    //    Service for fetching airport-city names -- ck
    public List<String> fetchDestinationAirports(String keyword,String origin){
        List<Flight> listOfAirports = repo.findByDestination(keyword,origin);
        List<String> suggestions = new ArrayList<String>();

        for (int i=0 ; i<listOfAirports.size(); i++){
            suggestions.add(listOfAirports.get(i).getTo());
        }
        Set<String> uniqueAirports = new HashSet<String>(suggestions);
        List<String> uniqueSuggestions = new ArrayList<String>();
        uniqueSuggestions.addAll(uniqueAirports);
        return uniqueSuggestions;
    }
}