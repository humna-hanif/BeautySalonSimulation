/*
 * Emily Balboni and Humna Hanif 
 * April 23rd 2021
 * CSC 111 Hair Salon Simulation
 * 
 * The App class runs a beauty salon simulator. It reads in a file with each customer's
 * name, arrival time and service time. With this information, it prints each time 
 * interval and updates who is in the hairdresser chair and who is in the waiting chars.
 * The user is notified if the chairs are full or empty. User also has option at begining of 
 * program to decide how many waiting chairs they would like. 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
	/*
	 * Instance variables 
	 */
	private int time, arrivalTime, serviceTime, chairTime;
	private String name;
	private Arrivals arrivalList;
	private Customer customer;
	private WaitingChairs waitingChair;
	/*
	 * Boolean to check to see if the hairdresser chair is filled. If false, no one is in the chair. If true 
	 * someone is in the chair. 
	 */
	private Boolean chairOccupancy;
/*
 * Constructor initializes current time, chair time, arrival list, and current customer. 
 * Also reads in file that user provides and prompts user to enter amount of waiting chairs along
 * with a file name that contains the customer list. 
 * Prints out arrival list. 
 * Authors note change to a try/catch statement
 */
	public App() throws FileNotFoundException {
		time = 0;
		chairTime = 0;
		arrivalList = new Arrivals();
		//empty
		chairOccupancy = false;

		Scanner input = new Scanner(System.in);

		System.out.println("Hello and welcome to the Beauty Salon!");

		System.out.println("How many chairs would you like in the beauty salon waiting area?");
		int numChairs = input.nextInt();
		waitingChair = new WaitingChairs(numChairs);
		
		//file not found, instead of throwing should we create a method/ do a try catch block?
		System.out.println("Please enter file name of customer list: ");
		String fileName = input.next();
		
		System.out.println("Opening and reading " + fileName);
		System.out.println("Finished reading file.");
		System.out.println("Starting a beauty salon simulation with 1 hairdresser and " + numChairs + " waiting chairs: ");
		System.out.println();
		printTime();
		System.out.println();
		
		/*
		 * Displays the current people in the waiting chair or empty if no one is there.
		 */
		waitingChair.display();
		
		/*
		 * Displays the current person in the hairdresser chair or empty if no one is being served. 
		 */
		displayHairdresserChair(customer);
		System.out.println("Arrival List");
		readCustomer(fileName);

		/*
		 * Runs the beauty salon simulation. Calls the update method keeping the user informed of 
		 * who is being served at what time and who is in the waiting chairs/ arrival list
		 * until the last person has been fully served. 
		 */
		while(!arrivalList.isEmpty() || !waitingChair.isEmpty() || customer != null) {
			update();
		}
	}
	/*
	 * The update method displays the current time, who is in the salon chair, the waiting chairs and who is left on the 
	 * arrival list by calling the appropriate methods. It is formatted to the desired output style by including 
	 * a dashed line to separate the different time intervals. It also increments the time at the end of the method.
	 */
	public void update() {
		printTime();
		System.out.println();
		updateHairdresserChair();
		updateWaitingChair();
		displayHairdresserChair(customer);
		waitingChair.display();
		arrivalList.displayArrivalList();
		System.out.println();
		System.out.println("------------------------------");
		time++;
	}
	
	/*
	 * The update waiting chair method checks to see if anyone is in the hairdresser chair and makes sure the first 
	 * person in the arrival list object is not null. It then checks to see if the first person 
	 * on the arrival list arrival time is less than or equal to the current time. It also checks to 
	 * see if the waiting chairs have room to add people. If this is the case, the person at the top 
	 * of the arrivals is added to the waiting chair and taken off of the arrival list. 
	 */
	public void updateWaitingChair() {
		if(!chairOccupancy && arrivalList.getHead() != null) {
			if(arrivalList.getHead().getArrivalTime() <= time && !waitingChair.isFull()) {
				waitingChair.addCustomer(arrivalList.getHead());
				arrivalList.removeCustomer();
				}
		}
		
	}
	
	/*
	 * The readCustomer method takes in a String parameter of the file name the user provides. 
	 * Calls the arrival class to create a reference based queue based on the information read. 
	 * Displays the displays the arrival list at time interval 0. 
	 */
	public void readCustomer(String fileName) throws FileNotFoundException {
		Scanner readFile = new Scanner(new File(fileName));
		while (readFile.hasNextLine()) {

			arrivalTime = readFile.nextInt();

			serviceTime = readFile.nextInt();

			name = readFile.next();

			arrivalList.addCustomer(arrivalTime, serviceTime, name);
		}
		arrivalList.displayArrivalList();
		System.out.println();
		System.out.println("------------------------------");

	}
	
	/*
	 * Prints a time statement to notify the user at which time interval they are at. 
	 */
	public void printTime() {
		System.out.println("Time = " + this.time);
	}
	
	/*
	 * The method updateChair keeps track of which customer is currently in the hairdresser's chair. The chair 
	 * is updated by taking customers either from the waiting chairs or directly from the arrival list depending 
	 * on if the waiting chairs are empty. 
	 * 
	 */
	public void updateHairdresserChair() {
		/*
		 * Checks to see if customer being served is done or current customer is not
		 * filled, take customer from arrival list if the waiting chairs are empty, else
		 * you are taking a customer from the waiting chairs
		 */
		if (customer == null || customer.getServiceTime() + chairTime == time) {
			if (waitingChair.isEmpty()) {
				if (!arrivalList.isEmpty()) {
					if (time == arrivalList.getHead().getArrivalTime()) {
						customer = arrivalList.removeCustomer();
						chairOccupancy = false;
						chairTime = time;
					}
				}else{
					customer = null;
				}
			}else{
				customer = waitingChair.removeCustomer();
				chairOccupancy = false;
				chairTime = time;
			}
		}
	}
	
	/*
	 * The displayHairdresserChair method displays the person who is currently in the 
	 * hair dresser chair. It takes a Customer object parameter but if the customer object is 
	 * null it out prints that the hairdresser's chair is empty. 
	 */
	public void displayHairdresserChair(Customer c) {
		if(c != null) {
			System.out.println("Hairdresser's chair: ");
			System.out.println("\t" + c.display());
		}else {
			System.out.println("Hairdresser's chair is empty");
		}
	}

	
	/*
	 * Starts the program by creating an anonymous instance of the app. 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		/*
		 * Initializes the app. 
		 */
		new App();
	}

}
