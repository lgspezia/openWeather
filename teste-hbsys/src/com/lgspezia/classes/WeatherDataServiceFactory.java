package com.lgspezia.classes;

/**
 * 
 * @author Sagie Lev, Ohad Cohen, Shiran Shem-Tov
 *
 *         A class representing A factory for restful web services
 */
public class WeatherDataServiceFactory
{
	/**
	 * 
	 * An enum for the various web services
	 *
	 */
	public enum service
	{
		OPEN_WEATHER_MAP;
	}

	/**
	 * A static method that supplies a web service according to the parameter it
	 * recieves
	 * 
	 * @param i_Service
	 *            A parameter represents the service
	 * @return IWeatherDataService An object representing the web service
	 */
	public static IWeatherDataService getWeatherDataService(service i_Service)
	{
		switch (i_Service)
		{
		case OPEN_WEATHER_MAP:
			return OpenWeatherMap.getInstance();
		}

		return null;
	}
}