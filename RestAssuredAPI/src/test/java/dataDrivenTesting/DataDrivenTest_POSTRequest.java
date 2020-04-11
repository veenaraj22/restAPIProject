package dataDrivenTesting;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
//import junit.framework.Assert;

public class DataDrivenTest_POSTRequest {
	@Test(dataProvider="empdataprovider")
	public void AddDetailsOffNewEmployee(String ename,String esal,String eage)
	{
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		RequestSpecification httpRequest=RestAssured.given();
		JSONObject requestparams=new JSONObject();
		requestparams.put("name",ename);
		requestparams.put("salary", esal);
		requestparams.put("Age", eage);
		httpRequest.header("content-Type","application/json");
		///we entered data in the form of hashmap so we need to convert hashmap to json
		httpRequest.body(requestparams.toJSONString());
		Response response=httpRequest.request(Method.POST,"/create");
		String responseBody=response.getBody().toString();
		System.out.println("responsebody is:"+responseBody);
		if(responseBody.contains(ename)==true)
		{
			System.out.println(true);
		}
		else
		{
			System.out.println(false);
		}
		
		//sert.assertEquals(responseBody.concat(ename),true);
		//Assert.assertEquals(responseBody.contains(ename),true);
		int statusCode=response.getStatusCode();
		System.out.println("StatusCode is :"+statusCode);
		
}
	@DataProvider(name="empdataprovider")
	String [][] getEmpData() throws IOException{
		//excel path
		String path="D:\\Liveproject\\RestAssuredAPI\\src/test/java/dataDrivenTesting/empData.xlsx";
		int rownum=XLUtility.getRowCount(path, "Sheet1");
		int colcount=XLUtility.getCellCount(path, "Sheet1", 1);
		String empdata[][]=new String[rownum][colcount];
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				empdata[i-1][j]=XLUtility.getCellData(path, "Sheet1", i, j);
				
			}
		}
		return empdata;
		
	}

}
