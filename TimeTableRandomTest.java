package calendar;

import java.util.*;
import java.io.*;
import org.junit.Test;


import static org.junit.Assert.*;



/**
 * Random Test Generator  for TimeTable class.
 */

public class TimeTableRandomTest {
	public static int RandomSelectRecur(Random random){
		        int[] RecurArray = new int[] {Appt.RECUR_BY_WEEKLY,Appt.RECUR_BY_MONTHLY,Appt.RECUR_BY_YEARLY};// The list of the of setting appointments to recur Weekly,Monthly, or Yearly

			    	int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
				        return RecurArray[n] ; // return the value of the  appointments to recur 
					        }	
		/**
		 * 	 * Return a randomly selected appointments to recur forever or Never recur  !.
		 * 	 	 */
	public static int RandomSelectRecurForEverNever(Random random){
		            int[] RecurArray = new int[] {Appt.RECUR_NUMBER_FOREVER,Appt.RECUR_NUMBER_NEVER};// The list of the of setting appointments to recur RECUR_NUMBER_FOREVER, or RECUR_NUMBER_NEVER

			        	int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
					        return RecurArray[n] ; // return appointments to recur forever or Never recur 
						        }	
	
	/**
	* Generate Random Tests that tests TimeTable Class.
	*/
	@Test
	public void TimeTabledeleteApptTest()  throws Throwable  {
		//Tests deleteAppt from TimeTable
		//Makes a TimeTable
		//Adds a number of random appts to a linked list	 
		//Gens some random appointments and attempts to see if any are removable
		//Removes the rest randomly
		TimeTable tt = new TimeTable();
		LinkedList<Appt> appts = new LinkedList<Appt>();
		Random random = new Random();
		int numAppts = 1000;
		System.out.println("Start deleteAppt testing...");
		PrintWriter outputStream = null; 
		outputStream = new PrintWriter("timetabletest.txt","UTF-8"); 
		for (int i = 0; i < numAppts; i++){
			//Add a bunch of valid appts to the list
			int startHour = ValuesGenerator.getRandomIntBetween(random,0,5);
			int startMinute = ValuesGenerator.getRandomIntBetween(random,0,5);
			int startDay = ValuesGenerator.getRandomIntBetween(random,1,3);
			int startYear = ValuesGenerator.getRandomIntBetween(random,0,2);
			int startMonth = ValuesGenerator.getRandomIntBetween(random,2,3);
			Appt appt = new Appt(startHour,startMinute,startDay,startMonth,startYear,"title","desc");
			assertTrue(appt.getValid());
			appts.add(appt);
		}
		//Now that the list is full, generate some random appts and attempt to remove them	
		for (int i = 0; i < numAppts; i++){
			int count = 0;
			int resCount = 0;
			int startHour = ValuesGenerator.getRandomIntBetween(random,-1,5);
			int startMinute = ValuesGenerator.getRandomIntBetween(random,-1,5);
			int startDay = ValuesGenerator.getRandomIntBetween(random,1,2);
			int startYear = ValuesGenerator.getRandomIntBetween(random,0,2);
			int startMonth = ValuesGenerator.getRandomIntBetween(random,2,3);
			Appt appt = new Appt(startHour,startMinute,startDay,startMonth,startYear,"title","desc");
			//See how many appt of type is in list
			//Even if it isn't, attempt to remove
			//Check how many are in the list
			//If there are more or less than supposed to be issue error
			for (int j = 0; j < appts.size(); j++){
				if (appt.compareTo(appts.get(j)) == 0){
					count++;
				}
			}
			LinkedList<Appt> apptsD = tt.deleteAppt(appts,appt);
			
			for (int j = 0; j < appts.size(); j++){
				if (appt.compareTo(appts.get(j)) == 0){
					resCount++;
				}
			}
			//Not null, was valid, and found a matching appt
			if (apptsD != null && count == resCount) {
				outputStream.println("DELETE ERROR !NULL");
				outputStream.println(appt.toString());
			}
			if (apptsD == null && count != 0) {
				outputStream.println("DELETE ERROR NULL");
				outputStream.println(appt.toString());
			}	
		}
		outputStream.close();



	}

	@Test
	public void TimeTablegetApptRangeTest()  throws Throwable  {
		//Tests getApptRange from TimeTable
		//Makes a TimeTable
		//Adds a number of random appts to a linked list, some invalid
		//Makes sure to add recurrence	 
		//Gens some random gregorian calendars and gets some caldays
		TimeTable tt = new TimeTable();
		LinkedList<Appt> appts = new LinkedList<Appt>();
		Random random = new Random();
		int numAppts = 100;
		int numTests = 10;
		System.out.println("Start getApptRange testing...");
		PrintWriter outputStream = null; 
		outputStream = new PrintWriter("apptrange.txt","UTF-8"); 
		for (int i = 0; i < numAppts; i++){
			//Add a bunch of valid appts to the list
			int startHour = ValuesGenerator.getRandomIntBetween(random,-1,30);
			int startMinute = ValuesGenerator.getRandomIntBetween(random,-1,70);
			int startDay = ValuesGenerator.getRandomIntBetween(random,-1,30);
			int startYear = ValuesGenerator.getRandomIntBetween(random,0,100);
			int startMonth = ValuesGenerator.getRandomIntBetween(random,2,11);
			Appt appt = new Appt(startHour,startMinute,startDay,startMonth,startYear,"title","desc");
			int sizeArray=ValuesGenerator.getRandomIntBetween(random, 0, 8);
			int[] recurDays=ValuesGenerator.generateRandomArray(random, sizeArray);
			int recur=ApptRandomTest.RandomSelectRecur(random);
			int recurIncrement = ValuesGenerator.RandInt(random);
			int recurNumber=ApptRandomTest.RandomSelectRecurForEverNever(random);
			appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
			appts.add(appt);
		}
		//Now that the list is full, generate some random gregorian calendars and
		//get CalDays back
		for (int i = 0; i < numTests; i++){
			int startDay = ValuesGenerator.getRandomIntBetween(random,1,29);
			int startYear = ValuesGenerator.getRandomIntBetween(random,0,1);
			int startMonth = ValuesGenerator.getRandomIntBetween(random,0,11);
			int endDay = ValuesGenerator.getRandomIntBetween(random,1,29);
			int endYear = ValuesGenerator.getRandomIntBetween(random,2,125);
			int endMonth = ValuesGenerator.getRandomIntBetween(random,1,11);
			GregorianCalendar start = new GregorianCalendar(startYear,startMonth,startDay);
			GregorianCalendar end = new GregorianCalendar(endYear,endMonth,endDay);
			LinkedList<CalDay> range = tt.getApptRange(appts,start,end);
		
		}
		outputStream.close();



	}

	
}
