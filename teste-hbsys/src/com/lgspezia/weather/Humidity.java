package com.lgspezia.weather;

/**
 * @author Sagie Lev, Ohad Cohen, Shiran Shem-Tov
 *
 *         A class representing Humidity properties defined according to
 *         OpenWeatherMap.org
 */
public class Humidity
{
	private String value;
	private String unit;

	/**
	 * Humidity object constructor receiving two parameters
	 * 
	 * @param value
	 *            Describes the value of the Humidity
	 * @param unit
	 *            Describes the unit of the Humidity
	 */
	public Humidity(String value, String unit)
	{
		setValue(value);
		setUnit(unit);
	}

	/**
	 * A get property for the value of the Humidity
	 * 
	 * @return a string that describes the Humidity value
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * A set property for the value of the Humidity
	 * 
	 * @param value
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * A get property for the unit of the Humidity
	 * 
	 * @return a string that describes the Humidity unit
	 */
	public String getUnit()
	{
		return unit;
	}

	/**
	 * A set property for the unit of the Humidity
	 * 
	 * @param name
	 */
	public void setUnit(String unit)
	{
		this.unit = unit;
	}

	/**
	 * A Method that returns a string that describes the Humidity object
	 */
	@Override
	public String toString()
	{
		return getValue() + getUnit();
	}
}
