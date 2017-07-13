package by.kostyl.coffee.validators;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import by.kostyl.coffee.entity.CoffeeType;
import by.kostyl.coffee.model.OrderDetails;

@Component
public class OrderDetailsMainValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return OrderDetails.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		OrderDetails orderDetails = (OrderDetails) target;
		List<CoffeeType> list = orderDetails.getCoffeeList();
		int i = 0;
		if (list != null) {
			for (CoffeeType ct : list) {
				if (ct.isSelected())
					i++;
				if (ct.isSelected() && ct.getQuantity() == 0) {
					errors.reject("quantity.zero");
					break;
				}
			}
			if (i == 0)
				errors.reject("selectsmth");
		}
		
		
	}

}
