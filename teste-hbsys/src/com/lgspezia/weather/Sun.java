package com.lgspezia.weather;

/**
 * @author Sagie Lev, Ohad Cohen, Shiran Shem-Tov
 *
 *         A class representing Sun properties defined according to
 *         OpenWeatherMap.org
 */
public class Sun
{
	private String rise;
	private String set;

	/**
	 * Sun object constructor receiving two parameters
	 * 
	 * @param rise
	 *            Describes the sun rise time
	 * @param set
	 *            Describes the sunset time
	 */
	public Sun(String rise, String set)
	{
		super();
		setRise(rise);
		setSet(set);
	}

	/**
	 * A get property for the sun rise time parameter
	 * 
	 * @return returns a string that describes the time of sun rise
	 */
	public String getRise()
	{
		return rise.replace('T', ' ');
	}

	/**
	 * A set property for the sun rise time parameter
	 */
	public void setRise(String rise)
	{
		this.rise = rise;
	}

	/**
	 * A get property for the sun set time parameter
	 * 
	 * @return returns a string that describes the time of sun set
	 */
	public String getSet()
	{
		return set.replace('T', ' ');
	}

	/**
	 * A set property for the sun set time parameter
	 */
	public void setSet(String set)
	{
		this.set = set;
	}

	/**
	 * A Method that returns a string that describes the Sun object
	 */
	@Override
	public String toString()
	{
		return "[Rise: " + getRise() + "] [Set: " + getSet() + "]";
	}
}