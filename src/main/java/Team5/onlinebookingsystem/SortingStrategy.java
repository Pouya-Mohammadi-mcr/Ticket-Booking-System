package Team5.onlinebookingsystem;

import java.util.List;

public interface SortingStrategy {

    public List<Flight> sort(List<Flight> flightList);
}
