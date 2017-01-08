package edu.ubb.tpjad.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.ubb.tpjad.entity.User;

@Stateless
public class UserServiceBean {
	
	@PersistenceContext( unitName = "tpjad-project")
	private EntityManager entityManager;
	
	public boolean login(User user){
		User actualUser = entityManager.find(User.class, user.getUsername());
		if(actualUser == null){
			return false;
		}
		if(user.getPassword().equals(actualUser.getPassword())){
			return true;
		}
		return false;
	}
	
}
