package edu.ubb.tpjad.test;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.jayway.restassured.http.ContentType;

import edu.ubb.tpjad.test.dto.UserDTO;

public class UserRestTest extends RestBaseTest {
	
	private static final String TESTROLE = "testrole";
	private static final String TESTPASSWORD = "testpassword";
	private static final String TESTUSER = "testuser";

	private UserDTO user = new UserDTO()
			.setUsername(TESTUSER)
			.setPassword(TESTPASSWORD)
			.setType(TESTROLE);
	
	private UserDTO user2 = new UserDTO()
			.setUsername(TESTUSER + "2")
			.setPassword(TESTPASSWORD)
			.setType(TESTROLE);
	
	@Test
	public void testBasicPing(){
		given().when().get("/users").then().statusCode(200);
	}
	
	@Test
	public void testCRUD(){
		createUser();
		createUserAlreadyExisting();
		loginUserValid();
		getUser();
		deleteUser();
	}
	
	@Test
	public void testLoginUserInvalid(){
		given()
			.contentType(ContentType.JSON)
			.body(user2)
		.when()
			.post("users/login")
		.then()
			.statusCode(401);
	}
	
	public void createUser(){
		given()
			.contentType(ContentType.JSON)
			.body(user)
		.when()
			.post("/users")
		.then()
			.statusCode(201);
	}
	
	public void createUserAlreadyExisting(){
		given()
			.contentType(ContentType.JSON)
			.body(user)
		.when()
			.post("/users")
		.then()
			.statusCode(500);
	}
	
	private void loginUserValid(){
		given()
			.contentType(ContentType.JSON)
			.body(user)
		.when()
			.post("users/login")
		.then()
			.statusCode(200);
	}
	
	private void getUser(){
		UserDTO result =
		given()
			.contentType(ContentType.JSON)
		.when()
			.get("users/"+TESTUSER)
		.then()
			.statusCode(200)
			.extract().as(UserDTO.class);
		assertEquals(user, result);
	}
	
	private void deleteUser(){
		given()
			.contentType(ContentType.JSON)
		.when()
			.delete("/users/testuser")
		.then()
			.statusCode(200);
	}

}
