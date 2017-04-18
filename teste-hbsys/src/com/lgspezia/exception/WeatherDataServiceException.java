package com.lgspezia.exception;

/**
 * @author Sagie Lev, Ohad Cohen, Shiran Shem-Tov
 *
 *         A class representing WeatherDataServiceException
 */
public class WeatherDataServiceException extends Exception
{
	private static final long serialVersionUID = 1L;

	/**
	 * A WeatherDataServiceException empty constructor
	 */
	public WeatherDataServiceException()
	{
		super();
	}

	/**
	 * A WeatherDataServiceException constructor that receives two parameters
	 * constructor
	 * 
	 * @param arg0
	 *            A string that describes the Exception
	 * @param arg1
	 *            A Throwable item that describes the inner exception
	 */
	public WeatherDataServiceException(String arg0, Throwable arg1)
	{
		super(arg0, arg1);
	}

	/**
	 * A WeatherDataServiceException constructor that receives one parameters
	 * 
	 * @param arg0
	 *            A string that describes the Exception
	 */
	public WeatherDataServiceException(String arg0)
	{
		super(arg0);
	}
}