//package Team5.onlinebookingsystem;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//public class FlightServiceTest {
//
//        private Map<String, Object> getSetupObjects(){
//        FlightRepository flightRepository = Mockito.mock(FlightRepository.class);
//        FlightServiceTestWrapper flightService = new FlightServiceTestWrapper();
//        flightService.SetRepository(flightRepository);
//
//        Map<String, Object> setupObjects = new HashMap<String, Object>()
//        {{
//            put("flightRepository", flightRepository);
//            put("flightService", flightService);
//        }};
//
//        return  setupObjects;
//    }
//
//    @Test
//    void listAllTest()
//    {
//        //Arrange
//        Map<String, Object> setupObjects = this.getSetupObjects();
//        FlightRepository flightRepository = (FlightRepository) setupObjects.get("flightRepository");
//        FlightServiceTestWrapper flightService = (FlightServiceTestWrapper) setupObjects.get("flightService");
//
//        List<Flight> flightList = new ArrayList<Flight>();
//        Flight flightObject1 = Mockito.mock(Flight.class);
//        Flight flightObject2 = Mockito.mock(Flight.class);
//        flightList.add(flightObject1);
//        flightList.add(flightObject2);
//
//        Mockito.when(flightRepository.findAll()).thenReturn(flightList);
//
//        //Act
//        List<Flight> allFlights = flightService.listAll();
//
//        //Assert
//        assertEquals(flightList, allFlights);
//
//    }
//
//    @Test
//    void saveTest() {
//        // Arrange
//        Map<String, Object> setupObjects = this.getSetupObjects();
//        FlightRepository flightRepository = (FlightRepository) setupObjects.get("flightRepository");
//        FlightServiceTestWrapper flightService = (FlightServiceTestWrapper) setupObjects.get("flightService");
//
//        Flight flightMock = Mockito.mock(Flight.class);
//
//        // Act
//        flightService.save(flightMock);
//
//        // Assert
//        verify(flightRepository, times(1)).save(any( Flight.class));
//
//    }
//
//    @Test
//    void getTest() {
//        //Arrange
//        Map<String, Object> setupObjects = this.getSetupObjects();
//        FlightRepository flightRepository = (FlightRepository) setupObjects.get("flightRepository");
//        FlightServiceTestWrapper flightService = (FlightServiceTestWrapper) setupObjects.get("flightService");
//
//        Flight flightMock = Mockito.mock(Flight.class);
//        Mockito.when(flightRepository.findById(1000L)).thenReturn(java.util.Optional.ofNullable(flightMock));
//
//        // Act
//        flightService.get(1000L);
//
//        // Assert
//        verify(flightRepository, times(1)).findById(any(Long.class));
//    }
//
//    @Test
//    void deleteTest() {
//        Map<String, Object> setupObjects = this.getSetupObjects();
//        FlightRepository flightRepository = (FlightRepository) setupObjects.get("flightRepository");
//        FlightServiceTestWrapper flightService = (FlightServiceTestWrapper) setupObjects.get("flightService");
//
//        Mockito.doNothing().when(flightRepository).deleteById(1000L);
//
//        // Act
//        flightService.delete(1000L);
//
//        // Assert
//        verify(flightRepository, times(1)).deleteById(any(Long.class));
//    }
//
//    @Test
//    // Find Case- "anywhere","alldates"
//    void findTest_Case1() {
//        // Arrange
//        List<Flight> flightList = new ArrayList<Flight>();
//
//        Flight flightObject1 = Mockito.mock(Flight.class);
//        Mockito.when(flightObject1.getDepartureTime()).thenReturn("00:00");
//        Mockito.when(flightObject1.getPrice()).thenReturn("200");
//        flightList.add(flightObject1);
//
//        Map<String, Object> setupObjects = this.getSetupObjects();
//        FlightRepository flightRepository = (FlightRepository) setupObjects.get("flightRepository");
//        Mockito.when(flightRepository.getAllConnectingFlightsAllDates(any(String.class))).thenReturn(flightList);
//
//        FlightServiceTestWrapper flightService = (FlightServiceTestWrapper) setupObjects.get("flightService");
//
//        flightService.SetRepository(flightRepository);
//
//        // Act
//        List<Flight> foundFlights = flightService.find("London","anywhere","alldates");
//
//        // Assert
//        assertEquals(flightList, foundFlights);
//        verify(flightRepository, times(1)).getAllConnectingFlightsAllDates(any(String.class));
//    }
//
//
//    @Test
//    // Find Case- "anywhere","someDate"
//    void findTest_Case2() {
//        // Arrange
//        List<Flight> flightList = new ArrayList<Flight>();
//
//        Flight flightObject1 = Mockito.mock(Flight.class);
//        Mockito.when(flightObject1.getDepartureTime()).thenReturn("00:00");
//        Mockito.when(flightObject1.getPrice()).thenReturn("200");
//        flightList.add(flightObject1);
//
//        Map<String, Object> setupObjects = this.getSetupObjects();
//        FlightRepository flightRepository = (FlightRepository) setupObjects.get("flightRepository");
//        Mockito.when(flightRepository.getAllConnectingFlights(any(String.class), any(String.class))).thenReturn(flightList);
//
//        FlightServiceTestWrapper flightService = (FlightServiceTestWrapper) setupObjects.get("flightService");
//
//        flightService.SetRepository(flightRepository);
//
//        // Act
//        List<Flight> foundFlights = flightService.find("London","anywhere","someDate");
//
//        // Assert
//        assertEquals(flightList, foundFlights);
//        verify(flightRepository, times(1)).getAllConnectingFlights(any(String.class), any(String.class));
//    }
//
//    @Test
//    // Find Case- "somewhere","alldates"
//    void findTest_Case3() {
//        // Arrange
//        List<Flight> flightList = new ArrayList<Flight>();
//
//        Flight flightObject1 = Mockito.mock(Flight.class);
//        Mockito.when(flightObject1.getDepartureTime()).thenReturn("00:00");
//        Mockito.when(flightObject1.getPrice()).thenReturn("200");
//        flightList.add(flightObject1);
//
//        Map<String, Object> setupObjects = this.getSetupObjects();
//        FlightRepository flightRepository = (FlightRepository) setupObjects.get("flightRepository");
//        Mockito.when(flightRepository.getFlightsAllDates(any(String.class), any(String.class))).thenReturn(flightList);
//
//        FlightServiceTestWrapper flightService = (FlightServiceTestWrapper) setupObjects.get("flightService");
//
//        flightService.SetRepository(flightRepository);
//
//        // Act
//        List<Flight> foundFlights = flightService.find("London","somewhere","alldates");
//
//        // Assert
//        assertEquals(flightList, foundFlights);
//        verify(flightRepository, times(1)).getFlightsAllDates(any(String.class), any(String.class));
//    }
//
//    @Test
//        // Find Case- "somewhere","someDate"
//    void findTest_Case4() {
//        // Arrange
//        List<Flight> flightList = new ArrayList<Flight>();
//
//        Flight flightObject1 = Mockito.mock(Flight.class);
//        Mockito.when(flightObject1.getDepartureTime()).thenReturn("00:00");
//        Mockito.when(flightObject1.getPrice()).thenReturn("200");
//        Mockito.when(flightObject1.getFrom()).thenReturn("London");
//        Mockito.when(flightObject1.getTo()).thenReturn("somewhere");
//        Mockito.when(flightObject1.getDate()).thenReturn("someDate");
//        flightList.add(flightObject1);
//
//        Map<String, Object> setupObjects = this.getSetupObjects();
//        FlightRepository flightRepository = (FlightRepository) setupObjects.get("flightRepository");
//        Mockito.when(flightRepository.findAll()).thenReturn(flightList);
//
//        FlightServiceTestWrapper flightService = (FlightServiceTestWrapper) setupObjects.get("flightService");
//
//        flightService.SetRepository(flightRepository);
//
//        // Act
//        List<Flight> foundFlights = flightService.find("London","somewhere","someDate");
//
//        // Assert
//        assertEquals(flightList, foundFlights);
//        verify(flightRepository, times(1)).findAll();
//    }
//
//    @Test
//        // Find Case- no matches
//    void findTest_Case5() {
//        // Arrange
//        List<Flight> flightList = new ArrayList<Flight>();
//
//        Flight flightObject1 = Mockito.mock(Flight.class);
//        Mockito.when(flightObject1.getDepartureTime()).thenReturn("00:00");
//        Mockito.when(flightObject1.getPrice()).thenReturn("200");
//        Mockito.when(flightObject1.getFrom()).thenReturn("London");
//        Mockito.when(flightObject1.getTo()).thenReturn("somewhere");
//        Mockito.when(flightObject1.getDate()).thenReturn("someDate");
//        flightList.add(flightObject1);
//
//        Map<String, Object> setupObjects = this.getSetupObjects();
//        FlightRepository flightRepository = (FlightRepository) setupObjects.get("flightRepository");
//        Mockito.when(flightRepository.findAll()).thenReturn(flightList);
//
//        FlightServiceTestWrapper flightService = (FlightServiceTestWrapper) setupObjects.get("flightService");
//
//        flightService.SetRepository(flightRepository);
//
//        // Act
//        List<Flight> foundFlights = flightService.find("Manchester","somewhere","someDate");
//
//        // Assert
//        assertEquals(0, foundFlights.size());
//        verify(flightRepository, times(1)).findAll();
//    }
//
//    @Test
//    void sortTest() {
//        // Arrange
//        List<Flight> inputList = new ArrayList<Flight>();
//
//        Flight flightObject1 = Mockito.mock(Flight.class);
//        Flight flightObject2 = Mockito.mock(Flight.class);
//        inputList.add(flightObject1);
//        inputList.add(flightObject2);
//
//        List<Flight> expectedList = new ArrayList<Flight>();
//        expectedList.add(flightObject1);
//        expectedList.add(flightObject2);
//
//        SortingStrategy sortingStrategy = Mockito.mock(SortByPriceAscending.class);
//        Mockito.doNothing().when(sortingStrategy).sort(any(List.class));
//
//        FlightService flightService = new FlightService();
//
//        // Act
//        List<Flight>sortedList = flightService.sort(sortingStrategy, inputList);
//
//        // Assert
//        assertEquals(expectedList, sortedList);
//        verify(sortingStrategy,times(1)).sort(inputList);
//    }
//
//    @Test
//    void fetchOriginAirportsTest() {
//        // Arrange
//        List<Flight> inputList = new ArrayList<Flight>();
//
//        String airport1 = "Athens";
//        String airport2 = "Mumbai";
//        List<String> expectedAirports = new ArrayList<String>();
//        expectedAirports.add(airport1);
//        expectedAirports.add(airport2);
//
//        Flight flightObject1 = Mockito.mock(Flight.class);
//        Mockito.when(flightObject1.getFrom()).thenReturn(airport1);
//        Flight flightObject2 = Mockito.mock(Flight.class);
//        Mockito.when(flightObject2.getFrom()).thenReturn(airport2);
//        inputList.add(flightObject1);
//        inputList.add(flightObject2);
//
//        Map<String, Object> setupObjects = this.getSetupObjects();
//        FlightRepository flightRepository = (FlightRepository) setupObjects.get("flightRepository");
//        Mockito.when(flightRepository.findByOrigin("keyword")).thenReturn(inputList);
//        FlightServiceTestWrapper flightService = (FlightServiceTestWrapper) setupObjects.get("flightService");
//
//        flightService.SetRepository(flightRepository);
//
//        // Act
//        List<String> destinationAirports = flightService.fetchOriginAirports("keyword");
//
//        // Assert
//        verify(flightRepository, times(1)).findByOrigin(any(String.class));
//        assertEquals(expectedAirports,destinationAirports);
//    }
//
//    @Test
//    void fetchDestinationAirportsTest() {
//        // Arrange
//        String airport1 = "Athens";
//        String airport2 = "Mumbai";
//        List<String> expectedAirports = new ArrayList<String>();
//        expectedAirports.add(airport1);
//        expectedAirports.add(airport2);
//
//        List<Flight> inputList = new ArrayList<Flight>();
//        Flight flightObject1 = Mockito.mock(Flight.class);
//        Mockito.when(flightObject1.getTo()).thenReturn(airport1);
//        Flight flightObject2 = Mockito.mock(Flight.class);
//        Mockito.when(flightObject2.getTo()).thenReturn(airport2);
//        inputList.add(flightObject1);
//        inputList.add(flightObject2);
//
//        Map<String, Object> setupObjects = this.getSetupObjects();
//        FlightRepository flightRepository = (FlightRepository) setupObjects.get("flightRepository");
//        Mockito.when(flightRepository.findByDestination("keyword","London")).thenReturn(inputList);
//        FlightServiceTestWrapper flightService = (FlightServiceTestWrapper) setupObjects.get("flightService");
//
//        flightService.SetRepository(flightRepository);
//
//        // Act
//        List<String> destinationAirports = flightService.fetchDestinationAirports("keyword","London");
//
//        // Assert
//        verify(flightRepository, times(1)).findByDestination(any(String.class), any(String.class));
//        assertEquals(expectedAirports,destinationAirports);
//    }
//
//    @Test
//    void fetchByIdTest() {
//        // Arrange
//        long flightId = 1000l;
//        long noMatchId = 1001L;
//        List<Flight> flightList = new ArrayList<Flight>();
//        Flight flightObject = Mockito.mock(Flight.class);
//        flightList.add(flightObject);
//
//        Map<String, Object> setup = this.getSetupObjects();
//        FlightRepository flightRepository = (FlightRepository) setup.get("flightRepository");
//        Mockito.when(flightRepository.getFlightById(flightId)).thenReturn(flightList);
//
//        FlightServiceTestWrapper flightService = (FlightServiceTestWrapper) setup.get("flightService");
//
//        flightService.SetRepository(flightRepository);
//
//        // Act
//         Flight matchedFlight = flightService.fetchById(flightId);
//
//         // no match case
//        Flight matchedFlight1 = flightService.fetchById(noMatchId);
//
//        // Assert
//         assertEquals(flightObject,matchedFlight);
//         verify(flightRepository, times(1)).getFlightById(flightId);
//         assertEquals(null ,matchedFlight1);
//         verify(flightRepository, times(1)).getFlightById(noMatchId);
//    }
//}
