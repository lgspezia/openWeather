package com.lgspezia.weather;

/**
 * @author Sagie Lev, Ohad Cohen, Shiran Shem-Tov
 *
 *         A class representing Temperature properties defined according to
 *         OpenWeatherMap.org
 */
public class Temperature
{
	private String value;
	private String max;
	private String min;
	private String unit;

	/**
	 * Temperature object constructor receiving two parameters
	 * 
	 * @param value
	 *            Describes the value of the current Temperature
	 * @param min
	 *            Describes the value of the min Temperature
	 * @param max
	 *            Describes the value of the max Temperature
	 * @param unit
	 *            Describes the value of the unit of the Temperature
	 */
	public Temperature(String value, String min, String max, String unit)
	{
		setValue(value);
		setMin(min);
		setMax(max);
		setUnit(unit);
	}

	/**
	 * A get property for the value of the Temperature
	 * 
	 * @return A string that describes the value of the Temperature
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * A set property for the value of the Temperature
	 * 
	 * @param value
	 *            Describes the current Temperature
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * A get property for the maximal Temperature
	 * 
	 * @return A string that describes the maximal Temperature
	 */
	public String getMax()
	{
		return max;
	}

	/**
	 * A set property for the max Temperature
	 * 
	 * @param max
	 *            Describes the maximal Temperature
	 */
	public void setMax(String max)
	{
		this.max = max;
	}

	/**
	 * A get property for the min Temperature
	 * 
	 * @return A string that describes the min Temperature
	 */
	public String getMin()
	{
		return min;
	}

	/**
	 * A set property for the minimal Temperature
	 * 
	 * @param min
	 *            Describes the minimal Temperature
	 */
	public void setMin(String min)
	{
		this.min = min;
	}

	/**
	 * A get property for the unit of the Temperature
	 * 
	 * @return A string that describes the Unit of the Temperature
	 */
	public String getUnit()
	{
		return unit;
	}

	/**
	 * A set property for the unit of the Temperature
	 * 
	 * @param unit
	 *            Describes the unit of the Temperature
	 */
	public void setUnit(String unit)
	{
		this.unit = unit;
	}

	/**
	 * A Method that returns a string that describes the Temperature object
	 */
	@Override
	public String toString()
	{
		return "[Current=" + getValue() + "] [Minimal=" + getMin() + "] [Maximal=" + getMax() + "] [Unit=" + getUnit()
				+ "]";
	}
}