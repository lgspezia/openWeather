package com.lgspezia.weather;

/**
 * @author Sagie Lev, Ohad Cohen, Shiran Shem-Tov
 *
 *         A class representing Weather conditions defined according to
 *         OpenWeatherMap.org
 */
public class Weather
{
	private String number;
	private String value;
	private String icon;

	/**
	 * Pressure object constructor receiving three parameters
	 * 
	 * @param value
	 *            Describes the current weather
	 * @param icon
	 *            Describes the URL of the icon representing current weather
	 *            condition
	 * @param number
	 *            Describes the current weather condition ID
	 */
	public Weather(String value, String icon, String number)
	{
		super();
		setValue(value);
		setIcon(icon);
		setNumber(number);
	}

	/**
	 * A get property for the ID number of the current weather condition
	 * 
	 * @return a string that describes the ID number of the current weather
	 *         condition
	 */
	public String getNumber()
	{
		return number;
	}

	/**
	 * A set property for the ID number of the current weather condition
	 * 
	 * @param number
	 */
	public void setNumber(String number)
	{
		this.number = number;
	}

	/**
	 * A get property for the value of the current weather condition
	 * 
	 * @return a string that describes the value of the current weather
	 *         condition
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * A set property for the value of the current weather condition
	 * 
	 * @param value
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * A get property for the URL of the icon that represents the current
	 * weather condition
	 * 
	 * @return a string that describes the URL of the icon that represents the
	 *         current weather condition
	 */
	public String getIcon()
	{
		return icon;
	}

	/**
	 * A set property for the URL of the icon representing current weather
	 * condition
	 * 
	 * @param icon
	 */
	public void setIcon(String icon)
	{
		this.icon = icon;
	}

	/**
	 * A method that returns a string that describes the Weather object
	 */
	@Override
	public String toString()
	{
		return "Weather [Value=" + getValue() + ", \nIcon=" + getIcon() + ", Number=" + getNumber() + "]";
	}
}