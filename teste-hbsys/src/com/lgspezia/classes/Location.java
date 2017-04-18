package com.lgspezia.classes;

import com.lgspezia.exception.*;

/**
 * @author Sagie Lev, Ohad Cohen, Shiran Shem-Tov
 *
 *         A class representing Location properties, composed by city name,
 *         country code and temperature measuring unit
 */
public class Location
{
	private String city;
	private String country;
	private String unit = "Metric";

	/**
	 * Location object constructor receiving two parameters
	 * 
	 * @param city
	 *            Describes the current city
	 * @param country
	 *            Describes the current country code
	 * @throws WeatherDataServiceException
	 */
	public Location(String city, String country) throws WeatherDataServiceException
	{
		setCity(city);
		setCountry(country);
	}

	/**
	 * A get property for the location's temperature measuring unit
	 * 
	 * @return A string that represents the location's temperature measuring
	 *         unit
	 */
	public String getUnit()
	{
		return unit;
	}

	/**
	 * A set property for the location's temperature measuring unit
	 * 
	 * @param unit
	 *            Describes the measuring unit to be set for the location's
	 *            parameters
	 */
	public void setUnit(String unit)
	{
		this.unit = unit;
	}

	/**
	 * A get property for the location's city name
	 * 
	 * @return A string that represents the location's city name
	 */
	public String getCity()
	{
		return city;
	}

	/**
	 * A set property for the location's city name
	 * 
	 * @param city
	 *            Describes the city name to be set for the location
	 */
	public void setCity(String city)
	{
		this.city = city;
	}

	/**
	 * A get property for the location's country code
	 * 
	 * @return A string that represents the location's country code
	 */
	public String getCountry()
	{
		return country;
	}

	/**
	 * A set property for the location's country code
	 * 
	 * @param country
	 *            Describes the country code to be set for the location
	 */
	public void setCountry(String country)
	{
		this.country = country;
	}

	/**
	 * A Method that returns a string that describes the Location object
	 */
	@Override
	public String toString()
	{
		return "Location [City=" + city + ", Country=" + country + ", Unit=" + unit + "]";
	}
}