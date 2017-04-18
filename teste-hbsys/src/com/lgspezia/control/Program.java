package com.lgspezia.control;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.lgspezia.exception.*;
import com.lgspezia.classes.*;
import com.lgspezia.classes.WeatherDataServiceFactory.service;

public class Program
{
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
	public List<String> listar() {
		IWeatherDataService dataService = WeatherDataServiceFactory.getWeatherDataService(service.OPEN_WEATHER_MAP);
		WeatherData data;
		List<String> CityForecast = new ArrayList<String>();
		try
		{
			
			data = dataService.getWeatherData(new Location("Blumenau", "BR"));
			CityForecast.add(data.toString());

			data = dataService.getWeatherData(new Location("Curitiba", "BR"));
			CityForecast.add(data.toString());
			
			data = dataService.getWeatherData(new Location("Koln", "GE"));
			CityForecast.add(data.toString());
			
		} catch (WeatherDataServiceException e)
		{
			e.printStackTrace();
		}
		return CityForecast;
	}
}