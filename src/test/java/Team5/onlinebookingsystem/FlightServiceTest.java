package Team5.onlinebookingsystem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FlightServiceTest {

    @Test
    @DisplayName("listAll Test")
    void ListAllTest()
    {
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
