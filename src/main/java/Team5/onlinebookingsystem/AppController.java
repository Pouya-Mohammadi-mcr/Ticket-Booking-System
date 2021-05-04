package Team5.onlinebookingsystem;

import antlr.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.lang.Math.round;

@Controller
public class AppController<HttpPost> {

	@Autowired
	private FlightService service;
	@Autowired
	private TicketService tService;
	@Autowired
	private CustomerService cService;
	@Autowired
	private BookingService bService;

	private SortingStrategyFactory sortFactory = SortingStrategyFactory.getInstance();
	private String flightOrigin;
	private long the_flightId;
	private long numberOfTickets;

	private List<Ticket> ticketsMade = new ArrayList<Ticket>();
//	public Ticket theTicket = new Ticket();

	@RequestMapping("/")
	public String showSearchPage(Model model){
		Flight flight = new Flight();
		flight.setAvailableSeats(1);
		model.addAttribute("flight", flight);
		return "SearchPage";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(@ModelAttribute(name = "Flight") Flight flight) {
		ModelAndView mav = new  ModelAndView("MatchedFlights");
		numberOfTickets = flight.getAvailableSeats();
		List<Flight> matchedFlights = service.find(flight.getFrom(), flight.getTo(), flight.getDate(), numberOfTickets);
		mav.addObject("matchedFlights", matchedFlights);
		Flight flightInfo = new Flight(flight.getFrom(), flight.getTo(), flight.getDate());
		mav.addObject("flightInfo", flightInfo);
		Flight newFlight = new Flight();
		newFlight.setAvailableSeats(flight.getAvailableSeats());
		mav.addObject("newFlight",newFlight);
		String sortingMethod = new String();
		mav.addObject(sortingMethod);
		return mav;
	}

	@RequestMapping(value = "/sort", method = RequestMethod.POST)
	public ModelAndView sort(@ModelAttribute(name = "Flight") Flight flight,@ModelAttribute(name = "sortingMethod") String sortingMethod) {
		ModelAndView mav = new ModelAndView("MatchedFlights");
		List<Flight> flightList = service.find(flight.getFrom(), flight.getTo(), flight.getDate(), numberOfTickets);
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
		numberOfTickets = flight.getAvailableSeats();
		List<Flight> matchedFlights = service.find(flight.getFrom(), flight.getTo(), flight.getDate(), numberOfTickets);
		mav.addObject("matchedFlights", matchedFlights);
		Flight flightInfo = new Flight(flight.getFrom(), flight.getTo(), flight.getDate());
		mav.addObject("flightInfo", flightInfo);
		Flight newFlight = new Flight();
		newFlight.setAvailableSeats(flight.getAvailableSeats());
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
		String button = new String("Add Ticket");
		model.addAttribute("button",button);
		List<Ticket> tickets = new ArrayList<Ticket>();
		model.addAttribute("tickets", tickets);
		ticketsMade.clear();
		return "BuildTicket";
	}


	// ck --- Here I get and set the ticket information (extra information)
	@RequestMapping(value = "/setTicketInformation", method = RequestMethod.POST)
	public String saveTicket(Model model,@ModelAttribute(name = "radio_class") String radio_class,@ModelAttribute(name = "insurance") String insurance,@ModelAttribute(name = "meal") String meal,@ModelAttribute(name = "luggage") String luggage,@ModelAttribute(name = "finalPrice") String finalPrice,@ModelAttribute(name = "radio_age") String radio_age) {
		System.out.println(" The class is: "+radio_class + " Luggage: " + luggage  + " Meal: " +meal+ " Insur: " + insurance + "Final price: " + finalPrice);

		String button = new String();
			if (ticketsMade.size()<numberOfTickets) {
				button = "Add Ticket";
				TicketBuilder ticketBuilder = new FlightTicketBuilder();
				ticketBuilder.addAgeGroup(radio_age);


/// building new random non existent booking refs - ck
				String bookingRef = tService.buildRandomTicketRef();
				while (tService.findByTicketRef(bookingRef).equals("yes")) {
					bookingRef = tService.buildRandomTicketRef();
				}
				ticketBuilder.addBookingRef(bookingRef);
				ticketBuilder.addFlightId(the_flightId);
				if (insurance.equals("yes")) {
					ticketBuilder.addInsurance(insurance);
				}
				if (!"".equals(luggage) && !"0".equals(luggage)) {
					ticketBuilder.addLuggage(luggage);
				}
				ticketBuilder.addSeatClass(radio_class);
				ticketBuilder.addMeal(meal);
				ticketBuilder.addPriceBought(finalPrice);
				Ticket ticket = ticketBuilder.getTicket();
				ticketsMade.add(ticket);
				System.out.print(ticket.toString());
			}
			else {
				List<Ticket> finalTickets = new ArrayList<Ticket>(ticketsMade);
				model.addAttribute("finalTickets",finalTickets);
				Customer customer = new Customer();
				model.addAttribute("customer", customer);
				double totalCost = 0;
				for (int i=0; i<ticketsMade.size();i++){
					totalCost += Double.parseDouble(ticketsMade.get(i).priceBought);
				}
				model.addAttribute("totalCost", Math.round(totalCost*100.0)/100.0);
				return "Checkout";
			}
			if (ticketsMade.size()==numberOfTickets){
				button ="Pay";
			}
		Flight flight = service.fetchById(the_flightId);
		model.addAttribute("flight", flight);
		model.addAttribute("tickets",ticketsMade);
		model.addAttribute("button",button);

		return "BuildTicket";
	}


	@RequestMapping(value = "/setCustomerInformation", method = RequestMethod.POST)
	public ModelAndView confirm(@ModelAttribute(name = "Customer") Customer customer) {
		ModelAndView mav = new ModelAndView("Confirmation" );
		cService.save(customer);

		for (int i=0; i<ticketsMade.size(); i++) {
			Booking book = new Booking();
			book.setCustomerEmail(customer.getCustomerEmail());
			tService.save(ticketsMade.get(i));
			book.setBookingRef(ticketsMade.get(i).bookingRef);
			bService.save(book);
		}
		service.decreaseCapacity(the_flightId, ticketsMade.size());

		return mav;
	}

	// search for booking  -- ck
	@RequestMapping("/getBooking")
	public String getBookingSearchPage(Model model){
		Flight flightInfo = new Flight();
		Ticket ticketInfo = new Ticket();
		ticketInfo.bookingRef="nosearch";
		model.addAttribute("ticketInfo",ticketInfo);
		model.addAttribute("flightInfo",flightInfo);
		return "BookingSearchPage";
	}
	@RequestMapping(value = "/returnBooking", method = RequestMethod.POST)
	public String showBookingSearchPage(Model model,@ModelAttribute(name = "bookingRef") String bookingRef,@ModelAttribute(name = "email") String email){
		model.addAttribute("bookingRef",bookingRef);
		model.addAttribute("email",email);
//		System.out.println("bookingRef: "+ bookingRef + "email: " + email);
		Flight flightInfo = new Flight();
		Ticket ticketInfo = tService.getTicketInformationByRef(bookingRef);
		//		Customer customerInfo = new Customer();
		if (ticketInfo != null){
			flightInfo = service.fetchById(ticketInfo.flightId);
			// If flight information for some reason can not be found
			if(flightInfo==null){
				// adding null here in order to catch it in the html page!!
				flightInfo = new Flight();
				ticketInfo.priceBought = "null";
			}
		}else{
			ticketInfo = new Ticket();
			ticketInfo.bookingRef = "null";
		}
		model.addAttribute("ticketInfo",ticketInfo);
		model.addAttribute("flightInfo",flightInfo);

		return "BookingSearchPage";
	}


}
