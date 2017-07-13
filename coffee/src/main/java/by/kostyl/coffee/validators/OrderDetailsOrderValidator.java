package by.kostyl.coffee.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import by.kostyl.coffee.model.OrderDetails;

@Component
public class OrderDetailsOrderValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return OrderDetails.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		OrderDetails orderDetails = (OrderDetails) target;
		if(orderDetails.getFullName().trim().length()<3){
			errors.reject("delivery.wrongname");
		}
		if(orderDetails.getAddress().trim().length()<3){
			errors.reject("delivery.wrongaddress");
		}
	}

}
