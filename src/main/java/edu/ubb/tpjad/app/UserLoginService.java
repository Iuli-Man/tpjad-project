package edu.ubb.tpjad.app;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.ubb.tpjad.beans.UserServiceBean;
import edu.ubb.tpjad.entity.User;

@Stateless
@Path("/login")
public class UserLoginService {
	
	@EJB
	private UserServiceBean userService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@QueryParam("username") String username, @QueryParam("password") String password){
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		boolean result = userService.login(user);
		return Response.status(200).entity(result).build();
	}
}
