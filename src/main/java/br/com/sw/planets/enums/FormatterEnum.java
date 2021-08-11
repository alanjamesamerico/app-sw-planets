package br.com.sw.planets.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *	Class that provides date and time format templates
 *
 */
@Getter
@AllArgsConstructor
public enum FormatterEnum {
	
	/**
	 * 	Time format in HH:mm:ss
	 * 	<b>Example:</b> 20:05:77 or 07:10:00
	 */
	HH_MM_SS ("HH:mm:ss"),
	
	
	/**
	 * 	Date format in dd/MM/yyyy
	 * 	<b>Example:</b> 26/10/1989
	 */
	DD_MM_YYYY ("dd/MM/yyyy"),
	
	
	/**
	 * 	Date format in dd/MM/yyyy-HH:mm:ss
	 * 	<b>Example:</b> 26/10/1989:19:07:33
	 */
	DATE_AND_HOUR("dd/MM/yyyy-HH:mm:ss");
	
	private String formatter;
	
	public String format() {
		return this.formatter;
	}
}
