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
	@RequestMapping(value = "/search/{id}")
	public ModelAndView search(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("MatchedFlights");
		Flight flight= service.get(id);
		mav.addObject("flight", flight);
		return mav;
	}

}
