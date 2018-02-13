package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  CalDay class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.*;


import org.junit.Test;

import static org.junit.Assert.*;

public class CalDayTest {

	/* Tests default constructor */
	 @Test
	  public void test01()  throws Throwable  {
		Calendar now = Calendar.getInstance();
		int thisMonth = now.get(Calendar.MONTH)+1;
		int thisYear = now.get(Calendar.YEAR);
		int thisDay = now.get(Calendar.DAY_OF_MONTH);
		
		GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
		CalDay day = new CalDay();
		assertFalse(day.isValid());

		CalDay day2 = new CalDay(today);
		assertTrue(day2.isValid());
	 }
	
	 /* Tests get functions */
	 @Test
	  public void test02()  throws Throwable  {
		Calendar now = Calendar.getInstance();
		int thisMonth = now.get(Calendar.MONTH)+1;
		int thisYear = now.get(Calendar.YEAR);
		int thisDay = now.get(Calendar.DAY_OF_MONTH);
		
		GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
		CalDay day = new CalDay(today);
	
       		assertEquals(day.getDay(),thisDay);
		assertEquals(day.getMonth(),thisMonth);
		assertEquals(day.getYear(),thisYear);		
	 }
	 /* Tests addAppt, getAppts and display function */
	 @Test
	  public void test03()  throws Throwable  {
		Calendar now = Calendar.getInstance();
		int thisMonth = now.get(Calendar.MONTH)+1;
		int thisYear = now.get(Calendar.YEAR);
		int thisDay = now.get(Calendar.DAY_OF_MONTH);
		 int startHour=1;
		 int startMinute=1;
		 int startDay=1;
		 int startMonth=1;
		 int startYear=1;
		 String title="Title";
		 String description="Description";
		
		GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
		CalDay day = new CalDay(today);
		 //Construct a new Appointment object with the initial data	 
		 Appt appt = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);
		
		 Appt badAppt = new Appt(startHour - 2,
		          startMinute,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);

		 startHour = 0;

		 Appt appt2 = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);
		
		day.addAppt(badAppt);

		assertEquals(0,day.getSizeAppts());

		CalDay badDay = new CalDay();

		System.out.print(badDay.toString());		

		 day.addAppt(appt);
	 	
		// assertEquals(day.getSizeAppts(),1);

		day.addAppt(appt2);

		//assertEquals(day.getSizeAppts(),2);
		LinkedList<Appt> apptList = new LinkedList<Appt>();

		apptList = day.getAppts();

		System.out.print(day.toString());		
		
		assertEquals(0,apptList.get(1).compareTo(appt));
		assertEquals(0,apptList.get(0).compareTo(appt2));

	 }
	 /* Tests iterator */
	 @Test
	  public void test04()  throws Throwable  {
		Calendar now = Calendar.getInstance();
		int thisMonth = now.get(Calendar.MONTH)+1;
		int thisYear = now.get(Calendar.YEAR);
		int thisDay = now.get(Calendar.DAY_OF_MONTH);
		 int startHour=1;
		 int startMinute=1;
		 int startDay=1;
		 int startMonth=1;
		 int startYear=1;
		 String title="Title";
		 String description="Description";
		
		GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
		CalDay day = new CalDay(today);
		 //Construct a new Appointment object with the initial data	 
		 Appt appt = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);
		
		 Appt badAppt = new Appt(startHour - 2,
		          startMinute,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);

		 startHour = 0;

		 Appt appt2 = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);
		
		CalDay badDay = new CalDay();

		 day.addAppt(appt);
	 	
		day.addAppt(appt2);

		Iterator apptDayIterator = badDay.iterator();

		assertEquals(null,apptDayIterator);

		apptDayIterator = day.iterator();

		assertNotEquals(null,apptDayIterator);
	 }
//add more unit tests as you needed	
}
