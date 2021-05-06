package Team5.onlinebookingsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AppControllerTest {
    // ToDo Needs more test methods

    @Mock
    FlightService flightServiceMock;

    @InjectMocks
    AppController appController;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void showSearchPageTest_ValidCase(){
        //Arrange
        Model model = Mockito.mock(Model.class);
        AppController controller = new AppController();
        String expectedResult = "SearchPage";

        //Act
        String page = controller.showSearchPage(model);

        //Assert
        verify(model, times(1)).addAttribute(any(String.class), any(Flight.class) );
        assertEquals(expectedResult, page);
    }

    @Test
    void showSearchPageTest_NullModelCase(){
        //Arrange
        AppController controller = new AppController();

        //Act
        Exception exception = assertThrows(NullPointerException.class, () -> {
            controller.showSearchPage(null);
        });
        String actualMessage = exception.getMessage();
        String expectedMessage = "\"model\" is null";

        //Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Not a test")
    // Just the mocking mechanism
    void search_basic() {
        //Arrange
        Flight inputFlightMock = Mockito.mock(Flight.class);
        when(inputFlightMock.getFrom()).thenReturn("Manchester");
        when(inputFlightMock.getTo()).thenReturn("London");
        when(inputFlightMock.getDate()).thenReturn("01/01/2022");
        when(inputFlightMock.getAvailableSeats()).thenReturn(5l);

        FlightService flightServiceMock = Mockito.mock(FlightService.class);

        Flight matchedFlightMock = Mockito.mock(Flight.class);
        when(matchedFlightMock.getFrom()).thenReturn("success");

        List<Flight> dummyList = new ArrayList<Flight>();
        dummyList.add(matchedFlightMock);

        when(flightServiceMock.find(inputFlightMock.getFrom(), inputFlightMock.getTo(),
                inputFlightMock.getDate(), inputFlightMock.getAvailableSeats())).thenReturn(dummyList);


        //Act
        AppController appController = new AppController();
        List<Flight> matchedFlights = flightServiceMock.find(inputFlightMock.getFrom(), inputFlightMock.getTo(),
                inputFlightMock.getDate(), inputFlightMock.getAvailableSeats());


        //Assert
        assertEquals("success" ,matchedFlights.get(0).getFrom());
    }

//    @Test
//    void searchTest_Incomplete() {
//        //Arrange
//        Flight inputFlightMock = Mockito.mock(Flight.class);
//        when(inputFlightMock.getFrom()).thenReturn("Manchester");
//        when(inputFlightMock.getTo()).thenReturn("London");
//        when(inputFlightMock.getDate()).thenReturn("01/01/2022");
//
//        FlightService flightServiceMock = Mockito.mock(FlightService.class);
//
//        Flight matchedFlightMock = Mockito.mock(Flight.class);
//        when(matchedFlightMock.getFrom()).thenReturn("success");
//
//        List<Flight> dummyList = new ArrayList<Flight>();
//        dummyList.add(matchedFlightMock);
//
//        when(flightServiceMock.find(inputFlightMock.getFrom(), inputFlightMock.getTo(),
//                inputFlightMock.getDate())).thenReturn(dummyList);
//
//
//        //Act
//        AppController appController = new AppController();
//        ModelAndView mav = appController.search(inputFlightMock);
//
//        //Assert
//    }

    @Test
    @DisplayName("Not a proper test")
    void SearchTest_1() {
        // Not a proper test
        // ToDo: Needs rework

        //Arrange
        Flight inputFlightMock = Mockito.mock(Flight.class);
        when(inputFlightMock.getFrom()).thenReturn("Manchester");
        when(inputFlightMock.getTo()).thenReturn("London");
        when(inputFlightMock.getDate()).thenReturn("01/01/2022");

        Flight matchedFlightMock = Mockito.mock(Flight.class);
        when(matchedFlightMock.getFrom()).thenReturn("success");

        List<Flight> dummyFlightList = new ArrayList<Flight>();
        dummyFlightList.add(matchedFlightMock);

        FlightService flightServiceMock = Mockito.mock(FlightService.class);
        when(flightServiceMock.find(inputFlightMock.getFrom(), inputFlightMock.getTo(),
                inputFlightMock.getDate(),inputFlightMock.getAvailableSeats())).thenReturn(dummyFlightList);


        //Act
        ModelAndView mav = new ModelAndView("MatchedFlights");
        mav.addObject("matchedFlights", dummyFlightList);
        List<Flight> matchedFlights = (List<Flight>) mav.getModelMap().get("matchedFlights");

        //Assert
        assertEquals(matchedFlights.get(0).getFrom(), "success");
    }

    @Test
    public void updateFlightTableTest(){
        // Arrange
        Flight flight = mock(Flight.class);
        when(flight.getFrom()).thenReturn("Manchester");
        when(flight.getTo()).thenReturn("London");
        when(flight.getDate()).thenReturn("01/01/2022");
        when(flight.getAvailableSeats()).thenReturn(122L);

        Flight matchedFlightMock = mock(Flight.class);
        when(matchedFlightMock.getFrom()).thenReturn("success");
        List<Flight> flightList = new ArrayList<Flight>();
        flightList.add(matchedFlightMock);

        when(flightServiceMock.find(flight.getFrom(), flight.getTo(),
                flight.getDate(),flight.getAvailableSeats())).thenReturn(flightList);

        // Act
        ModelAndView mav = appController.updateFlightTable(flight);

        // Assert
        verify(flightServiceMock, times(1)).find(flight.getFrom(), flight.getTo(),
                flight.getDate(),flight.getAvailableSeats());
        assertEquals("Manchester", ((Flight)(mav.getModelMap().get("flightInfo"))).getFrom());
    }

    @Test
    public void selectedFlightIdTest(){
        // Arrange
        String id = "1237";
        Flight flight = mock(Flight.class);
        String expectedResult = "BuildTicket";

        Model model = mock(Model.class);
        when(flightServiceMock.fetchById(Long.parseLong(id))).thenReturn(flight);

        // Act
        String result = appController.selectedFlightId(id, model);

        //Assert
        verify(model, times(1)).addAttribute(any(String.class), any(List.class));
        verify(model, times(1)).addAttribute("flight", flight);
        verify(model, times(1)).addAttribute(any(String.class), any(String.class));
        assertEquals(expectedResult, result);
    }
}
