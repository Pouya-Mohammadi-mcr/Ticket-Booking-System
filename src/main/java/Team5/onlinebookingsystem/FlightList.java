package Team5.onlinebookingsystem;

import java.util.List;

public class FlightList {

    private List<Flight> flightList;

    public FlightList() {
    }
    public void addFlight(Flight flight) {
        this.flightList.add(flight);
    }

    public FlightList(List<Flight> flightList) {
        this.flightList = flightList;
    }

    public List<Flight> getFlightList() {
        return flightList;
    }

    public void setFlightList(List<Flight> flightList) {
        this.flightList = flightList;
    }


}
