package Team5.onlinebookingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppController {

	@Autowired
	private FlightService service;


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
		System.out.print(matchedFlights);
		mav.addObject("matchedFlights", matchedFlights);
		return mav;
	}

	@RequestMapping(value = "/sortByPriceAscending", method = RequestMethod.POST)
	public ModelAndView sortByPriceAscending(@RequestParam("mf") List<Flight> flightList) {
		ModelAndView mav = new ModelAndView("MatchedFlights");
		System.out.print(flightList);
		List<Flight> matchedFlights = service.sort(new SortByPriceAscending(), flightList);
		System.out.print(matchedFlights);
		mav.addObject("matchedFlights", matchedFlights);
		return mav;
	}

}
