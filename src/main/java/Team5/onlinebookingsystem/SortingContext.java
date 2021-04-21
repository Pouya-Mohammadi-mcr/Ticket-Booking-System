package Team5.onlinebookingsystem;

import java.util.List;

public class SortingContext {

    private SortingStrategy strategy;

    public SortingStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Flight> sortFlights (List<Flight> flightList){
        return strategy.sort(flightList);
    }
}
