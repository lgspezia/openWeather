package com.lgspezia.weather;

/**
 * @author Sagie Lev, Ohad Cohen, Shiran Shem-Tov
 *
 *         A class representing Wind properties defined according to
 *         OpenWeatherMap.org
 */
public class Wind
{
	private Speed speed;
	private Direction direction;

	/**
	 * Wind object constructor receiving two parameters
	 * 
	 * @param speed
	 *            Describes the speed of the Wind
	 * @param direction
	 *            Describes the direction of the wind
	 */
	public Wind(Speed speed, Direction direction)
	{
		super();
		setSpeed(speed);
		setDirection(direction);
	}

	/**
	 * Wind object constructor receiving no parameters
	 */
	public Wind()
	{

	}

	/**
	 * A get property for the wind speed parameter
	 * 
	 * @return an object that describes the speed
	 */
	public Speed getSpeed()
	{
		return speed;
	}

	/**
	 * A set property for the wind speed parameter
	 * 
	 * @param speed
	 *            Sets the wind speed received from the restful web service XML
	 *            parsing
	 */
	public void setSpeed(Speed speed)
	{
		this.speed = speed;
	}

	/**
	 * A get property for the wind direction parameter
	 * 
	 * @return an object that describes the direction
	 */
	public Direction getDirection()
	{
		return direction;
	}

	/**
	 * A set property for the wind direction parameter
	 * 
	 * @param speed
	 *            Sets the wind direction received from the restful web service
	 *            XML parsing
	 */
	public void setDirection(Direction direction)
	{
		this.direction = direction;
	}

	/**
	 * A Method that returns a string that describes the wind object
	 */
	@Override
	public String toString()
	{
		return getSpeed() + ", " + getDirection();
	}
}