package by.kostyl.coffee.dal;

import java.util.ArrayList;

import by.kostyl.coffee.entity.CoffeeType;

public interface CoffeeDao extends CrudDao {
	ArrayList<CoffeeType> getActiveCoffeeTypes();

	String getPropValue(String propID);

}
