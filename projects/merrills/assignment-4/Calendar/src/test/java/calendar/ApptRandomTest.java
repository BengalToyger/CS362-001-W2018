package calendar;

import java.util.Calendar;
import java.util.Random;
import org.junit.Test;
import java.io.*;
import java.time.*;

import static org.junit.Assert.*;



/**
 * Random Test Generator  for Appt class.
 */

public class ApptRandomTest {
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




	 @Test
	  public void setRecurrenceRandTest()  throws Throwable  {
		Random random = new Random();
		  
		System.out.println("Start setRecurrence testing...");
		PrintWriter outputStream = null; 
		outputStream = new PrintWriter("recurrencetest.txt","UTF-8"); 
		Appt appt = new Appt(1/*startHour*/,
			  1/*startMinute*/ ,
			  1/*startDay*/ ,
			  3/*startMonth*/ ,
			  1/*startYear*/ ,
			  "title"/*title*/,
			 "desc"/*description*/);
				
		for (int i = 0; i < 10000; i++) {
			   int sizeArray=ValuesGenerator.getRandomIntBetween(random, 0, 8);
			   int[] recurDays=ValuesGenerator.generateRandomArray(random, sizeArray);
			   int recur=ApptRandomTest.RandomSelectRecur(random);
			   int recurIncrement = ValuesGenerator.RandInt(random);
			   int recurNumber=ApptRandomTest.RandomSelectRecurForEverNever(random);
			   appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
			   //Test against getValues, put results to file
			   int[] retRecurDays = appt.getRecurDays();
			   outputStream.println("Expected Values:");
			   for (int j = 0; j < recurDays.length; j++) {
				outputStream.print(recurDays[j] + " ");
				outputStream.println();
			   }
			   outputStream.println("Actual Values:");
			   for (int j = 0; j < retRecurDays.length; j++) {
				outputStream.print(retRecurDays[j] + " ");
				outputStream.println();
			   }
			   assertEquals(retRecurDays.length, recurDays.length);
			   System.out.println(recurDays.length);
			   for (int j = 0; (j < retRecurDays.length) && (j < recurDays.length); j++) {
				if (recurDays[j] != retRecurDays[j]) {
					outputStream.print("MATCH FAIL ");
					System.out.println("MATCH FAIL ");
				}
				else {
					
					System.out.println("MATCH SUCCESS ");
				}
			   }
			   outputStream.println();
					   

		}		   
						   
				
	
	 	 outputStream.close();
		 System.out.println("Done setRecurrence testing...");
	 }
	

	
}
