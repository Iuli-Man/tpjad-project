package edu.ubb.tpjad.app;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import edu.ubb.tpjad.entity.User;
import edu.ubb.tpjad.interfaces.UserService;

@Stateless
@Path("/users")
public class UserRestService {
	
	@EJB
	private UserService userService;
	
	@PersistenceContext(unitName = "tpjad-project")
	EntityManager manager;
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(User user){
		boolean loggedIn = userService.login(user);
		if(loggedIn){
			return Response.ok().build();
		}else{
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}
	
	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response doGet(){
		Query q = manager.createQuery("SELECT u FROM User u");
		List<User> users = q.getResultList();
		return Response.status(Status.OK).entity(users).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response doPost(User user){
		try{
			userService.add(user);
			return Response.status(Status.CREATED).build();
		}catch(Exception e){
			return Response.status(Status.CONFLICT).entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response doGet(@PathParam("username") String username){
		User user = manager.find(User.class, username);
		if(user != null){
			return Response.ok(user).build();
		}else{
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response doPut(User user){
		userService.update(user);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{username}")
	public Response doDelete(@PathParam("username") String username){
		userService.remove(username);
		return Response.ok().build();
	}
}
