package by.kostyl.coffee.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import by.kostyl.coffee.entity.CoffeeType;
import by.kostyl.coffee.model.OrderDetails;
import by.kostyl.coffee.service.CoffeeService;
import by.kostyl.coffee.validators.OrderDetailsMainValidator;
import by.kostyl.coffee.validators.OrderDetailsOrderValidator;

@Controller
@SessionAttributes("orderDetails")
public class MainController {
	@Autowired
	private OrderDetailsMainValidator mainValidator;
	@Autowired
	private OrderDetailsOrderValidator orderValidator;
	@Autowired
	private CoffeeService service;
	
	

	@RequestMapping("/")
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView("main");
		OrderDetails orderDetails = new OrderDetails();
		
		ArrayList<CoffeeType> list = service.getActiveCoffeeTypes();

		orderDetails.setCoffeeList(list);
		mav.addObject("orderDetails", orderDetails);
		return mav;
	}

	@RequestMapping("/order")
	public ModelAndView order(@ModelAttribute("orderDetails")  OrderDetails orderDetails,
			BindingResult bindingResult, Model model) {
		mainValidator.validate(orderDetails, bindingResult);
		if (bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView("main");
			mav.addObject("orderDetails", orderDetails);
			return mav;
		}
		service.calculateOrderDetails(orderDetails);
		ModelAndView mav = new ModelAndView("orderlist");
		mav.addObject("orderDetails", orderDetails);
		return mav;
	}
	@RequestMapping("/order/confirm")
	public ModelAndView confirmOrder(@ModelAttribute("orderDetails")  OrderDetails orderDetails,
			BindingResult bindingResult, Model model,WebRequest request,SessionStatus status) {
		orderValidator.validate(orderDetails, bindingResult);
		if (bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView("orderlist");
			mav.addObject("orderDetails", orderDetails);
			return mav;
		}
		service.mergeOrder(orderDetails);
		status.setComplete();
		request.removeAttribute("orderDetails", WebRequest.SCOPE_SESSION);
		
		return new ModelAndView("confirm");
	}

}
