package org.zzo.dataloding;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.zzo.AppEntity.product.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonDataLoad {
	
	
	public static List<ProductUoM> uomLoading ( String fileNamae) throws FileNotFoundException, IOException{
		
		System.out.println("## Loading " +  fileNamae + " !");
		
		JsonArray elementList = (JsonArray) new JsonParser().parse(new FileReader(fileNamae));
		List<ProductUoM> uomList = new ArrayList<ProductUoM>();
		
		for (JsonElement jsonElement : elementList) {
			uomList.add(new Gson().fromJson(jsonElement.toString(), ProductUoM.class));
		}
		
		return ( !uomList.isEmpty() ) ? uomList : null ;

	}
	
	public static List<ProductCategory> pcLoading ( String fileNamae) throws FileNotFoundException, IOException{
		
		System.out.println("## Loading " +  fileNamae + " !");
		
		JsonArray elementList = (JsonArray) new JsonParser().parse(new FileReader(fileNamae));
		List<ProductCategory> pcList = new ArrayList<ProductCategory>();
		
		for (JsonElement jsonElement : elementList) {
			pcList.add(new Gson().fromJson(jsonElement.toString(), ProductCategory.class));
		}
		
		return ( !pcList.isEmpty() ) ? pcList : null ;

	}	
	
	
	public static List<ProductDetails> pdLoading ( String fileNamae) throws FileNotFoundException, IOException{
		
		System.out.println("## Loading " +  fileNamae + " !");
		
		JsonArray elementList = (JsonArray) new JsonParser().parse(new FileReader(fileNamae));
		List<ProductDetails> pdList = new ArrayList<ProductDetails>();
		
		for (JsonElement jsonElement : elementList) {
			pdList.add(new Gson().fromJson(jsonElement.toString(), ProductDetails.class));
		}
		
		return ( !pdList.isEmpty() ) ? pdList : null ;

	}

	
	public static List<ProductPrice> ppLoading ( String fileNamae) throws FileNotFoundException, IOException{
		
		System.out.println("## Loading " +  fileNamae + " !");
		
		JsonArray elementList = (JsonArray) new JsonParser().parse(new FileReader(fileNamae));
		List<ProductPrice> ppList = new ArrayList<ProductPrice>();
		
		for (JsonElement jsonElement : elementList) {
			ppList.add(new Gson().fromJson(jsonElement.toString(), ProductPrice.class));
		}
		
		return ( !ppList.isEmpty() ) ? ppList : null ;

	}
	
	
	
	
}
