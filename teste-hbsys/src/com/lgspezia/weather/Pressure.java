package com.lgspezia.weather;

/**
 * 
 * @author Sagie Lev, Ohad Cohen, Shiran Shem-Tov
 *
 *         A class representing weather Pressure properties defined according to
 *         OpenWeatherMap.org
 */
public class Pressure
{
	private String value;
	private String unit;

	/**
	 * Pressure object constructor receiving two parameters
	 * 
	 * @param value
	 *            Describes the value of the Pressure
	 * @param unit
	 *            Describes the unit of the Pressure
	 */
	public Pressure(String value, String unit)
	{
		this.setValue(value);
		this.setUnit(unit);
	}

	/**
	 * A get property for the value of the Pressure
	 * 
	 * @return a string that describes the Pressure value
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * A set property for the value of the Pressure
	 * 
	 * @param value
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * A get property for the unit of the Pressure
	 * 
	 * @return a string that describes the Pressure unit
	 */
	public String getUnit()
	{
		return unit;
	}

	/**
	 * A set property for the unit of the Pressure
	 * 
	 * @param name
	 */
	public void setUnit(String unit)
	{
		this.unit = unit;
	}

	/**
	 * A Method that returns a string that describes the Pressure object
	 */
	@Override
	public String toString()
	{
		return getValue() + " " + getUnit();
	}
}