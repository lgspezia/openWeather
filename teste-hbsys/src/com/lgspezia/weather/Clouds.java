package com.lgspezia.weather;

/**
 * @author Sagie Lev, Ohad Cohen, Shiran Shem-Tov
 *
 *         A class representing Clounds properties defined according to
 *         OpenWeatherMap.org
 */
public class Clouds
{
	private String value;
	private String name;

	/**
	 * Clouds object constructor receiving two parameters
	 * 
	 * @param name
	 *            Describes the Name of the clouds
	 * @param value
	 *            Describes the value of the clouds
	 */
	public Clouds(String name, String value)
	{
		super();
		this.name = name;
		this.value = value;
	}

	/**
	 * A get property for the value of the clouds
	 * 
	 * @return a string that describes the weather's Cloudiness
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * A set property for the value of the clouds
	 * 
	 * @param value
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * A get property for the Name of the clouds
	 * 
	 * @return a string that describes the Name of the cloudiness
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * A set property for the Name of the clouds
	 * 
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * A Method that returns a string that describes the Clouds object
	 */
	@Override
	public String toString()
	{
		return getValue() + " : " + getName();
	}
}