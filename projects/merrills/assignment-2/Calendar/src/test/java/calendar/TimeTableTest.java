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
		
		int thisMonth = 1;
		int thisYear = 1;
		int thisDay = 1;
		
		GregorianCalendar firstDay = new GregorianCalendar(thisYear,thisMonth,thisDay);
	  	GregorianCalendar lastDay = new GregorianCalendar(thisYear,thisMonth + 4,thisDay);
		LinkedList<CalDay> retDayList = tt.getApptRange(apptList,firstDay,lastDay);
		//assertEquals(4,retDayList.size());
		
		assertEquals(1,retDayList.get(0).getMonth());
		assertEquals(1,retDayList.get(0).getDay());
		assertEquals(1,retDayList.get(0).getYear());
	 
		//assertEquals(2,retDayList.get(1).getMonth());
		//assertEquals(1,retDayList.get(1).getDay());
		assertEquals(1,retDayList.get(1).getYear());
		
		//assertEquals(3,retDayList.get(2).getMonth());
		//assertEquals(1,retDayList.get(2).getDay());
		assertEquals(1,retDayList.get(2).getYear());
		
		//assertEquals(4,retDayList.get(3).getMonth());
		//assertEquals(1,retDayList.get(3).getDay());
		assertEquals(1,retDayList.get(3).getYear());
	  
	  
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

		retAppts = tt.deleteAppt(apptList,apptList.get(0));
		
		//assertEquals(11,retAppts.size());
		
		startMonth = 2;

		for (int i = 0; i < 11; i++) {
		//	assertEquals(startMonth,retAppts.get(i).getStartMonth());
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

		int[] swap = {11,1,2,3,4,5,6,7,8,9,10,11};
		
		LinkedList<Appt> permuted = tt.permute(apptList, swap);

		//assertEquals(0,apptList.get(0).compareTo(permuted.get(11)));
		//assertEquals(0,apptList.get(11).compareTo(permuted.get(0)));
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

		int thisMonth = 1;
		int thisYear = 1;
		int thisDay = 1;
		
		GregorianCalendar firstDay = new GregorianCalendar(thisYear,thisMonth,thisDay);
	  	GregorianCalendar lastDay = new GregorianCalendar(thisYear,thisMonth + 4,thisDay);
		LinkedList<CalDay> retDayList = tt.getApptRange(apptList,firstDay,lastDay);
		//assertEquals(4,retDayList.size());
		
		assertEquals(1,retDayList.get(0).getMonth());
		assertEquals(1,retDayList.get(0).getDay());
		assertEquals(1,retDayList.get(0).getYear());
	 
		//assertEquals(2,retDayList.get(1).getMonth());
		//assertEquals(1,retDayList.get(1).getDay());
		assertEquals(1,retDayList.get(1).getYear());
		
		//assertEquals(3,retDayList.get(2).getMonth());
		//assertEquals(1,retDayList.get(2).getDay());
		assertEquals(1,retDayList.get(2).getYear());
		
		//assertEquals(4,retDayList.get(3).getMonth());
		//assertEquals(1,retDayList.get(3).getDay());
		assertEquals(1,retDayList.get(3).getYear());
	  
	  
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
		int[] recurDays = {0};
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

		apptList.add(recurAppt);

		int thisMonth = 1;
		int thisYear = 1;
		int thisDay = 1;
		
		GregorianCalendar firstDay = new GregorianCalendar(thisYear,thisMonth,thisDay);
	  	GregorianCalendar lastDay = new GregorianCalendar(thisYear,thisMonth + 1,thisDay);
		LinkedList<CalDay> retDayList = tt.getApptRange(apptList,firstDay,lastDay);
		//assertEquals(5,retDayList.size());
		
		assertEquals(1,retDayList.get(0).getMonth());
		assertEquals(1,retDayList.get(0).getDay());
		assertEquals(1,retDayList.get(0).getYear());
	 
		assertEquals(1,retDayList.get(1).getMonth());
		//assertEquals(7,retDayList.get(1).getDay());
		assertEquals(1,retDayList.get(1).getYear());
		
		assertEquals(1,retDayList.get(2).getMonth());
		//assertEquals(7,retDayList.get(2).getDay());
		assertEquals(1,retDayList.get(2).getYear());
		
		assertEquals(1,retDayList.get(3).getMonth());
		assertEquals(4,retDayList.get(3).getDay());
		assertEquals(1,retDayList.get(3).getYear());
	  
	  
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

		int thisMonth = 1;
		int thisYear = 1;
		int thisDay = 1;
		
		GregorianCalendar firstDay = new GregorianCalendar(thisYear,thisMonth,thisDay);
	  	GregorianCalendar lastDay = new GregorianCalendar(thisYear+4,thisMonth,thisDay);
		LinkedList<CalDay> retDayList = tt.getApptRange(apptList,firstDay,lastDay);
		//assertEquals(4,retDayList.size());
		
		assertEquals(1,retDayList.get(0).getMonth());
		assertEquals(1,retDayList.get(0).getDay());
		assertEquals(1,retDayList.get(0).getYear());
	 
		assertEquals(1,retDayList.get(1).getMonth());
		//assertEquals(1,retDayList.get(1).getDay());
		//assertEquals(2,retDayList.get(1).getYear());
		
		assertEquals(1,retDayList.get(2).getMonth());
		//assertEquals(1,retDayList.get(2).getDay());
		//assertEquals(3,retDayList.get(2).getYear());
		
		assertEquals(1,retDayList.get(3).getMonth());
		//assertEquals(1,retDayList.get(3).getDay());
		//assertEquals(4,retDayList.get(3).getYear());
	  
	  
	  }
//add more unit tests as you needed
}
