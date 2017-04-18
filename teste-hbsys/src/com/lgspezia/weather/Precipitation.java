package com.lgspezia.weather;

/**
 * @author Sagie Lev, Ohad Cohen, Shiran Shem-Tov
 *
 *         A class representing Precipitation properties defined according to
 *         OpenWeatherMap.org
 */
public class Precipitation
{
	private String value;
	private String mode;

	/**
	 * Precipitation object constructor receiving two parameters
	 * 
	 * @param value
	 *            Describes the value of the Precipitation
	 * @param mode
	 *            Describes the mode of the Precipitation
	 */
	public Precipitation(String value, String mode)
	{
		this.value = value;
		this.mode = mode;
	}

	/**
	 * A get property for the value of the Precipitation
	 * 
	 * @return A string that describes the Precipitation, mm
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * A set property for the value of the Precipitation
	 * 
	 * @param value
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * A get property for the Mode of the Precipitation
	 * 
	 * @return A string that describes the Mode of the Precipitation. Possible
	 *         values are 'no", name of weather phenomena as 'rain', 'snow'
	 */
	public String getMode()
	{
		return mode;
	}

	/**
	 * A set property for the Mode of the Precipitation
	 * 
	 * @param mode
	 */
	public void setMode(String mode)
	{
		this.mode = mode;
	}

	/**
	 * A Method that returns a string that describes the Precipitation object
	 */
	@Override
	public String toString()
	{
		return "[value=" + getValue() + ", mode=" + getMode() + "]";
	}
}