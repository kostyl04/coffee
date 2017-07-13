package by.kostyl.coffee.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.kostyl.coffee.components.CalculatorRunner;
import by.kostyl.coffee.components.PriceCalculator;
import by.kostyl.coffee.dal.CoffeeDao;
import by.kostyl.coffee.entity.CoffeeOrder;
import by.kostyl.coffee.entity.CoffeeOrderItem;
import by.kostyl.coffee.entity.CoffeeType;
import by.kostyl.coffee.model.OrderDetails;

@Service
public class CoffeeService {
	@Autowired
	private CalculatorRunner runner;

	@Autowired
	private CoffeeDao coffeeDao;

	@Transactional
	public void calculateOrderDetails(OrderDetails details) {
		details.setCoffeeList(
				details.getCoffeeList().stream().filter(c -> c.isSelected()).collect(Collectors.toList()));
		try {
			PriceCalculator calc = runner.getCalculator();
			int cupCount = getDiscountCups();
			double discountThreshold = getDiscountThreshold();
			double deliveryPrice = getDeliveryPrice();
			calc.calculatPositionPrice(details.getCoffeeList(), cupCount);
			double summ = details.getCoffeeList().stream().mapToDouble(c -> c.getPriceSumm()).sum();
			details.setSummWithoutDel(summ);
			details.setDeliveryPrice(calc.calculateDelivery(summ, discountThreshold, deliveryPrice));
			summ += details.getDeliveryPrice();
			details.setSummWithDelivery(summ);

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException
				| URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<CoffeeType> getActiveCoffeeTypes() {
		return coffeeDao.getActiveCoffeeTypes();
	}

	private double getDiscountThreshold() {
		return Double.valueOf(coffeeDao.getPropValue("x"));
	}

	private int getDiscountCups() {
		return Integer.valueOf(coffeeDao.getPropValue("n"));
	}

	private double getDeliveryPrice() {
		return Double.valueOf(coffeeDao.getPropValue("m"));
	}

	@Transactional
	public void mergeOrder(OrderDetails orderDetails) {
		Set<CoffeeOrderItem> items = new HashSet<CoffeeOrderItem>();
		CoffeeOrder order = new CoffeeOrder(new Date(), orderDetails.getFullName(), orderDetails.getAddress(),
				orderDetails.getSummWithDelivery());
		orderDetails.getCoffeeList().forEach(c -> {
			items.add(new CoffeeOrderItem(c.getQuantity(), order, c));
		});
		order.setItems(items);
		coffeeDao.merge(order);

	}

}
