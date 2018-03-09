package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  TimeTable class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTableTest {
	/* Tests getApptRange */
	 @Test
	  public void test01()  throws Throwable  {
		int startHour=0;
		int startMinute=1;
		int startDay=1;
		int startMonth=1;
		int startYear=1;
		String title="Title";
		String description="Description";
		//int[] recurDays = {1,2,3,4};
		//int recurBy = 1;
		//int recurIncrement = 1;
		//int recurNumber = 1;
		//Construct a new Appointment object with the initial data	 
	
		TimeTable tt = new TimeTable();

		LinkedList<Appt> apptList = new LinkedList<Appt>();

		for (int i = 0; i < 12; i++) {
			apptList.add(new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description));
			startMonth++;
			assertEquals(1,apptList.get(i).getStartDay());
		}

		assertEquals(1,apptList.get(1).getStartDay());
		
		int thisMonth = 0;
		int thisYear = 1;
		int thisDay = 1;
		
		GregorianCalendar firstDay = new GregorianCalendar(thisYear,thisMonth,thisDay);
	  	GregorianCalendar lastDay = new GregorianCalendar(thisYear,thisMonth + 3,thisDay);
		LinkedList<CalDay> retDayList = tt.getApptRange(apptList,firstDay,lastDay);
		assertEquals(90,retDayList.size()); 
				
		assertEquals(0,retDayList.get(0).getMonth());
		assertEquals(1,retDayList.get(0).getDay());
		assertEquals(1,retDayList.get(0).getYear());
	 
	  
	  
	  }
	 /*Tests deleteAppt */
	 @Test
	  public void test02()  throws Throwable  {
		int startHour=0;
		int startMinute=1;
		int startDay=1;
		int startMonth=1;
		int startYear=1;
		String title="Title";
		String description="Description";
		//int[] recurDays = {1,2,3,4};
		//int recurBy = 1;
		//int recurIncrement = 1;
		//int recurNumber = 1;
		//Construct a new Appointment object with the initial data	 
	
		TimeTable tt = new TimeTable();

		LinkedList<Appt> apptList = new LinkedList<Appt>();
		LinkedList<Appt> nullApptList = null;
		
		for (int i = 0; i < 12; i++) {
			apptList.add(new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description));
			startMonth++;
		}
		
		Appt nullAppt = null;
		Appt badAppt = new Appt(-1,-1,-1,1,-1,title,description);
		Appt nonExist = new Appt(0,1,1,1,1,title,description);

		LinkedList<Appt> retAppts = tt.deleteAppt(apptList,nullAppt);

		assertEquals(null,retAppts);

		retAppts = tt.deleteAppt(nullApptList,apptList.get(0));

		assertEquals(null,retAppts);

		retAppts = tt.deleteAppt(apptList,badAppt);

		assertEquals(null,retAppts);
	
		for (int i = 0; i < 12; i++) {
			System.out.println(apptList.get(i).toString());
		}

		retAppts = tt.deleteAppt(apptList,apptList.get(11));

		assertEquals(null,retAppts);
		
		retAppts = tt.deleteAppt(apptList,apptList.get(5));
		
		assertEquals(11,retAppts.size());

		startMonth = 1;

		for (int i = 0; i < 11; i++) {
			if (startMonth == 6) {
				startMonth++;
			}
			assertEquals(startMonth,retAppts.get(i).getStartMonth());
			
			startMonth++;
		}
		
		retAppts = tt.deleteAppt(apptList,nonExist);

		assertEquals(null,retAppts);	


	  }

	 /*Tests permute */
	 @Test
	  public void test03()  throws Throwable  {
		int startHour=0;
		int startMinute=1;
		int startDay=1;
		int startMonth=1;
		int startYear=1;
		String title="Title";
		String description="Description";
		//int[] recurDays = {1,2,3,4};
		//int recurBy = 1;
		//int recurIncrement = 1;
		//int recurNumber = 1;
		//Construct a new Appointment object with the initial data	 
	
		TimeTable tt = new TimeTable();

		LinkedList<Appt> apptList = new LinkedList<Appt>();
	
		

		for (int i = 0; i < 12; i++) {
			apptList.add(new Appt(startHour, startMinute, startDay, 
				startMonth, startYear, title, description));
			startMonth++;
		}

		int[] swap = {11,1,2,3,4,5,6,7,8,9,10,0};
		
		LinkedList<Appt> permuted = tt.permute(apptList, swap);

		assertEquals(0,apptList.get(11).compareTo(permuted.get(11)));
		assertEquals(0,apptList.get(0).compareTo(permuted.get(0)));
		assertEquals(0,apptList.get(1).compareTo(permuted.get(1)));
		assertEquals(0,apptList.get(2).compareTo(permuted.get(2)));
		assertEquals(0,apptList.get(3).compareTo(permuted.get(3)));
	}
	/* Tests getApptRange with recurrence by month */
	 @Test
	  public void test04()  throws Throwable  {
		int startHour=0;
		int startMinute=1;
		int startDay=1;
		int startMonth=1;
		int startYear=1;
		String title="Title";
		String description="Description";
		int[] recurDays = {0};
		int recurBy = 2;
		int recurIncrement = 1;
		int recurNumber = 12;
		//Construct a new Appointment object with the initial data	 
	
		TimeTable tt = new TimeTable();

		Appt recurAppt = new Appt(startHour, startMinute, startDay, 
				startMonth, startYear, title, description);

		recurAppt.setRecurrence(recurDays, recurBy, recurIncrement,
				recurNumber);

		LinkedList<Appt> apptList = new LinkedList<Appt>();

		apptList.add(recurAppt);

		int thisMonth = 0;
		int thisYear = 1;
		int thisDay = 1;
		
		GregorianCalendar firstDay = new GregorianCalendar(thisYear,thisMonth,thisDay);
	  	GregorianCalendar lastDay = new GregorianCalendar(thisYear,thisMonth + 3,thisDay);
		LinkedList<CalDay> retDayList = tt.getApptRange(apptList,firstDay,lastDay);
		assertEquals(90,retDayList.size());
		
		assertEquals(0,retDayList.get(0).getMonth());
		assertEquals(1,retDayList.get(0).getDay());
		assertEquals(1,retDayList.get(0).getYear());
	 
	 	assertEquals(1,retDayList.get(0).getAppts().size());
		assertEquals(startHour,retDayList.get(0).getAppts().get(0).getStartHour());
	  
	 	assertEquals(1,retDayList.get(31).getAppts().size());
		assertEquals(startHour,retDayList.get(31).getAppts().get(0).getStartHour());
		
	 	assertEquals(1,retDayList.get(59).getAppts().size());
		assertEquals(startHour,retDayList.get(59).getAppts().get(0).getStartHour());

	  }
	/* Tests getApptRange with recurrence by week on Sunday */
	 @Test
	  public void test05()  throws Throwable  {
		int startHour=0;
		int startMinute=1;
		int startDay=1;
		int startMonth=1;
		int startYear=1;
		String title="Title";
		String description="Description";
		int recurBy = 1;
		int recurIncrement = 1;
		int recurNumber = 12;
		//Construct a new Appointment object with the initial data	 
	
		TimeTable tt = new TimeTable();

		Appt recurAppt = new Appt(startHour, startMinute, startDay, 
				startMonth, startYear, title, description);

		recurAppt.setRecurrence(null, recurBy, recurIncrement,
				recurNumber);

		LinkedList<Appt> apptList = new LinkedList<Appt>();

		assertTrue(recurAppt.getValid());

		apptList.add(recurAppt);

		int thisMonth = 1;
		int thisYear = 1;
		int thisDay = 1;
		
		GregorianCalendar firstDay = new GregorianCalendar(thisYear,thisMonth,thisDay);
	  	GregorianCalendar lastDay = new GregorianCalendar(thisYear,thisMonth + 1,thisDay);
		LinkedList<CalDay> retDayList = tt.getApptRange(apptList,firstDay,lastDay);
		assertEquals(28,retDayList.size());
		
		assertEquals(1,retDayList.get(0).getMonth());
		assertEquals(1,retDayList.get(0).getDay());
		assertEquals(1,retDayList.get(0).getYear());
	
		for (int i = 0; i < retDayList.size(); i++){
			System.out.println(retDayList.get(i).toString());
		}
	        
		assertEquals(1,retDayList.get(0).getAppts().size()); 
		assertEquals(0,retDayList.get(1).getAppts().size());	
	 	 
	  }
	/* Tests getApptRange with recurrence by year */
	 @Test
	  public void test06()  throws Throwable  {
		int startHour=0;
		int startMinute=1;
		int startDay=1;
		int startMonth=1;
		int startYear=1;
		String title="Title";
		String description="Description";
		int[] recurDays = {0};
		int recurBy = 3;
		int recurIncrement = 1;
		int recurNumber = 12;
		//Construct a new Appointment object with the initial data	 
	
		TimeTable tt = new TimeTable();

		Appt recurAppt = new Appt(startHour, startMinute, startDay, 
				startMonth, startYear, title, description);

		recurAppt.setRecurrence(recurDays, recurBy, recurIncrement,
				recurNumber);

		LinkedList<Appt> apptList = new LinkedList<Appt>();

		apptList.add(recurAppt);

		int thisMonth = 0;
		int thisYear = 1;
		int thisDay = 1;
		
		GregorianCalendar firstDay = new GregorianCalendar(thisYear,thisMonth,thisDay);
	  	GregorianCalendar lastDay = new GregorianCalendar(thisYear+4,thisMonth,thisDay);
		LinkedList<CalDay> retDayList = tt.getApptRange(apptList,firstDay,lastDay);
		assertEquals(1461,retDayList.size());
	
		assertEquals(0,retDayList.get(0).getMonth());
		assertEquals(1,retDayList.get(0).getDay());
		assertEquals(1,retDayList.get(0).getYear());
	  
	 	assertEquals(1,retDayList.get(31).getAppts().size());
	 	assertEquals(1,retDayList.get(396).getAppts().size());
		assertEquals(startHour,retDayList.get(396).getAppts().get(0).getStartHour());
	  }
	/* Tests getApptRange with recurrence by week on Monday */
	 @Test
	  public void test07()  throws Throwable  {
		int startHour=0;
		int startMinute=1;
		int startDay=1;
		int startMonth=1;
		int startYear=1;
		String title="Title";
		String description="Description";
		int[] recurDays = {1};
		int recurBy = 1;
		int recurIncrement = 1;
		int recurNumber = 12;
		//Construct a new Appointment object with the initial data	 
	
		TimeTable tt = new TimeTable();

		Appt recurAppt = new Appt(startHour, startMinute, startDay, 
				startMonth, startYear, title, description);

		recurAppt.setRecurrence(recurDays, recurBy, recurIncrement,
				recurNumber);

		LinkedList<Appt> apptList = new LinkedList<Appt>();

		assertTrue(recurAppt.getValid());

		apptList.add(recurAppt);

		int thisMonth = 1;
		int thisYear = 1;
		int thisDay = 1;
		
		GregorianCalendar firstDay = new GregorianCalendar(thisYear,thisMonth,thisDay);
	  	GregorianCalendar lastDay = new GregorianCalendar(thisYear,thisMonth + 1,thisDay);
		LinkedList<CalDay> retDayList = tt.getApptRange(apptList,firstDay,lastDay);
		assertEquals(28,retDayList.size());
		
		assertEquals(1,retDayList.get(0).getMonth());
		assertEquals(1,retDayList.get(0).getDay());
		assertEquals(1,retDayList.get(0).getYear());
	
		for (int i = 0; i < retDayList.size(); i++){
			System.out.println(retDayList.get(i).toString());
		}
	        
		assertEquals(1,retDayList.get(0).getAppts().size()); 
		assertEquals(0,retDayList.get(1).getAppts().size());	
	 	assertEquals(1,retDayList.get(5).getAppts().size()); 
	 	assertEquals(1,retDayList.get(12).getAppts().size()); 
	  }
//add more unit tests as you needed
}
