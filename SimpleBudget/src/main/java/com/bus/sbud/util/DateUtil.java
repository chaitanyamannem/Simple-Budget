/**
 * 
 */
package com.bus.sbud.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import org.springframework.util.StringUtils;

/**
 * @author chaitanyam
 * 
 */
public class DateUtil {
	
	private static final Logger logger = Logger.getLogger("DateUtil");

	public static final String DATE_FORMAT_YYYY_MM_DD_WITH_DASH = "yyyy-MM-dd";
	public static final String DATE_FORMAT_DD_MM_YYYY_WITH_SLASH = "dd/MM/yyyy";
	public static final String FORMAT_MM = "MM";
	public static final String FORMAT_DD = "dd";

	public static Date parseDate(String stringDate, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		Date date = null;
		if(StringUtils.isEmpty(stringDate)){
			return date;
		}
		try {
			date = simpleDateFormat.parse(stringDate);
		} catch (ParseException e) {
			logger.warning("Unable to parse date" + e.getMessage());
		}

		return date;
	}
	
	public static String formatDate(Date date, String format){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}
	
	public static String getNumericDay(Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DD);
		return simpleDateFormat.format(date);
	}
	
	public static String getMonth(Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_MM);
		return simpleDateFormat.format(date);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(parseDate("2014-01-11",
				DATE_FORMAT_YYYY_MM_DD_WITH_DASH));
		System.out.println(parseDate("15/01/2014",
				DATE_FORMAT_DD_MM_YYYY_WITH_SLASH));
		System.out.println(formatDate(new Date(), DATE_FORMAT_DD_MM_YYYY_WITH_SLASH));
		
	}

}
