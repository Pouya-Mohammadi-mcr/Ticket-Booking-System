package Team5.onlinebookingsystem;

import antlr.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class AppController<HttpPost> {

	@Autowired
	private FlightService service;
	@Autowired
	private TicketService tService;

	private SortingStrategyFactory sortFactory = SortingStrategyFactory.getInstance();
	private String flightOrigin;
	private long the_flightId;
//	public Ticket theTicket = new Ticket();

	@RequestMapping("/")
	public String showSearchPage(Model model){
		Flight flight = new Flight();
		model.addAttribute("flight", flight);
		return "SearchPage";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(@ModelAttribute(name = "Flight") Flight flight) {
		ModelAndView mav = new ModelAndView("MatchedFlights");
		List<Flight> matchedFlights = service.find(flight.getFrom(), flight.getTo(), flight.getDate());
		mav.addObject("matchedFlights", matchedFlights);
		Flight flightInfo = new Flight(flight.getFrom(), flight.getTo(), flight.getDate());
		mav.addObject("flightInfo", flightInfo);
		Flight newFlight = new Flight();
		mav.addObject("newFlight",newFlight);
		String sortingMethod = new String();
		mav.addObject(sortingMethod);
		return mav;
	}

	@RequestMapping(value = "/sort", method = RequestMethod.POST)
	public ModelAndView sort(@ModelAttribute(name = "Flight") Flight flight,@ModelAttribute(name = "sortingMethod") String sortingMethod) {
		ModelAndView mav = new ModelAndView("MatchedFlights");
		List<Flight> flightList = service.find(flight.getFrom(), flight.getTo(), flight.getDate());
		List<Flight> matchedFlights = new ArrayList<Flight>();

		SortingStrategy strategy = sortFactory.getStrategy(sortingMethod);
		matchedFlights = service.sort(strategy, flightList);

		mav.addObject("matchedFlights", matchedFlights);
		Flight flightInfo = new Flight(flight.getFrom(), flight.getTo(), flight.getDate());
		mav.addObject("flightInfo", flightInfo);
		Flight newFlight = new Flight();
		mav.addObject("newFlight", newFlight);
		mav.addObject(sortingMethod);
		return mav;
	}
	// ck function get origin Airport name from search form
	@RequestMapping(value = "/setOrigin", method = RequestMethod.POST)
	public ModelAndView setOrigin(@RequestBody String origin) {
		String[] origin_parts = origin.split("=");
		flightOrigin = origin_parts[1].replace("+"," ");
		return null;
	}

	// ck function fetching airports name based on origin input
	@GetMapping("/townOriginAirportNames")
	@ResponseBody
	public List<String> townOriginAirportNames(@RequestParam(value="term" , required=false,defaultValue = "") String term){
		List<String> suggestions = service.fetchOriginAirports(term);
		return suggestions;
	}

	// ck function fetching airports name based on destination input
	@GetMapping("/townDestinationAirportNames")
	@ResponseBody
	public List<String> townDestinationAirportNames(@RequestParam(value="term" , required=false,defaultValue = "") String term){
		List<String> suggestions = service.fetchDestinationAirports(term, flightOrigin);
		return suggestions;
	}

	// ck observer pattern -- get all new input values
	@RequestMapping(value = "/updateFlightTable", method = RequestMethod.POST)
	public ModelAndView updateFlightTable(@ModelAttribute(name = "Flight") Flight flight) {
		ModelAndView mav = new ModelAndView("MatchedFlights");
		List<Flight> matchedFlights = service.find(flight.getFrom(), flight.getTo(), flight.getDate());
		mav.addObject("matchedFlights", matchedFlights);
		Flight flightInfo = new Flight(flight.getFrom(), flight.getTo(), flight.getDate());
		mav.addObject("flightInfo", flightInfo);
		Flight newFlight = new Flight();
		mav.addObject("newFlight",newFlight);
		String sortingMethod = new String();
		mav.addObject(sortingMethod);
		return mav;
	}

	// ck function get flight id for ticket constructor
	@RequestMapping("/selectedFlightId/{id}")
	public String selectedFlightId(@PathVariable("id") String id, Model model) {
		the_flightId = Long.parseLong(id);
		Flight flight = service.fetchById(the_flightId);
		model.addAttribute("flight", flight);
		return "BuildTicket";
	}

	public String buildRandomTicketRef(){
		Random rand = new Random();
		// Obtain a number between [0 - 9].
		int d1 = rand.nextInt(10);
		int d2 = rand.nextInt(10);
		int d3 = rand.nextInt(10);
		int d4 = rand.nextInt(10);
		int d5 = rand.nextInt(10);
		int d6 = rand.nextInt(10);
		String randBookingReference ="";
		randBookingReference += Integer.toString(d1) + Integer.toString(d2) + Integer.toString(d3) + Integer.toString(d4) + Integer.toString(d5) + Integer.toString(d6);

		return randBookingReference;
	}

	// ck --- Here I get and set the ticket information (extra information)
	@RequestMapping(value = "/setTicketInformation", method = RequestMethod.POST)
	public String saveTicket(Model model,@ModelAttribute(name = "radio_class") String radio_class,@ModelAttribute(name = "insurance") String insurance,@ModelAttribute(name = "meal") String meal,@ModelAttribute(name = "luggage") String luggage,@ModelAttribute(name = "finalPrice") String finalPrice) {
		System.out.println(" The class is: "+radio_class + " Luggage: " + luggage  + " Meal: " +meal+ " Insur: " + insurance + "Final price: " + finalPrice);
//		Flight flight = service.fetchById(the_flightId);
//		model.addAttribute("flight", flight);
//		model.addAttribute("customer", new Customer());
		TicketBuilder ticketBuilder = new FlightTicketBuilder();
		ticketBuilder.addAgeGroup("Adult");

		/// building new random non existent booking refs - ck
		String bookingRef= buildRandomTicketRef();
		System.out.println(bookingRef);
		while(tService.findByTicketRef(bookingRef).equals("yes")){
			bookingRef= buildRandomTicketRef();
		}
		System.out.println(tService.findByTicketRef(bookingRef));
		ticketBuilder.addBookingRef(bookingRef);

		ticketBuilder.addFlightId(the_flightId);
		if (insurance.equals("yes")) {
			ticketBuilder.addInsurance(insurance);
		}
		if (!"".equals(luggage) && !"0".equals(luggage)){
			ticketBuilder.addLuggage(luggage);
		}

		ticketBuilder.addSeatClass(radio_class);
		ticketBuilder.addMeal(meal);
		ticketBuilder.addPriceBought(finalPrice);
		Ticket ticket = ticketBuilder.getTicket();
		System.out.print(ticket.toString());
		tService.save(ticket);
		System.out.print(tService.listAll());
		return "ResultPage";
	}


}
