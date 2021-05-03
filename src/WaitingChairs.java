/*
 * Starter code from author Zach Kohlberg
 * Modified from CSC111L by Emily Balboni and Humna Hanif
 * CSC111 Beauty Salon Simulation
 * April 23, 2021
 * Implementation of a circular array-based queue which represents the waiting chairs and relays information on whether or not a chair is full or empty.
 */

public class WaitingChairs {
	/*
	 * Instance Variables
	 */
	private Customer[] data;
    private int front;
    private int back;
    private int count;

    /*
	 * Constructor
	 */
    public WaitingChairs(int size) {
        data = new Customer[size];
        front = 0;
        back = -1;
    }


    /*
	 * Returns a boolean of whether or not the waiting chairs are empty
	 */
    public boolean isEmpty() {
        if (count == 0) {
            return true;
        } else {
            return false;
        }
    }

    /*
	 * Returns a boolean of whether or not the waiting chairs are full
	 */
    public boolean isFull() {
        if (count == data.length) {
            return true;
        } else {
            return false;
        }
    }
    
    /*
	 * Adds a customer to the back of the waiting chairs line if the chairs are not
	 * full
	 */
    public void addCustomer(Customer customer) {
        if (!isFull()) {
            back = (back + 1) % data.length;
            data[back] = customer;
            count++;
        }
    }

    /*
	 * Removes a customer from the waiting chairs from the front of the queue and
	 * returns the front customer
	 */
    public Customer removeCustomer() {
        if (isEmpty()) {
            return null;
        } else {
            count--;
            Customer customer = data[front];
            front = (front + 1) % data.length;
            return customer;
        }
    }
    
    /*
	 * Fetches the information of the first customer
	 */
    public Customer peek() {
        return data[front];
    }

    /*
	 * Prints out the list of customers in the waiting chairs or a statement stating
	 * the chairs are empty when applicable
	 */
    public void display() {
        if (isEmpty() == true) {
            System.out.println("Waiting chairs are empty");
        } else {
            System.out.println("Waiting chairs:");
            for (int i = 0; i < count; i++) {
                System.out.println("\t" + data[(front + i) % data.length].display());
            }
        }
    }
}
