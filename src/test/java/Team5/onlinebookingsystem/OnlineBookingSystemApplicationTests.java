package Team5.onlinebookingsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class OnlineBookingSystemApplicationTests {

	@Test
	void contextLoads() {
	}


		private SortByPriceAscending sortByPriceAscending;
		private Flight flight1;
		private Flight flight2;
		private List<Flight> flightList;
		private List<Flight> sortedFlightList;

	@BeforeEach
		public void setUp() throws Exception {
			sortByPriceAscending = new SortByPriceAscending();
			flight1 = new Flight("200");
			flight2 = new Flight("300");

			flightList= new ArrayList<Flight>();
			flightList.add(flight2);
			flightList.add(flight1);

			sortedFlightList= new ArrayList<Flight>();
			sortedFlightList.add(flight1);
			sortedFlightList.add(flight2);

	}

		@Test
		@DisplayName(" should work")
		public void testSort() {
			assertEquals(sortedFlightList, sortByPriceAscending.sort(flightList),
					"Regular multiplication should work");
		}

	}



