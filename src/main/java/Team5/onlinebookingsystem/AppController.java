package Team5.onlinebookingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
	public Flight temp_flight = new Flight();

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
		return mav;
	}

	// ck function get origin Airport name from search form
	@RequestMapping(value = "/setOrigin", method = RequestMethod.POST)
	public ModelAndView setOrigin(@RequestBody String origin) {
		String[] origin_parts = origin.split("=");
		temp_flight.setFrom(origin_parts[1]);
//		System.out.println(origin_parts[1]);
		return null;
	}

	// ck function fetching airports name based on origin input
	@GetMapping("/townOriginAirportNames")
	@ResponseBody
	public List<String> townOriginAirportNames(@RequestParam(value="term" , required=false,defaultValue = "") String term){
		List<String> suggestions = service.fetchOriginAirports(term);
		//System.out.println(term);
		return suggestions;
	}

	// ck function fetching airports name based on destination input
	@GetMapping("/townDestinationAirportNames")
	@ResponseBody
	public List<String> townDestinationAirportNames(@RequestParam(value="term" , required=false,defaultValue = "") String term){
//		System.out.println(temp_flight.getFrom());
		List<String> suggestions = service.fetchDestinationAirports(term,temp_flight.getFrom());
		return suggestions;
	}

}
