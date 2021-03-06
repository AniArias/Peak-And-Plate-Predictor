package com.prueba.picoplaca;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

public class PicoyPlaca extends TestCase {
	
	 public static final int to_pico_1 = 700;
	 public static final int from_pico_1 = 730;	 
	 public static final int to_pico_2 = 1600;
	 public static final int from_pico_2 = 1930;
	 
	private static int validaPicoPlaca(String plate, String date, int time) throws ParseException{
		
		String lastNumber="";
		int day=0;
		int isValid=1;
		
		//Getting the last number of entire plate number
		lastNumber = getLastNumber(plate);
		//Getting the day of the week from the date to predict. It will be a number
		day = getDay(date);
		//If the time is between a range wich is not a peak and plate time so the user can road
		if(!isInRange(time,to_pico_1,from_pico_1)&&!isInRange(time,to_pico_2,from_pico_2)){
			isValid=1;
		}else{
			switch(day){
				case 2:{
					if((lastNumber.equals("1"))||lastNumber.equals("2"))
						isValid=0;
				}
				case 3:{
					if((lastNumber.equals("3"))||lastNumber.equals("4"))
						isValid=0;
				}
				case 4:{
					if((lastNumber.equals("5"))||lastNumber.equals("6"))
						isValid=0;
				}
				case 5:{
					if((lastNumber.equals("7"))||lastNumber.equals("8"))
						isValid=0;
				}
				case 6:{
					if((lastNumber.equals("0"))||lastNumber.equals("9"))
						isValid=0;
				}
			}
		}
		
		return isValid;
		
	}
	
	private static String getLastNumber(String placa){
		return placa.substring(placa.length()-1);
	}
	
	private static boolean isInRange(int now_time, int start_time, int end_time) {

	       if ((now_time>start_time)&&(now_time<end_time))
	       {
	        return true;
	       }
	        return false;
	    }

	private static int getDay(String date) throws ParseException{
		Date format_date = null; 
		format_date = new SimpleDateFormat("dd/M/yyyy").parse(date);
		Calendar c = Calendar.getInstance();
		c.setTime(format_date);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek;
	}
		
	public static void main(String[] args) {
		
		String date_user="8/4/2016";
		String plate_user="1230";
		int time_user=2000;
		int isValid=0;
		
		try {
			
			isValid=validaPicoPlaca(plate_user,date_user,time_user);
			
			assertEquals(false, isInRange(time_user,to_pico_1,from_pico_1));
			assertEquals(false, isInRange(time_user,to_pico_2,from_pico_2));
			assertEquals(1, isValid);
			
			if(isValid==0)
				System.out.println("SORRY, You can't road");
			else
				System.out.println("YES, You can road");
		} catch (ParseException e) {
			System.out.println("Upss!! Something is wrong! :(");
		}

	}
	
	
}
