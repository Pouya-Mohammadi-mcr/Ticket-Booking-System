package Team5.onlinebookingsystem;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortByPriceAscending implements SortingStrategy{

    @Override
    public List<Flight> sort(List<Flight> flightList) {
        //float f=Float.parseFloat();
        Comparator<Flight> compareByPrice = (Flight f1, Flight f2) -> Float.compare(Float.parseFloat(f1.getPrice()),Float.parseFloat(f2.getPrice()));
        Collections.sort(flightList, compareByPrice);
        return flightList;
    }
}
