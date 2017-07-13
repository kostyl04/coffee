package by.kostyl.coffee.components;

import java.util.List;

import by.kostyl.coffee.entity.CoffeeType;

public interface PriceCalculator {
	void calculatPositionPrice(List<CoffeeType> list,int cupCount);
	
	double calculateDelivery(double realSumm,double discountThreshold,double deliveryPrice);
	
}
