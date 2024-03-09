package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//User EndPoints Java
//Created to perform Create,Read, Update, Perform, Delete operations
public class UserEndPoints2 
{
	//method created for getting URL'S from properties files
	static ResourceBundle getURL()
	{
	  ResourceBundle routes=ResourceBundle.getBundle("routes"); //Load properties file and name of the proeproty file
	  
	  return routes;
	}

   public static Response createUser(User payload)
   {
	  String post_url=getURL().getString("post_url");
	   
	   Response response=given()
		   .contentType(ContentType.JSON)
		   .accept(ContentType.JSON)
		   .body(payload)
	  .when()
	     //.post(Routes.base_url);
	    .post(post_url);
	   
	   return response;
    
   }
   
   
   
   public static Response readUser(String username)
   {
	   
	   String get_url=getURL().getString("get_url");
	   
	   Response response=given()
		   .pathParam("username", username)
	  .when()
	     //.get(Routes.get_url);
	       .get(get_url);
	   
	   return response;
    
   }
   
   
   public static Response updateUser(String username,User payload)
   {
	   
	   String update_url=getURL().getString("update_url");
	   Response response=given()
		   .contentType(ContentType.JSON)
		   .accept(ContentType.JSON)
		   .pathParam("username",username)
		   .body(payload)
	  .when()
	     //.put(Routes.update_url);
	     .put(update_url);
	   return response;
    
   }
   
   
   public static Response deleteUser(String username)
   {
	   
	   String delete_url=getURL().getString("delete_url");
	   Response response=given()
		   .pathParam("username", username)
	  .when()
	     //.delete(Routes.delete_url);
	    .delete(delete_url);
	   
	   return response;
    
   }
   
   
 
	
}
