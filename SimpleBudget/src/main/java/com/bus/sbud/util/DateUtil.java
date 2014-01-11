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

	public static Date parseDate(String stringDate) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

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
		System.out.println(parseDate("2014-01-11"));

	}

}
