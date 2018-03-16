package Theater;

import java.util.Calendar;

public class Driver {

	public static void main(String[] args){
		/*
		 * This driver program serves as a way of testing new class functionality before
		 * it is implemented into the UserInterface and Theater classes.
		 * Will not be present in final version.
		 */
		
		// create a new show on January 10th, 2018 that lasts for 5 days
		Calendar show1StartDate = Calendar.getInstance();
		show1StartDate.clear();
		show1StartDate.set(2018, 0, 10); // january 10th 2018
		// create a new show on January 20th 2018 that lasts for 2 days
		Calendar show2StartDate = Calendar.getInstance();
		show2StartDate.clear();
		show2StartDate.set(2018, 0, 20); // january 20th 2018
		
		Show show1 = new Show("Awesome Show", "Cli1", show1StartDate, 5);
		Show show2 = new Show("Best Show EVER!", "Cli2", show2StartDate, 2);
		System.out.println("show1 toString: " + show1.toString());
		System.out.println("show2 toString: " + show2.toString());
		
		ShowList showList = ShowList.instance();
		showList.insertShow(show1);
		showList.insertShow(show2);
		System.out.println("*Added shows to showList*");
		
		
		// testing show.isTheaterAvailable(date)
		Calendar date1 = Calendar.getInstance();
		date1.clear();
		date1.set(2018, 0, 12); // january 12th 2018 (calendar month is 0 indexed)
		
		Calendar date2 = Calendar.getInstance();
		date2.clear();
		date2.set(2018, 0, 20); // january 20th 2018 (calendar month is 0 indexed)
		
		System.out.println("Is theater available between " + Show.dateToString(date1) + " and "
							+ Show.dateToString(date2) + "?");
		System.out.println(Theater.instance().isTheaterAvailable(date1, date2));
	}
}