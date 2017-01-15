package edu.ubb.tpjad.app;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import edu.ubb.tpjad.entity.Product;
import edu.ubb.tpjad.interfaces.ProductService;

@Stateless
@Path("/products")
public class ProductRestService {

	@EJB
	ProductService productService;
	
	@PersistenceContext(unitName = "tpjad-project")
	EntityManager entityManager;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response filterProducts(@QueryParam("name") String name, @QueryParam("type") String type,
			@QueryParam("description") String description) {
		Product p = new Product();
		p.setName(name == null ? "" : name);
		p.setType(type == null ? "" : type);
		p.setDescription(description == null ? "" : description);
		List<Product> poducts = entityManager
				.createQuery("SELECT p FROM Product p WHERE p.name LIKE :name AND p.type LIKE :type AND p.description LIKE :description", Product.class)
				.setParameter("name", "%" + p.getName() + "%")
				.setParameter("type", "%" + p.getType() + "%")
				.setParameter("description", "%" + p.getDescription() + "%")
				.getResultList();
		return Response.status(200).entity(poducts).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response doGet(@PathParam("id") int id){
		Product product = entityManager.find(Product.class, id);
		if(product != null){
			return Response.status(Status.OK).entity(product).build();
		}else{
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response doPut(Product p){
		return Response.status(Status.OK).entity(entityManager.merge(p)).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response doPost(Product p){
		return Response.status(200).entity(productService.add(p)).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response doDelete(@PathParam("id") int id){
		Product p = entityManager.find(Product.class, id);
		if(p != null){
			entityManager.remove(p);
			return Response.status(Status.OK).build();
		}else{
			return Response.status(Status.NOT_FOUND).build();
		}
	}

}
