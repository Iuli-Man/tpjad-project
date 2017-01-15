package edu.ubb.tpjad.beans;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.ubb.tpjad.entity.User;
import edu.ubb.tpjad.interfaces.UserService;

@Stateless
@Remote(UserService.class)
public class UserServiceBean implements UserService{
	
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

	@Override
	public void add(User user) {
		entityManager.persist(user);
	}

	@Override
	public User get(String username) {
		return entityManager.find(User.class, username);
	}

	@Override
	public void update(User user) {
		entityManager.merge(user);
	}
	
	@Override
	public void remove(String username){
		User user =  entityManager.find(User.class, username);
		if(user != null){
			entityManager.remove(user);
		}
	}
	
}
