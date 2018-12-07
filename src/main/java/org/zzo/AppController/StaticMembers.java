package org.zzo.AppController;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;


public class StaticMembers {
	
	
	
	
	
	
	
	/*public static void main(String[] args) {
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDate localDate = LocalDate.now();
		
		System.out.println(asDate(localDate).toString());
		System.out.println(asDate(localDateTime).toString());
		
		System.out.println(asLocalDate(asDate(localDate)).toString());
		System.out.println(asLocalDateTime(asDate(localDateTime)).toString());
		
		System.out.println(getLocalDate("21.11.2015","d.MM.yyyy"));
		System.out.println("---");
	}*/

	public static LocalDate getLocalDate(String date, String format) {
		
		DateTimeFormatter dateTimeFormatter;
		LocalDate localDate;
		
		try {
			dateTimeFormatter = DateTimeFormatter.ofPattern(format);
			localDate = LocalDate.parse(date, dateTimeFormatter);
			return localDate;
		}catch(DateTimeParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static LocalDateTime getLocalDateTime(String date, String format) {
		
		DateTimeFormatter dateTimeFormatter;
		LocalDateTime localDateTime;
		
		try {
			dateTimeFormatter = DateTimeFormatter.ofPattern(format);
			localDateTime = LocalDateTime.parse(date, dateTimeFormatter);
			return localDateTime;
		}catch(DateTimeParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
	public static Date asDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date asDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public static LocalDate asLocalDate(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static LocalDateTime asLocalDateTime(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
	 
	

}
