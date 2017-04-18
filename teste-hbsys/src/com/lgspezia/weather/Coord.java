package com.lgspezia.weather;

/**
 * @author Sagie Lev, Ohad Cohen, Shiran Shem-Tov
 *
 *         A class representing Coordinates of the city properties defined
 *         according to OpenWeatherMap.org
 */
public class Coord
{
	private String longitude;
	private String latitude;

	/**
	 * Coord object constructor receiving two parameters
	 * 
	 * @param Longitude
	 *            Describes the Longitude of the Coord
	 * @param Latitude
	 *            Describes the Latitude of the Coord
	 */
	public Coord(String longitude, String latitude)
	{
		super();
		setLatitude(latitude);
		setLongitude(longitude);
	}

	/**
	 * A get property for the longitude property
	 * 
	 * @return A string that describes the City geo location, longitude
	 */
	public String getLongitude()
	{
		return longitude;
	}

	/**
	 * A set property for the longitude property
	 * 
	 * @param longitude
	 */
	public void setLongitude(String longitude)
	{
		this.longitude = longitude;
	}

	/**
	 * A get property for the latitude property
	 * 
	 * @return A string that describes the City geo location, longitude
	 */
	public String getLatitude()
	{
		return latitude;
	}

	/**
	 * A set property for the latitude property
	 * 
	 * @param latitude
	 */
	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}

	/**
	 * A Method that returns a string that describes the Coord object
	 */
	@Override
	public String toString()
	{
		return "[" + getLongitude() + "," + getLatitude() + "]";
	}
}