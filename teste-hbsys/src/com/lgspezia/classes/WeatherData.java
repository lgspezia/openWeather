package com.lgspezia.classes;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.lgspezia.weather.*;

/**
 * @author Sagie Lev, Ohad Cohen, Shiran Shem-Tov
 *
 *         A class representing WeatherData properties defined according to
 *         OpenWeatherMap.org
 */
public class WeatherData
{
	private City city;
	private Clouds clouds;
	private Direction direction;
	private Humidity humidity;
	private LastUpdate lastUpdate;
	private Precipitation precipitation;
	private Pressure pressure;
	private Temperature temperature;
	private Visibility visibility;
	private Weather weather;
	private Wind wind;
	private Speed speed;
	private Sun sun;

	/**
	 * A method that sets the attribute of the class according to the tag name
	 * received and sets the value given by the element
	 * 
	 * @param currentTagName
	 *            The name of the attribute currently being set
	 * @param rootElement
	 *            The value that will be set to the attribute
	 */
	private void setAttribute(String currentTagName, Element rootElement)
	{
		NodeList currentList = rootElement.getElementsByTagName(currentTagName);
		Node currentNode = currentList.item(0);
		Element currentElement = (Element) currentNode;

		switch (currentTagName)
		{
		case "city":
			city = new City(currentElement.getAttribute("id"), currentElement.getAttribute("name"));
			break;
		case "coord":
			Coord coord = new Coord(currentElement.getAttribute("lon"), currentElement.getAttribute("lat"));
			city.setCoordinates(coord);
			break;
		case "country":
			city.setCountry(currentElement.getTextContent());
			break;
		case "sun":
			sun = new Sun(currentElement.getAttribute("rise"), currentElement.getAttribute("set"));
			city.setSun(sun);
			break;
		case "temperature":
			temperature = new Temperature(currentElement.getAttribute("value"), currentElement.getAttribute("min"),
					currentElement.getAttribute("max"), currentElement.getAttribute("unit"));
			break;
		case "humidity":
			humidity = new Humidity(currentElement.getAttribute("value"), currentElement.getAttribute("unit"));
			break;
		case "pressure":
			pressure = new Pressure(currentElement.getAttribute("value"), currentElement.getAttribute("unit"));
			break;
		case "wind":
			wind = new Wind();
			break;
		case "speed":
			Speed speed = new Speed(currentElement.getAttribute("value"), currentElement.getAttribute("name"));
			wind.setSpeed(speed);
			break;
		case "direction":
			Direction direction = new Direction(currentElement.getAttribute("value"),
					currentElement.getAttribute("code"), currentElement.getAttribute("name"));
			wind.setDirection(direction);
			break;
		case "clouds":
			clouds = new Clouds(currentElement.getAttribute("value"), currentElement.getAttribute("name"));
			break;
		case "visibility":
			visibility = new Visibility(currentElement.getAttribute("value"));
			break;
		case "precipitation":
			precipitation = new Precipitation(currentElement.getAttribute("value"),
					currentElement.getAttribute("mode"));
			break;
		case "weather":
			weather = new Weather(currentElement.getAttribute("value"), currentElement.getAttribute("icon"),
					currentElement.getAttribute("number"));
			break;
		case "lastupdate":
			lastUpdate = new LastUpdate(currentElement.getAttribute("value"));
			break;
		}
	}

	/**
	 * A method that sets all attributes according to a parsed document
	 * 
	 * @param doc
	 *            The parsed document
	 */
	public void setAllWeatherDataByTagNames(Document doc)
	{
		NodeList list = doc.getElementsByTagName("current");
		Node rootNode = list.item(0);
		Element rootElement = (Element) rootNode;

		setAttribute("city", rootElement);
		setAttribute("coord", rootElement);
		setAttribute("country", rootElement);
		setAttribute("sun", rootElement);
		setAttribute("temperature", rootElement);
		setAttribute("humidity", rootElement);
		setAttribute("pressure", rootElement);
		setAttribute("wind", rootElement);
		setAttribute("speed", rootElement);
		setAttribute("direction", rootElement);
		setAttribute("clouds", rootElement);
		setAttribute("visibility", rootElement);
		setAttribute("precipitation", rootElement);
		setAttribute("weather", rootElement);
		setAttribute("lastupdate", rootElement);
	}

	/**
	 * A Method that returns a string that describes the WeatherData object
	 */
	@Override
	public String toString()
	{
		return "WeatherData [Clouds=" + getClouds()
		+ ", Wind=" + getWind()
		+ ",\nLast update=" + getLastUpdate()
				+ ", Humidity=" + getHumidity()
				+ ", \nPressure=" + getPressure()
				+ ", Visibility=" + getVisibility()
				+ ", \nPrecipitation=" + getPrecipitation()
				+ ", Weather=" + getWeather()
				+ ", Temperature=" + getTemperature() + "]";
	}

	/**
	 * A get property for the WeatherData's city
	 * 
	 * @return A City object that represents the WeatherData's city
	 */
	public City getCity()
	{
		return city;
	}

	/**
	 * A get property for the WeatherData's clouds
	 * 
	 * @return A Clouds object that represents the WeatherData's clouds
	 */
	public Clouds getClouds()
	{
		return clouds;
	}

	/**
	 * A get property for the WeatherData's wind direction
	 * 
	 * @return A Direction object that represents the WeatherData's wind
	 *         direction
	 */
	public Direction getDirection()
	{
		return direction;
	}

	/**
	 * A get property for the WeatherData's Humidity
	 * 
	 * @return A Humidity object that represents the WeatherData's Humidity
	 */
	public Humidity getHumidity()
	{
		return humidity;
	}

	/**
	 * A get property for the WeatherData's Last update date and time
	 * 
	 * @return A LastUpdate object that represents the WeatherData's Last update
	 *         date and time
	 */
	public LastUpdate getLastUpdate()
	{
		return lastUpdate;
	}

	/**
	 * A get property for the WeatherData's Precipitation
	 * 
	 * @return A Precipitation object that represents the WeatherData's
	 *         Precipitation
	 */
	public Precipitation getPrecipitation()
	{
		return precipitation;
	}

	/**
	 * A get property for the WeatherData's Pressure
	 * 
	 * @return A Pressure object that represents the WeatherData's Pressure
	 */
	public Pressure getPressure()
	{
		return pressure;
	}

	/**
	 * A get property for the WeatherData's wind speed
	 * 
	 * @return A Speed object that represents the WeatherData's wind speed
	 */
	public Speed getSpeed()
	{
		return speed;
	}

	/**
	 * A get property for the WeatherData's Sun attributes
	 * 
	 * @return A Sun object that represents the WeatherData's Sun attributes
	 */
	public Sun getSun()
	{
		return sun;
	}

	/**
	 * A get property for the WeatherData's Temperature
	 * 
	 * @return A Temperature object that represents the WeatherData's
	 *         Temperature
	 */
	public Temperature getTemperature()
	{
		return temperature;
	}

	/**
	 * A get property for the WeatherData's Visibility
	 * 
	 * @return A Visibility object that represents the WeatherData's Visibility
	 */
	public Visibility getVisibility()
	{
		return visibility;
	}

	/**
	 * A get property for the WeatherData's Weather
	 * 
	 * @return A Weather object that represents the WeatherData's Weather
	 */
	public Weather getWeather()
	{
		return weather;
	}

	/**
	 * A get property for the WeatherData's wind
	 * 
	 * @return A Wind object that represents the WeatherData's wind
	 */
	public Wind getWind()
	{
		return wind;
	}
}