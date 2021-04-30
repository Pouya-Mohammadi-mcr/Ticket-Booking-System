package Team5.onlinebookingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AppController<HttpPost> {

	@Autowired
	private FlightService service;

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


	// ck --- Here I get and set the ticket information (extra information)
	@RequestMapping(value = "/setTicketInformation", method = RequestMethod.POST)
	public String saveTicket(Model model,@ModelAttribute(name = "radio_class") String radio_class,@ModelAttribute(name = "radio_insurance") String radio_insurance,@ModelAttribute(name = "radio_meal") String radio_meal,@ModelAttribute(name = "luggage") String luggage,@ModelAttribute(name = "finalPrice") String finalPrice) {
		System.out.println(" The class is: "+radio_class + " Luggage: " + luggage  + " Meal: " +radio_meal+ " Insur: " + radio_insurance + "Final price: " + finalPrice);
//		Flight flight = service.fetchById(the_flightId);
//		model.addAttribute("flight", flight);
//		model.addAttribute("customer", new Customer());
		return "ResultPage";
	}


}
