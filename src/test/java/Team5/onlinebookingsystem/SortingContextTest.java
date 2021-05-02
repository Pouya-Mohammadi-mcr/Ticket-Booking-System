package Team5.onlinebookingsystem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SortingContextTest {

    public static SortingStrategy[] data(){
        SortingStrategy strategy = Mockito.mock(SortingStrategy.class);
        return new SortingStrategy[]{strategy, null};
    }

    @ParameterizedTest
    @MethodSource(value =  "data")
    void SortingStrategyTest_temp(SortingStrategy data){
        //Arrange
        SortingContext context = new SortingContext();

        //Act
        context.setStrategy(data);
        SortingStrategy actualStrategy = context.getStrategy();

        //Assert
        assertEquals(data, actualStrategy);
    }

    @Test
    void SortingStrategyTest(){
        //Arrange
        SortingStrategy strategy = Mockito.mock(SortingStrategy.class);
        SortingContext context = new SortingContext();

        //Act
        context.setStrategy(strategy);
        SortingStrategy actualStrategy = context.getStrategy();

        //Assert
        assertEquals(strategy, actualStrategy);
    }

    @Test
    void SortingStrategyTest_NullCase(){
        //Arrange
        SortingContext context = new SortingContext();

        //Act
        context.setStrategy(null);
        SortingStrategy actualStrategy = context.getStrategy();

        //Assert
        assertEquals(null, actualStrategy);
    }

    @Test
    void SortFlightsTest(){
        //Arrange
        List<Flight> dummyList = new ArrayList<Flight>();
        Flight flightObject1 = Mockito.mock(Flight.class);
        dummyList.add(flightObject1);

        List<Flight> sortedList = new ArrayList<Flight>();
        Flight flightObject2 = Mockito.mock(Flight.class);
        sortedList.add(flightObject2);

        SortingStrategy strategy = Mockito.mock(SortingStrategy.class);
        SortingContext context = new SortingContext();
        context.setStrategy(strategy);
        //ToDo ------------------check-----------------------
//        when(strategy.sort(dummyList)).thenReturn(sortedList);
//
//        //Act
//        List<Flight> sortedFlights = context.sortFlights(dummyList);

        //Assert
//        verify(strategy, times(1)).sort(any( List.class));
//        assertEquals(sortedList, sortedFlights);
    }

    //ToDo
    // Can strategy possibly be null?
    // If yes, then throw exception in the prod method and handle it in the test code

}
