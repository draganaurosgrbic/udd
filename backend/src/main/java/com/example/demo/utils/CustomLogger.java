package com.example.demo.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CustomLogger {

	private static final Logger LOGGER = LoggerFactory.getLogger("logger");

	public void storeApplicationFormAccessLog(String city, Double lat, Double lng) {
		LOGGER.info("city: " + city + ", lat: " + lat + ", lng: " + lng);
	}

	public void storeApplicationSubmitLog() {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(new Date());
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		String period;
		if (hours < 6) {
			period = "night";
		} else if (hours < 12) {
			period = "morning";
		} else if (hours < 18) {
			period = "afternoon";
		} else {
			period = "evening";
		}
		LOGGER.info("submission: " + period + ", " + hours);
	}

}
