package org.zzo.dataloding;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class ReadJsonFile {
	
	public static void main(String[] args) {

		JsonParser jsonParser = new JsonParser();
		try 
		
			(FileReader reader = new FileReader("InfoNote/unit.json")) {
			
			Object obj = jsonParser.parse(reader);
			JsonArray employeeList = (JsonArray) obj;
			System.out.println(employeeList);
			
			for (JsonElement jsonElement : employeeList) {
				System.out.println(jsonElement);
			}
			
			/*https://www.mkyong.com/java/how-do-convert-java-object-to-from-json-format-gson-api/
			 * 
			 * */
			 
		    //employeeList.forEach( emp -> parseEmployeeObject( (JsonObject) emp ) );

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	private static void parseEmployeeObject(JsonObject employee) 
	{
		//Get employee object within list
		JsonObject employeeObject = (JsonObject) employee.get("employee");
		
		//Get employee first name
		JsonElement firstName = employeeObject.get("firstName");	
		System.out.println(firstName);
		
		//Get employee last name
		JsonElement lastName = employeeObject.get("lastName");	
		System.out.println(lastName);
		
		//Get employee website name
		JsonElement website =  employeeObject.get("website");	
		System.out.println(website);
	}

}
