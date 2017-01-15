package edu.ubb.tpjad.interfaces;

import edu.ubb.tpjad.entity.User;

public interface UserService {
	
	public boolean login(User user);
	
	public void add(User user);
	
	public User get(String username);
	
	public void update(User user);
	
	public void remove(String username);

}
