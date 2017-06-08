package bd2.Muber.model;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import bd2.Muber.model.Trip;
import bd2.Muber.model.User;
/**
 * Driver is a subclass from User that 
 * implements its own methods.
 */

public class Driver extends User {
	/**
    * A collection of trips owned
    */
	private Collection<Trip> trips;
	/**
    * Date variable with the driver license's date
    */
	private Date licenseDate;
	
	public Driver() {
	}
	/**
    * Driver object's constructor
    */
	public Driver(String fullName, String password, Date licenseDate){
		super(fullName, password);
		this.licenseDate = licenseDate;
	}

	public Collection<Trip> getTrips() {
		return trips;
	}

	public void setTrips(Collection<Trip> trips) {
		this.trips = trips;
	}

	public Date getLicenseDate() {
		return licenseDate;
	}
	public String getFullName() {
		return super.getFullName();
	}
	public void setLicenseDate(Date date) {
		this.licenseDate = date;
	}
	/**
    * Creates a new trip and adds it to its collection of trips
    */
	public void createAndAddTrip(String origin, String destination, Date date, Integer maxPassenger, double price){
		Trip trip = new Trip(this, date, maxPassenger, price, origin, destination);
		this.trips.add(trip);
	}
	/**
	 * Closes a trip.
    * @return true  when the trip closed succesfully
    * @return false when the trip was already closed
    */
	public boolean endTrip(Trip trip){
		trip.close();
	}
	/**
    * Calculates the average driver's score.
    * Iterates through all of the driver's trips and adds the average score
    * of each trip to the parameter average.
    * 
    * @param average  the average score of the trips iterated
    * @return		  the full adding of the trip's average scores.
    */
	public float averageScore(){
		float average = 0;
		if(trips.size() > 0){
			for (Iterator<Trip> iterator = trips.iterator(); iterator.hasNext();) {
				average = average + iterator.next().getAverageScore();
	        }
			average = average / trips.size(); 
		}
		return average;
	}
	/**
    * Checks of an open trip. If there is returns true, else returns false.
    * 
    * @return true if there is an open trip
    * @return false if there is not an open trip
    */
	public boolean haveOpenTrip(){
		for (Trip t: this.trips){
			if (t.getState()){
				return true;
			}			
		}
		return false;
	}
	
	
}
