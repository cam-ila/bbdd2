package bd2.Muber.model;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import bd2.Muber.model.Trip;
import bd2.Muber.model.User;

public class Driver extends User {
	private Collection<Trip> trips;
	private Date licenseDate;
	
	public Driver() {
	}
	
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
	public void setLicenseDate(Date date) {
		this.licenseDate = date;
	}
	public void createAndAddTrip(String origin, String destination, Date date, Integer maxPassenger, double price){
		Trip trip = new Trip(this, date, maxPassenger, price, origin, destination);
		this.trips.add(trip);
	}
	public void endTrip(Trip trip){
		trip.close();
//		false significa que el viaje esta cerrado
	}
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
}
