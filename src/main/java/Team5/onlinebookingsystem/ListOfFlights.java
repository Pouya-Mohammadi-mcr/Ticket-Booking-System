package Team5.onlinebookingsystem;

import java.util.List;

public class ListOfFlights {

    private List<Flight> flightList;

    public ListOfFlights() {
    }
    public void addFlight(Flight flight) {
        this.flightList.add(flight);
    }

    public ListOfFlights(List<Flight> flightList) {
        this.flightList = flightList;
    }

    public List<Flight> getFlightList() {
        return flightList;
    }

    public void setFlightList(List<Flight> flightList) {
        this.flightList = flightList;
    }


}
