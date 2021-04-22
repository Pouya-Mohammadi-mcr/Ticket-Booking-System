package Team5.onlinebookingsystem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class FlightServiceTest {
    // ToDo: Needs more test methods

    @Test
    @DisplayName("listAll Test")
    void ListAllTest()
    {
        // ToDo: Not complete, needs rework
        //Arrange
        FlightRepository flightRepository = Mockito.mock(FlightRepository.class);

        List<Flight> dummyList = new ArrayList<Flight>();
        Flight flightObject = Mockito.mock(Flight.class);
        dummyList.add(flightObject);

        Mockito.when(flightRepository.findAll()).thenReturn(dummyList);

        FlightService fs = new FlightService();

        //Act
//      List<Flight> flights = fs.listAll();


        //Assert

    }

    @Test
    void getTest() {
    }

    @Test
    void deleteTest() {
    }

    @Test
    void findTest() {
    }
}
