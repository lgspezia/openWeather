package com.lgspezia.weather;

/**
 * @author Sagie Lev, Ohad Cohen, Shiran Shem-Tov
 *
 *         A class representing the time that the information was last updated
 *         on, defined according to OpenWeatherMap.org
 */
public class LastUpdate
{
	private String value;

	/**
	 * LastUpdate object constructor receiving one parameter
	 * 
	 * @param value
	 *            Describes the time the information was last updated
	 */
	public LastUpdate(String value)
	{
		super();
		this.value = value;
	}

	/**
	 * A get property for the value of the last update
	 * 
	 * @return a string that describes the last update value
	 */
	public String getValue()
	{
		return value.replace('T', ' ');
	}

	/**
	 * A set property for the value of the last update
	 * 
	 * @param value
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * A Method that returns a string that describes the LastUpdate object
	 */
	@Override
	public String toString()
	{
		return "LastUpdate [Value=" + getValue() + "]";
	}
}