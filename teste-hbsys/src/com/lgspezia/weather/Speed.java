package com.lgspezia.weather;

/**
 * @author Sagie Lev, Ohad Cohen, Shiran Shem-Tov
 *
 *         A class representing wind speed properties defined according to
 *         OpenWeatherMap.org
 */
public class Speed
{
	private String value;
	private String name;

	/**
	 * A wind speed object constructor receiving two parameters
	 * 
	 * @param value
	 *            Describes the value of the speed
	 * @param name
	 *            Describes the name of the speed
	 */
	public Speed(String value, String name)
	{
		super();
		setValue(value);
		setName(name);
	}

	/**
	 * A get property for the value of the speed
	 * 
	 * @return Returns A string representing the speed value
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * A set property for the value of the speed
	 * 
	 * @param value
	 *            A string representing the value of the speed
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * A get property for the name of the speed
	 * 
	 * @return Returns A string representing the speed name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * A set property for the name of the speed
	 * 
	 * @param value
	 *            A string representing the name of the speed
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * A Method that returns a string that describes the speed object
	 */
	@Override
	public String toString()
	{
		return getValue() + "m/s, " + getName();
	}
}