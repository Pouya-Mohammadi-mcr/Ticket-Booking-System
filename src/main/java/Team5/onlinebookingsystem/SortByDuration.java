package Team5.onlinebookingsystem;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortByDuration implements SortingStrategy {



    @Override
    public List<Flight> sort(List<Flight> flightList) {
        //float f=Float.parseFloat();
        Comparator<Flight> compareByDuration = new Comparator<Flight>() {
            @Override
            public int compare(Flight f1, Flight f2) {
                Integer f1DT = 0;
                Integer f1AT = 0;
                Integer f2DT = 0;
                Integer f2AT = 0;
                Integer f1Duration = 0;
                Integer f2Duration = 0;


                //calculate values for flight f1
                if (f1.getDepartureTime().length()==4){
                    f1DT= (Integer.parseInt(f1.getDepartureTime().substring(0,2))*60)+(Integer.parseInt(f1.getDepartureTime().substring(2,4)));
                }
                else if (f1.getDepartureTime().length()==3){
                    f1DT= (Integer.parseInt(f1.getDepartureTime().substring(0,1))*60)+(Integer.parseInt(f1.getDepartureTime().substring(1,3)));
                }
                else if (f1.getDepartureTime().length()<3 ){
                    f1DT= (Integer.parseInt(f1.getDepartureTime())*60);
                }

                if (f1.getArrivalTime().length()==4){
                    f1AT= (Integer.parseInt(f1.getArrivalTime().substring(0,2))*60)+(Integer.parseInt(f1.getArrivalTime().substring(2,4)));
                }
                else if (f1.getArrivalTime().length()==3){
                    f1AT= (Integer.parseInt(f1.getArrivalTime().substring(0,1))*60)+(Integer.parseInt(f1.getArrivalTime().substring(1,3)));
                }
                else if (f1.getArrivalTime().length()<3 ){
                    f1AT= (Integer.parseInt(f1.getArrivalTime())*60);
                }

                //calculate values for flight f2
                if (f2.getDepartureTime().length()==4){
                    f2DT= (Integer.parseInt(f2.getDepartureTime().substring(0,2))*60)+(Integer.parseInt(f2.getDepartureTime().substring(2,4)));
                }
                else if (f2.getDepartureTime().length()==3){
                    f2DT= (Integer.parseInt(f2.getDepartureTime().substring(0,1))*60)+(Integer.parseInt(f2.getDepartureTime().substring(1,3)));
                }
                else if (f2.getDepartureTime().length()<3 ){
                    f2DT= (Integer.parseInt(f2.getDepartureTime())*60);
                }
                if (f2.getArrivalTime().length()==4){
                    f2AT= (Integer.parseInt(f2.getArrivalTime().substring(0,2))*60)+(Integer.parseInt(f2.getArrivalTime().substring(2,4)));
                }
                else if (f2.getArrivalTime().length()==3){
                    f2AT= (Integer.parseInt(f2.getArrivalTime().substring(0,1))*60)+(Integer.parseInt(f2.getArrivalTime().substring(1,3)));
                }
                else if (f2.getArrivalTime().length()<3 ){
                    f2AT= (Integer.parseInt(f2.getArrivalTime())*60);
                }

                //When arrival is in the next day
                if (f1AT<f1DT){
                    f1Duration = (1440-f1DT)+f1AT;
                }
                else{
                    f1Duration = f1AT-f1DT;
                }

                //When arrival is in the next day
                if (f2AT<f2DT){
                    f2Duration = (1440-f2DT)+f2AT;
                }
                else{
                    f2Duration = f2AT-f2DT;
                }
                return f1Duration-f2Duration;
            }
        };
        Collections.sort(flightList, compareByDuration);
        return flightList;
    }
}
