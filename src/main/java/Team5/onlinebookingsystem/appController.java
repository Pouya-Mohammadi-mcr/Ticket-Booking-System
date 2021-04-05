package Team5.onlinebookingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	@RequestMapping(value = "/search")
	public String search(@RequestParam(value="id", required=false, defaultValue = "1") Long id, Model model) {
		Flight flight= service.get(id);
		model.addAttribute("flight", flight);
		return "MatchedFlights";
	}

}
