package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {
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
 Response response = UserEndpoints.createUser(userPayload);
 response.then().log().all();
 
 logger.info("Validate status code");
 Assert.assertEquals(response.statusCode(), 200);
	logger.info("*********Finish Testing Post User***********");
	}
	
	@Test(priority = 2)
	private void testGetUserByUsername() {
		logger.info("******* Starting Testing Get User By User name**************8");
	Response response =	UserEndpoints.readUser(this.userPayload.getUsername());
	response.then().log().all();
	logger.info("Validate response when send request GET User By Username");
	Assert.assertEquals(response.statusCode(), 200);
	logger.info("*********Finish Testing GET User By Username***************");
	}
	
	@Test(priority = 3)
	private void testUpdateUserByUsername() {
		logger.info("*********Starting Testing Update User By Username***************");
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		logger.info("Updating user by username:  "+ this.userPayload.getUsername());
		Response responseAfterUpdate =UserEndpoints.updateUser(userPayload, this.userPayload.getUsername());
		responseAfterUpdate.then().log().all();
		logger.info("Validate update user by username ");
		Assert.assertEquals(responseAfterUpdate.statusCode(), 200);
		logger.info("*********Finish Update user By Username**************");
	}
	
	@Test(priority = 4)
	private void testDeleteUserByUsername() {
		logger.info("**********Starting Testing Delete user By Username****************");
		logger.info("Deleting user by: "+ this.userPayload.getUsername());
		Response response = UserEndpoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		logger.info("Validate response with status code is 200");
		Assert.assertEquals(response.statusCode(), 200);
		
		logger.info("Finish delete user by username");
	}
}
