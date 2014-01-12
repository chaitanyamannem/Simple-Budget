/**
 * 
 */
package com.bus.sbud.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chaitanyam
 * 
 */
public class DateUtil {
	
	public static final String DATE_FORMAT_YYYY_MM_DD_WITH_DASH = "yyyy-MM-dd";
	public static final String DATE_FORMAT_DD_MM_YYYY_WITH_SLASH = "dd/MM/yyyy";

	public static Date parseDate(String stringDate, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

		Date date = null;
		try {
			date = simpleDateFormat.parse(stringDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(parseDate("2014-01-11",DATE_FORMAT_YYYY_MM_DD_WITH_DASH));
		System.out.println(parseDate("15/01/2014",DATE_FORMAT_DD_MM_YYYY_WITH_SLASH));
	}

}
