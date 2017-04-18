package com.lgspezia.weather;

/**
 * @author Sagie Lev, Ohad Cohen, Shiran Shem-Tov
 *
 *         A class representing City properties defined according to
 *         OpenWeatherMap.org
 */
public class City
{
	private String id;
	private String name;
	private String country;
	private Coord coord;
	private Sun sun;

	/**
	 * City object constructor receiving two parameters
	 * 
	 * @param ID
	 *            Describes the id of the city
	 * @param name
	 *            Describes the name of the city
	 */
	public City(String ID, String name)
	{
		setID(ID);
		setName(name);
	}

	/**
	 * A get property for the city ID property
	 * 
	 * @return A string that describes the city id
	 */
	public String getID()
	{
		return id;
	}

	/**
	 * A set property for the city ID property
	 * 
	 * @param id
	 */
	public void setID(String id)
	{
		this.id = id;
	}

	/**
	 * A get property for the city name property
	 * 
	 * @return A string that describes the city name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * A set property for the city name property
	 * 
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * A get property for the Country property
	 * 
	 * @return A string that describes the Country name
	 */
	public String getCountry()
	{
		return country;
	}

	/**
	 * A set property for the Country property
	 * 
	 * @param countryCode
	 */
	public void setCountry(String countryCode)
	{
		this.country = countryCode;
	}

	/**
	 * A get property for the Coordinates (Coord) property
	 * 
	 * @return An object that describes the City geo location.
	 */
	public Coord getCoordinates()
	{
		return coord;
	}

	/**
	 * A set property for the Coordinates (Coord) property
	 * 
	 * @param coordinates
	 */
	public void setCoordinates(Coord coordinates)
	{
		this.coord = coordinates;
	}

	/**
	 * A get property for Sun property
	 * 
	 * @return An object that describes the sun object
	 */
	public Sun getSun()
	{
		return sun;
	}

	/**
	 * A set property for the sun object
	 * 
	 * @param sun
	 */
	public void setSun(Sun sun)
	{
		this.sun = sun;
	}

	/**
	 * A Method that returns a string that describes the city object
	 */
	@Override
	public String toString()
	{
		return "[ID=" + getID() + ", Name=" + getName() + ", Country Code=" + getCountry() + ", Coord="
				+ getCoordinates() + ", Sun=" + getSun() + "]";
	}
}