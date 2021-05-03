/*
 * Authors Humna Hanif, Matt Merrit, Ty Hutchison 
 * Modified by Emily Balboni and Humna Hanif
 * CSC 111 Beauty Salon Simulation
 * April 23rd 2021
 * 
 * The Arrivals class creates a reference based queue to keep track of the customers 
 * that will be coming to the beauty salon. 
 */


public class Arrivals {
	/*
	 * Instance variables. 
	 */
	private Customer head, tail;
	private Customer c;
	
	/*
	 * Constructor 
	 */
	public Arrivals()
	{
		head = null;
		tail = null;
	}
	
	/*
	 * Checks to see if arrival list is empty returns a boolean. 
	 */
	public boolean isEmpty()
	{
		return head == null;
	}
	
	/*
	 * Returns the head of the arrival list. 
	 */
	public Customer getHead() {
		return head;
	}
	
	/*
	 * Adds customer to the arrival queue. Takes the customer's arrival and service time 
	 * along with their name. If the head is empty sets the customer to the head, else adds the 
	 * customer to the tail of the queue. 
	 */
	public void addCustomer(int arrivalTime, int serviceTime, String name)
	{
		if (isEmpty())
		{
			head = new Customer(arrivalTime, serviceTime, name);
			tail = head;
		}
		else 
		{
			Customer temp = new Customer(arrivalTime, serviceTime, name);
			tail.setNextCustomer(temp);
			tail = temp;
		}
	}
	
	/*
	 * Removes a customer by using temp variable to avoid losing data. Returns 
	 * the customer head. 
	 */
	public Customer removeCustomer()
	{
		Customer temp = head;
		head = head.getNextCustomer();
		return temp;
	}
	
	/*
	 * Returns the next customer in line. 
	 */
	public Customer nextCustomer()
	{
		return head;
	}
	
	/*
	 * Prints out the arrival list customers. Displays each customers 
	 * service and arrival time along with their name. 
	 */
	public void displayArrivalList()
	{
		Customer curr = head;
		if (curr != null) {
			System.out.println("Arrival List");
			while (curr != null) {
				System.out.println("\t" + curr.display());
				curr = curr.getNextCustomer();
			}
		}else{
			System.out.print("Arrival List is empty");
		}
	}
}
