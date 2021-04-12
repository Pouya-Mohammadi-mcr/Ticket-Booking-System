package Team5.onlinebookingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

    public List<Flight> find(String from, String to, String date, String time){
        List<Flight> allFlights = listAll();
        List<Flight> matchedFlights = new ArrayList<Flight>();
        for (int i=0 ; i<allFlights.size(); i++){
            if ( allFlights.get(i).getFrom().equals(from)  && allFlights.get(i).getTo().equals(to)  && allFlights.get(i).getDate().equals(date) && allFlights.get(i).getTime().equals(time)) {
                matchedFlights.add(allFlights.get(i));
            }
        }
        if (matchedFlights.size() == 0) {
            System.out.print("NO RESULTS");
        }
        return matchedFlights;
    }
}
