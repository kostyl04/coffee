package by.kostyl.coffee.components;

import java.util.List;

import by.kostyl.coffee.entity.CoffeeType;

public class MainCalculator implements PriceCalculator {

	@Override
	public void calculatPositionPrice(List<CoffeeType> list, int cupCount) {
		for (CoffeeType ct : list) {
			int freeCups = ct.getQuantity() / cupCount;
			ct.setPriceSumm(ct.getPrice() * (ct.getQuantity() - freeCups));
		}

	}

	@Override
	public double calculateDelivery(double realSumm, double discountThreshold, double deliveryPrice) {
		if (realSumm >= discountThreshold)
			return 0;
		return deliveryPrice;
	}

	

}
