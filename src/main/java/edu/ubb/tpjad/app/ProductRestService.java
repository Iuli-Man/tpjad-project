package edu.ubb.tpjad.app;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.ubb.tpjad.entity.Product;
import edu.ubb.tpjad.interfaces.ProductService;

@Stateless
@Path("/product")
public class ProductRestService {

	@EJB
	ProductService productService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response filterProducts(@QueryParam("name") String name, @QueryParam("type") String type,
			@QueryParam("description") String description) {
		Product p = new Product();
		p.setName(name == null ? "" : name);
		p.setType(type == null ? "" : type);
		p.setDescription(description == null ? "" : description);
		return Response.status(200).entity(productService.listProducts(p)).build();
	}

}
