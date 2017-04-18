package com.lgspezia.weather;

/**
 * @author Sagie Lev, Ohad Cohen, Shiran Shem-Tov
 *
 *         A class representing Direction properties defined according to
 *         OpenWeatherMap.org
 */
public class Direction
{
	private String value;
	private String name;
	private String code;

	/**
	 * Direction object constructor receiving two parameters
	 * 
	 * @param value
	 *            The Direction value
	 * @param code
	 *            The Direction code
	 * @param name
	 *            The Direction name
	 */
	public Direction(String value, String code, String name)
	{
		super();
		this.value = value;
		this.code = code;
		this.name = name;
	}

	/**
	 * A get property for the value of the Direction
	 * 
	 * @return
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * A set property for the value of the Direction
	 * 
	 * @param value
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * A get property for the name of the Direction
	 * 
	 * @return A string that describes the name of the Direction
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * A set property for the name of the Direction
	 * 
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * A get property for the Direction code
	 * 
	 * @return A string that describes the Direction code
	 */
	public String getDirectionCode()
	{
		return code;
	}

	/**
	 * A set property for the Direction code
	 * 
	 * @param directionCode
	 */
	public void setDirectionCode(String directionCode)
	{
		this.code = directionCode;
	}

	/**
	 * A Method that returns a string that describes the Direction object
	 */
	@Override
	public String toString()
	{
		return getValue() + ", " + getName();
	}
}