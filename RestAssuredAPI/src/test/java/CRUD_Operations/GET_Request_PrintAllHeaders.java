package CRUD_Operations;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class GET_Request_PrintAllHeaders {
	@Test
	public void allHeaders()
	{
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
		//specify the Request Object
		RequestSpecification httpRequest=RestAssured.given();
		//specify the Response Object
		Response httpResponse=httpRequest.request(Method.GET,"/Delhi");
		//Respose code in the form of JSON FOrmat ,so we need tpo convert JSON to String
		String responseBody=httpResponse.getBody().asString();
		System.out.println("httpRespose"+ responseBody);
		boolean h1=responseBody.contains("Delhi");
		Assert.assertEquals(h1,true);
		//Assert.assertEquals(responseBody.contains("Delhi"), true);
		//capture all headers
		Headers allHeaders=httpResponse.headers();
		for(Header eachHeader:allHeaders)
		{
			System.out.println(eachHeader.getName()+"   "+eachHeader.getValue());
		}
	}

}
