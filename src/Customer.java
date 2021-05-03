/*
 * Authors Humna Hanif, Matt Merrit, Ty Hutchison 
 * Modified by Emily Balboni and Humna Hanif
 * CSC 111 Beauty Salon Simulation
 * April 23rd 2021 
 * The customer class stores each customers information including their arrival and service time 
 * along with their name. 
 */


public class Customer {
	/*
	 * Instance variables 
	 */
	private int arrivalTime, serviceTime;
	private String name;
	private Customer nextCustomer;
	
	/*
	 * Constructor takes two ints representing arrival and service time along with a string parameter 
	 * for the customers name and the next customer object. 
	 */
	public Customer(int arrivalTime, int serviceTime, String name, Customer nextCustomer)
	{
		this.arrivalTime = arrivalTime;
		this.serviceTime = serviceTime;
		this.name = name;
		this.nextCustomer = nextCustomer;
	}
	
	/*
	 * Constructors takes two ints representing arrival and service time along with a string parameter 
	 * to represent the customers name. 
	 */
	public Customer(int arrivalTime, int serviceTime, String name)
	{
		this.arrivalTime = arrivalTime;
		this.serviceTime = serviceTime;
		this.name = name;
	}
	
	/*
	 * The set arrivalTime takes an int parameter and sets the customer object's 
	 * arrival time to the parameter. 
	 */
	public void setArrivalTime(int arrivalTime)
	{
		this.arrivalTime = arrivalTime;
	}
	
	/*
	 * The set servicetime takes an int parameter and sets the customer object's 
	 * serviceTime to the parameter. 
	 */
	public void setServiceTime(int serviceTime)
	{
		this.serviceTime = serviceTime;
	}
	
	/*
	 * The getArrivalTime method returns the arrival time of the customer object. 
	 */
	public int getArrivalTime() 
	{
		return arrivalTime;
	}
	
	/*
	 * The getServiceTime returns the customer object's service time. 
	 */
	public int getServiceTime()
	{
		return serviceTime;
	}
	
	/*
	 * The setName method takes a string parameter and set's the customer object's name to 
	 * the parameter. 
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/*
	 * The getName method returns the customer object's name.
	 */
	public String getName()
	{
		return name;
	}
	
	/*
	 * The setNextCustomer method takes a customer object as a parameter and sets the customer object's next
	 * customer to the parameter passed. 
	 */
	public void setNextCustomer(Customer nextCustomer)
	{
		this.nextCustomer = nextCustomer;
	}

	/*
	 * The getNextCustomer method returns the customer object's next Customer. 
	 */
	public Customer getNextCustomer()
	{
		return nextCustomer;
	}
	
	/*
	 * The display method returns a string of the customer object's information including the 
	 * object's name, arrival time and service time. 
	 */
	public String display()
	{
		return name + ": arrival = " + arrivalTime + ": service = " + serviceTime;
	}
}
