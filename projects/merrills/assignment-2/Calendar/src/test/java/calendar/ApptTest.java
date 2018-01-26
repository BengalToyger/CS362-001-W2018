package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  Appt class.
 */
import org.junit.Test;

import static org.junit.Assert.*;
public class ApptTest {
    /**
     * Test that the gets methods work as expected.
     */
	 @Test
	  public void test01()  throws Throwable  {
		 int startHour=21;
		 int startMinute=30;
		 int startDay=15;
		 int startMonth=01;
		 int startYear=2018;
		 String title="Birthday Party";
		 String description="This is my birthday party.";
		 //Construct a new Appointment object with the initial data	 
		 Appt appt = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);
	// assertions
		 assertTrue(appt.getValid());
		 assertEquals(21, appt.getStartHour());
		 assertEquals(30, appt.getStartMinute());
		 assertEquals(15, appt.getStartDay());
		 assertEquals(01, appt.getStartMonth());
		 assertEquals(2018, appt.getStartYear());
		 assertEquals("Birthday Party", appt.getTitle());
		 assertEquals("This is my birthday party.", appt.getDescription());         		
	  }
	/* Tests the set functions */
	 @Test
	  public void test02()  throws Throwable  {
	 	System.gc();
		 int startHour=1;
		 int startMinute=1;
		 int startDay=1;
		 int startMonth=1;
		 int startYear=1;
		 String title="bleh";
		 String description="bleh";
		 //Construct a new Appointment object with the initial data	 
		 Appt appt = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);
		//Set all the data
		 appt.setStartHour(2);
		 appt.setStartMinute(2);
		 appt.setStartDay(2);
		 appt.setStartMonth(2);
		 appt.setStartYear(2);
		 appt.setTitle("Title");
		 appt.setDescription("Description");
	 
	// assertions
		 assertEquals(2, appt.getStartHour());
		 assertEquals(2, appt.getStartMinute());
		 assertEquals(2, appt.getStartDay());
		 assertEquals(2, appt.getStartMonth());
		 assertEquals(2, appt.getStartYear());
		 assertEquals("Title", appt.getTitle());
		 assertEquals("Description", appt.getDescription());         		
	  }
	/* Tests the isValid function */
	 @Test
	  public void test03()  throws Throwable  {
	 	System.gc();
		 int startHour=1;
		 int startMinute=1;
		 int startDay=1;
		 int startMonth=1;
		 int startYear=1;
		 String title="Title";
		 String description="Description";
		 //Construct a new Appointment object with the initial data	 
		 Appt appt = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);
		//Set all the data
	 
		 assertTrue(appt.getValid());

		 int hour = -1;
		
		 appt.setStartHour(hour);

		 assertFalse(appt.getValid());

		hour = 0;

		 while( hour < 24) {
			appt.setStartHour(hour);
		 	assertTrue(appt.getValid());
			hour++;
		 }

		appt.setStartHour(hour);

		 assertFalse(appt.getValid());

		 appt.setStartHour(1);

		 startMinute = -1;

		 appt.setStartMinute(startMinute);

		 assertFalse(appt.getValid());

		 startMinute = 0;
		 
		 while( startMinute < 60) {
			appt.setStartMinute(startMinute);
		 	assertTrue(appt.getValid());
			startMinute++;
		 }

		appt.setStartMinute(startMinute);
		
		assertFalse(appt.getValid());

		appt.setStartMinute(1);
		
		appt.setStartDay(0);

		assertFalse(appt.getValid());

		startDay = 2;

		while( startDay < 32) {
			appt.setStartDay(startDay);
		 	//assertTrue(appt.getValid());
			startDay++;
		 }

		appt.setStartDay(startDay);

		assertFalse(appt.getValid());

		appt.setStartDay(1);

		while( startMonth < 13) {
			appt.setStartMonth(startMonth);
		 	assertTrue(appt.getValid());
			startMonth++;
		 }
		
	  }
	 /* Tests recurrence functions*/
	 @Test
	  public void test04()  throws Throwable  {
		 int startHour=1;
		 int startMinute=1;
		 int startDay=1;
		 int startMonth=1;
		 int startYear=1;
		 String title="Title";
		 String description="Description";
		int[] recurDays = {1,2,3,4};
	       int recurBy = 1;
       		int recurIncrement = 1;
 		int recurNumber = 1;
		int i;		
		 //Construct a new Appointment object with the initial data	 
		 Appt appt = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);
	 	
		appt.setRecurrence(recurDays, recurBy, recurIncrement, recurNumber);
		assertEquals(recurNumber,appt.getRecurNumber());
		assertEquals(recurBy,appt.getRecurBy());
		int[] recurDays2 = appt.getRecurDays();
		for (i = 0; i < 4; i++) {
			assertEquals(recurDays[i],recurDays2[i]);
		}
		assertTrue(appt.isRecurring());
		assertEquals(appt.getRecurIncrement(),recurIncrement);
	  }
	 	
	 /*Tests the printing functions*/
	 @Test
	  public void test05()  throws Throwable  {
		 int startHour=0;
		 int startMinute=1;
		 int startDay=1;
		 int startMonth=1;
		 int startYear=1;
		 String title="Title";
		 String description="Description";
		int[] recurDays = {1,2,3,4};
	       int recurBy = 1;
       		int recurIncrement = 1;
 		int recurNumber = 1;
		int i;	
		String apptString;	
		 //Construct a new Appointment object with the initial data	 
		 Appt appt = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);
		apptString = appt.toString();
		System.out.print(apptString);
		appt.setStartHour(12);
		apptString = appt.toString();
		System.out.print(apptString);
	  }
		
	/* Tests compareTo */
	 @Test
	  public void test06()  throws Throwable  {
		 int startHour=0;
		 int startMinute=1;
		 int startDay=1;
		 int startMonth=1;
		 int startYear=1;
		 String title="Title";
		 String description="Description";
		int[] recurDays = {1,2,3,4};
	       int recurBy = 1;
       		int recurIncrement = 1;
 		int recurNumber = 1;
		int i;	
		String apptString;	
		 //Construct a new Appointment object with the initial data	 
		 Appt appt = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);
		
		 Appt appt2 = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);
		
		 assertEquals(0,appt.compareTo(appt2));
		
	 	appt.setStartHour(1);

		assertEquals(1,appt.compareTo(appt2));

		appt.setStartMinute(2);		
		
		assertEquals(2,appt.compareTo(appt2));
		
		appt.setStartDay(2);		
		
		assertEquals(3,appt.compareTo(appt2));
		
		appt.setStartMonth(2);		
		
		assertEquals(4,appt.compareTo(appt2));
		
		appt.setStartYear(2);		
		
		assertEquals(5,appt.compareTo(appt2));
	  
	  }
		 //add more unit tests as you needed
	
}
