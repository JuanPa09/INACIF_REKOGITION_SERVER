package com.inacif.rekognition.web.app;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.text.ParseException;

import com.inacif.rekognition.web.app.entity.Settings;

public class Utils {
	
	public static String getExpirationTimeZone(int expirationDays) {
		ZoneId zoneId = ZoneId.of(Constants.timeZone);
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        zonedDateTime = zonedDateTime.plusDays(expirationDays);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return zonedDateTime.format(formatter);
	}
	
	public static String getTimeZone() {
        ZoneId zoneId = ZoneId.of(Constants.timeZone);
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        zonedDateTime.plusDays(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return zonedDateTime.format(formatter);
	}
	
	public static String getTimeZoneWithFormat(String format) {
        ZoneId zoneId = ZoneId.of(Constants.timeZone);
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return zonedDateTime.format(formatter);
	}
	
	public static String getDateWithFormat(String date) {
		SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat nuevoFormato = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date fecha = formatoOriginal.parse(date);
            return nuevoFormato.format(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public static Boolean isEmpty(Object value) {
		if (value == null) {
            return true;
        }

        if (value instanceof Object[]) {
            return ((Object[]) value).length == 0;
        }

        return false;
	}
	
	public static Integer getAgeByDate(String birthDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthLocalDate = LocalDate.parse(birthDate, formatter);
        LocalDate currentDate = LocalDate.now();
        
        Period period = Period.between(birthLocalDate, currentDate);
       
        return period.getYears();
	}
	
	public static Settings getDefaultSettings() {
		Settings defaultSettings 
			= new Settings(
					0,0,0,0,0,0,80,0);
		return defaultSettings;
	}
	
}
