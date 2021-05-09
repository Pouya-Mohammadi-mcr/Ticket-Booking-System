package Team5.onlinebookingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AppController{

	@Autowired
	private FlightService flightService;
	@Autowired
	private TicketService ticketService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private BookingService bookingService;
	@Autowired
	private MailingService mailingService;

	private SortingStrategyFactory sortFactory = SortingStrategyFactory.getInstance();
	private String flightOrigin;
	private long the_flightId;
	private long numberOfTickets;

	private List<Ticket> ticketsMade = new ArrayList<>();

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
		List<Flight> matchedFlights = flightService.find(flight.getFrom(), flight.getTo(), flight.getDate(), numberOfTickets);
		mav.addObject("matchedFlights", matchedFlights);
		mav.addObject("flightInfo", flight);
		return mav;
	}

	@RequestMapping(value = "/sort", method = RequestMethod.POST)
	public ModelAndView sort(@ModelAttribute(name = "Flight") Flight flight,@ModelAttribute(name = "sortingMethod") String sortingMethod) {
		ModelAndView mav = new ModelAndView("MatchedFlights");
		numberOfTickets = flight.getAvailableSeats();
		List<Flight> flightList = flightService.find(flight.getFrom(), flight.getTo(), flight.getDate(), numberOfTickets);
		SortingStrategy strategy = sortFactory.getStrategy(sortingMethod);
		List<Flight> matchedFlights = flightService.sort(strategy, flightList);
		mav.addObject("matchedFlights", matchedFlights);
		mav.addObject("flightInfo", flight);
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
		return flightService.fetchOriginAirports(term);
	}

	// ck function fetching airports name based on destination input
	@GetMapping("/townDestinationAirportNames")
	@ResponseBody
	public List<String> townDestinationAirportNames(@RequestParam(value="term" , required=false,defaultValue = "") String term){
		return flightService.fetchDestinationAirports(term, flightOrigin);
	}

	// ck function get flight id for ticket constructor
	@RequestMapping("/selectedFlightId/{id}")
	public String selectedFlightId(@PathVariable("id") String id, Model model) {
		the_flightId = Long.parseLong(id);
		Flight flight = flightService.fetchById(the_flightId);
		model.addAttribute("flight", flight);
		String button = "Add Ticket";
		model.addAttribute("button",button);
		List<Ticket> tickets = new ArrayList<>();
		model.addAttribute("tickets", tickets);
		ticketsMade.clear();
		return "BuildTicket";
	}


	// ck --- Here I get and set the ticket information (extra information)
	@RequestMapping(value = "/setTicketInformation", method = RequestMethod.POST)
	public String saveTicket(Model model,@ModelAttribute(name = "radio_class") String radio_class,@ModelAttribute(name = "insurance") String insurance,@ModelAttribute(name = "meal") String meal,@ModelAttribute(name = "luggage") String luggage,@ModelAttribute(name = "finalPrice") String finalPrice,@ModelAttribute(name = "radio_age") String radio_age) {
		String button;
			if (ticketsMade.size()<numberOfTickets) {
				button = "Add Ticket";
				TicketBuilder ticketBuilder = new FlightTicketBuilder();
				TicketDirector ticketDirector = new TicketDirector();
				Ticket ticket = ticketDirector.makeTicket(ticketBuilder, ticketService,radio_class,meal,luggage,finalPrice,insurance,radio_age,the_flightId);
				ticketsMade.add(ticket);
			}
			else {
				List<Ticket> finalTickets = new ArrayList<>(ticketsMade);
				model.addAttribute("finalTickets",finalTickets);
				Customer customer = new Customer();
				model.addAttribute("customer", customer);
				double totalCost = 0;
				for (Ticket ticket : ticketsMade) {
					totalCost += Double.parseDouble(ticket.priceBought);
				}
				model.addAttribute("totalCost", Math.round(totalCost*100.0)/100.0);
				return "Checkout";
			}
			if (ticketsMade.size()==numberOfTickets){
				button ="Pay";
			}
		Flight flight = flightService.fetchById(the_flightId);
		model.addAttribute("flight", flight);
		model.addAttribute("tickets",ticketsMade);
		model.addAttribute("button",button);
		return "BuildTicket";
	}


	@RequestMapping(value = "/setCustomerInformation", method = RequestMethod.POST)
	public ModelAndView confirm(@ModelAttribute(name = "Customer") Customer customer) {
		ModelAndView mav = new ModelAndView("Confirmation" );
		customerService.save(customer);

		List<Booking> currentBookings = new ArrayList<>();
		for (Ticket ticket : ticketsMade) {
			Booking book = new Booking();
			book.setCustomerEmail(customer.getCustomerEmail());
			ticketService.save(ticket);
			book.setBookingRef(ticket.bookingRef);
			bookingService.save(book);
			currentBookings.add(book);
		}
		flightService.decreaseCapacity(the_flightId, ticketsMade.size());
		mailingService.sendConfirmationEmail(customer.getCustomerEmail(), currentBookings, customer.getFullName(),
				flightService, ticketsMade);
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
		boolean wrongEmail =false;
		boolean wrongBookingRef =false;
		boolean validation = bookingService.validate(email,bookingRef);
		Ticket ticketInfo = ticketService.getTicketInformationByRef(bookingRef);
		Customer customerInfo = customerService.findByEmail(email);
		List<Boolean> val = flightService.validation(ticketInfo,customerInfo,wrongBookingRef,wrongEmail);
		Flight flightInfo = flightService.getFlightInfoIfTicketExists(ticketInfo);
		model.addAttribute("ticketInfo",ticketInfo);
		model.addAttribute("flightInfo",flightInfo);
		model.addAttribute("wrongBookingRef",val.get(0));
		model.addAttribute("wrongEmail",val.get(1));
		model.addAttribute("validation",validation);
		return "BookingSearchPage";
	}
}
