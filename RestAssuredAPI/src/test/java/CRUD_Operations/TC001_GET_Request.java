package CRUD_Operations;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC001_GET_Request {
	//specify base uri
	@Test
	public void GetWeatherDetails()
	{
RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
//specify the Request Object
RequestSpecification httpRequest=RestAssured.given();
//specify the Response Object
Response httpResponse=httpRequest.request(Method.GET,"/Hyderabad");
//Respose code in the form of JSON FOrmat ,so we need tpo convert JSON to String
String responseBody=httpResponse.getBody().asString();
System.out.println("httpRespose"+ responseBody);
//Status code validation
int statuscode=httpResponse.getStatusCode();
System.out.println("displaying statuscode:"+statuscode);
Assert.assertEquals(statuscode, 200);


//validating StatusLine
String statusLine=httpResponse.getStatusLine();
System.out.println("displaying status line:"+statusLine);
Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");



}
}
