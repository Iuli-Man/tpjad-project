package edu.ubb.tpjad.test;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.jayway.restassured.http.ContentType;

import edu.ubb.tpjad.test.dto.ProductDTO;

public class ProductRestTest extends RestBaseTest {

	private ProductDTO product = new ProductDTO()
			.setName("TEST name")
			.setType("TEST type")
			.setDescription("TEST description");
	
	@Test
	public void testBasicPing(){
		given().when().get("/products").then().statusCode(200);
	}
	
	@Test
	public void testCRUD(){
		int id = createProduct();
		readProduct(id);
		deleteProduct(id);
		readProductInexistent(id);
	}
	
	private int createProduct(){
		ProductDTO result = 
		given()
		.contentType(ContentType.JSON)
		.body(product)
		.when()
		.post("/products")
		.then()
		.statusCode(200)
		.extract().as(ProductDTO.class);
		assertNotNull(result.getId());
		assertEquals(product, result);
		return result.getId();
	}
	
	private void readProduct(int id){
		ProductDTO result = 
		given()
		.contentType(ContentType.JSON)
		.when()
		.get("/products/"+id)
		.then()
		.statusCode(200)
		.extract().as(ProductDTO.class);
		assertEquals(product, result);
	}
	
	private void readProductInexistent(int id){
		given()
		.contentType(ContentType.JSON)
		.when()
		.get("/products/"+id)
		.then()
		.statusCode(404);
	}
	
	private void deleteProduct(int id){
		given()
		.contentType(ContentType.JSON)
		.when()
		.delete("/products/"+id)
		.then()
		.statusCode(200);
	}

}
