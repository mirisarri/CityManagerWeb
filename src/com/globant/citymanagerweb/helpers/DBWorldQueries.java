package com.globant.citymanagerweb.helpers;

public class DBWorldQueries {

	public static String getUSACitiesOrderedByDistrictAndPopulation() {
		return "select * from city where CountryCode = 'USA' order by District ASC, Population DESC";
	}
	
	public static String getCountriesOrderedByName() {
		return "select Code, Name, Population from country order by Name";
	}
}
