package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UsersTests {
	Faker faker;
	User userPalyload;
	
	@BeforeClass
	public void setUpDate()
	{
		faker=new Faker();
		userPalyload=new User();
		
		userPalyload.setId(faker.idNumber().hashCode());
		userPalyload.setUsername(faker.name().username());
		userPalyload.setLastName(faker.name().firstName());
		userPalyload.setLastName(faker.name().lastName());
		userPalyload.setEmail(faker.internet().safeEmailAddress());
		userPalyload.setPassword(faker.internet().password(5,10));
		userPalyload.setPhone(faker.phoneNumber().cellPhone());
			
	}
	@Test(priority=1)
	public void testPostuser()
	{
		Response response=UserEndPoints2.createUser(userPalyload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        
	}
	
	@Test(priority=2)
	public void testGetUserByName()
	{
		Response response=UserEndPoints2.readUser(this.userPalyload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	@Test(priority=3)
	public void testUpdatUserByName()
	{
		userPalyload.setUsername(faker.name().username());
		userPalyload.setLastName(faker.name().firstName());
		userPalyload.setLastName(faker.name().lastName());
		
	Response response=UserEndPoints2.updateUser(this.userPalyload.getUsername(),userPalyload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
		//Checking data after update
		Response responseAfterupdate=UserEndPoints2.readUser(this.userPalyload.getUsername());
		response.then().log().all();
		Assert.assertEquals(responseAfterupdate.getStatusCode(),200);
		
	}
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		Response response=UserEndPoints2.deleteUser(this.userPalyload.getUsername());
		Assert.assertEquals(response.getStatusCode(),204);
		
	}
	
}
