package com.lgspezia.weather;

/**
 * @author Sagie Lev, Ohad Cohen, Shiran Shem-Tov
 *
 *         A class representing weather Visibility properties defined according
 *         to OpenWeatherMap.org
 */
public class Visibility
{
	private String value;

	/**
	 * Pressure object constructor receiving one parameter
	 * 
	 * @param value
	 *            Describes the value of the Visibility
	 */
	public Visibility(String value)
	{
		super();
		setValue(value);
	}

	/**
	 * A get property for the value of the Visibility
	 * 
	 * @return a string that describes the Visibility value
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * A set property for the value of the Visibility
	 * 
	 * @param value
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * A Method that returns a string that describes the Visibility object
	 */
	@Override
	public String toString()
	{
		return "Visibility [Value=" + getValue() + "]";
	}
}