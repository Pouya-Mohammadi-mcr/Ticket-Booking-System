package Team5.onlinebookingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class appController {

	@Autowired
	private FlightService service;
/*
	@RequestMapping("/")
	public String homePage(Model model) {
		List<Flight> FlightsList = service.listAll();
		model.addAttribute("FlightsList", FlightsList);
		return "index";
	}
*/

	@RequestMapping("/SearchPage")
	public String showSearchPage(Model model){
		Flight flight = new Flight();
		model.addAttribute("flight", flight);
		return "SearchPage";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(@ModelAttribute(name = "Flight") Flight flight) {
		ModelAndView mav = new ModelAndView("MatchedFlights");
		Long id = flight.getId();
		Flight matchedFlight= service.get(id);
		mav.addObject("flight", matchedFlight);
		return mav;
	}

}
