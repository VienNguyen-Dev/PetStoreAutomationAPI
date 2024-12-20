package api.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class DDTest {
	
	@Test(priority = 1 , dataProvider = "Data", dataProviderClass = api.utilities.DataProvider.class)
	public void testPostUser(String userID, String userName, String fName, String lName, String email, String pass, String phone) {

		User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fName);
		userPayload.setLastName(lName);
		userPayload.setEmail(email);
		userPayload.setPassword(pass);
		userPayload.setPhone(phone);
		
		
		Response response = UserEndpoints.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = api.utilities.DataProvider.class)
	public void testDeleteByUsername(String userName) {

		Response response = UserEndpoints.deleteUser(userName);
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
	}
}
