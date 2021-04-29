package Team5.onlinebookingsystem;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortByDepartureTimeAscending implements SortingStrategy {

    @Override
    public List<Flight> sort(List<Flight> flightList) {
        //float f=Float.parseFloat();
        Comparator<Flight> compareByDepartureTime = (Flight f1, Flight f2) -> Float.compare(Float.parseFloat(f1.getDepartureTime()), Float.parseFloat(f2.getDepartureTime()));
        Collections.sort(flightList, compareByDepartureTime);
        return flightList;
    }
}
