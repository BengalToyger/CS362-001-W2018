package calendar;


import org.junit.Test;
import java.util.*;
import java.io.*;

import static org.junit.Assert.*;



/**
 * Random Test Generator  for CalDay class.
 */

public class CalDayRandomTest {
	
	/**
	* Generate Random Tests that tests CalDay Class.
	*/
	@Test
	public void addApptTest1()  throws Throwable  {
		//Tests add appointment to CalDay
		//Makes a CalDay
		//Adds a random number of appointments, each with a random hour	 
		//Checks to see they are all in the right order 
		CalDay day = new CalDay(new GregorianCalendar()); 
		Random random = new Random();
		int numAppts = 100;
		System.out.println("Start addAppt testing...");
		PrintWriter outputStream = null; 
		outputStream = new PrintWriter("caldaytest.txt","UTF-8"); 
		for (int i = 0; i < numAppts; i++){
			//Create and add all appts to CalDay
			//Check to see that the size is correct	
			int startHour = ValuesGenerator.getRandomIntBetween(random,0,23);
			int startMinute = ValuesGenerator.getRandomIntBetween(random,0,59);
			Appt appt = new Appt(startHour,startMinute,1,3,1,"title","desc");
			assertTrue(appt.getValid());
			day.addAppt(appt);
			int expectedSize = i+1;
			int actualSize = day.getSizeAppts();
			outputStream.println("Expected Size: " + expectedSize);
			outputStream.println("Actual Size: " + actualSize);
			if (expectedSize != actualSize) {
				outputStream.println("SIZE MISMATCH");
			}
		}
		//Get all appointments from the CalDay, iterate through 
		//and make sure they are in order
		//Order should be least to greatest hour
		LinkedList<Appt> appts = day.getAppts();	
		int i = 0;
		for (i = 0; i < numAppts-1; i++){
			outputStream.println(appts.get(i).getStartHour());
			if (appts.get(i).getStartHour() > appts.get(i+1).getStartHour()){
				outputStream.println("OUT OF ORDER");
			}
		}
		outputStream.println(appts.get(i).getStartHour());
		outputStream.close();
	}


	
}
