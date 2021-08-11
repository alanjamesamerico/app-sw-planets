package br.com.sw.planets.helper;

import java.time.LocalDateTime;

import br.com.sw.planets.enums.FormatterEnum;

/**
 *	Class that helps with date and time formatting
 *	
 */
public class DateHelper {

	
	/**
	 * 	Convert a {@link LocalDateTime} object to time in HH:mm:ss format String
	 * 	<b>Example:</b> 21:05:77	
	 * 
	 * 	@param hour {@link LocalDateTime} 	
	 * 	@return a formatted {@link String}. <i>Example:</i> 21:05:77	
	 * 
	 */
	public static String formatHourToHHmmss(LocalDateTime hour) {
		return FormatterHelper
					.getTimeFormatted(FormatterEnum.HH_MM_SS.format())
					.format(hour)
					.toString();
	}
	
	/**
	 * 	Convert a {@link LocalDateTime} object to time in HH:mm:ss format String
	 * 	
	 * @param date {@link LocalDateTime} 
	 * @return a formatted {@link String}. <i>Example:</i> 26/10/1989 
	 * 
	 */
	public static String formatDateToDDmmyyyy(LocalDateTime date) {
		return FormatterHelper
					.getDateFormatted(FormatterEnum.DD_MM_YYYY.format())
					.format(date)
					.toString();
	}
	
	
	/**
	 * 	Convert a {@link LocalDateTime} object to time in dd/MM/yyyy HH:mm:ss format String.
	 * 
	 * 	@param date {@link LocalDateTime} 
	 * 	@return a formatted {@link String}. <i>Example:</i> 17:21:00 26/10/1989 
	 * 
	 */
	public static String formatDateAndHour(LocalDateTime date) {
		return FormatterHelper
					.getDateFormatted(FormatterEnum.DATE_AND_HOUR.format())
					.format(date)
					.toString();
	}
	
}
