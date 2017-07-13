package by.kostyl.coffee.dal;

import java.util.ArrayList;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import by.kostyl.coffee.entity.CoffeeType;

@Repository
@Transactional
public class CoffeeDaoBean extends CrudDaoBean implements CoffeeDao {
	@Override
	public ArrayList<CoffeeType> getActiveCoffeeTypes() {
		org.hibernate.query.Query<CoffeeType> query = currentSession()
				.createQuery("from CoffeeType c where c.disabled is null", CoffeeType.class);
		ArrayList<CoffeeType> result = (ArrayList<CoffeeType>) query.getResultList();
		
		return result;
	}

	@Override
	public String getPropValue(String propID) {
		Query query = currentSession().createNativeQuery("select value from configuration where id=:id")
				.setParameter("id", propID);
		return (String) query.getResultList().get(0);
	}

}
