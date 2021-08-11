package br.com.sw.planets.helper;

import java.time.format.DateTimeFormatter;

/**
 * 	Class that provides templates for date and time formatting.
 * 
 */
public class FormatterHelper {
	
	/**
	 * 	Method responsible for formatting time in a specific format.
	 * 
	 */
	public static DateTimeFormatter getTimeFormatted(String format) {
		return DateTimeFormatter.ofPattern(format);
	}
	
	/**
	 * 	Method responsible for formatting date in a specific format.
	 * 
	 */
	public static DateTimeFormatter getDateFormatted(String format) {
		return DateTimeFormatter.ofPattern(format);
	}
}
