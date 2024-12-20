package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.endpoints.UserEndpoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest2 {
Faker faker;
User userPayload;
Logger logger;
	@BeforeClass
	public void setupData() {
	
		faker = new Faker();
		userPayload = new User();
		logger = LogManager.getLogger(this.getClass());
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		userPayload.setPassword(faker.internet().password(5,10));
		
		
	}
	
	@Test(priority = 1)
	public void testPostUser() {
		logger.info("******* Starting Testing Post User ***************");
 Response response = UserEndpoints2.createUser(userPayload);
 response.then().log().all();
 
 logger.info("Validate status code");
 Assert.assertEquals(response.statusCode(), 200);
	logger.info("*********Finish Testing Post User***********");
	}
	
	@Test(priority = 2)
	private void testGetUserByUsername() {
		logger.info("******* Starting Testing Get User By User name**************8");
	Response response =	UserEndpoints2.readUser(this.userPayload.getUsername());
	response.then().log().all();
	logger.info("Validate response when send request GET User By Username");
	Assert.assertEquals(response.statusCode(), 200);
	logger.info("*********Finish Testing GET User By Username***************");
	}
	
	@Test(priority = 3)
	private void testUpdateUserByUsername() {
		
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response responseAfterUpdate =UserEndpoints2.updateUser(userPayload, this.userPayload.getUsername());
		responseAfterUpdate.then().log().all();
		
		Assert.assertEquals(responseAfterUpdate.statusCode(), 200);
	}
	
	@Test(priority = 4)
	private void testDeleteUserByUsername() {

		Response response = UserEndpoints2.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
	}
}
