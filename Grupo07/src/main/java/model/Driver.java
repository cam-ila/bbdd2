package model;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

public class Driver extends User {
	private Collection<Trip> trips;
	private Date licenseDate;
	
	public Driver() {
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
	public void createAndAddTrip(String from, String to, Date date, Integer maxPassenger, String state, double price){
		Trip trip = new Trip(this,from,to,date,maxPassenger, state, price);
		this.trips.add(trip);
	}
	public void endTrip(Trip trip){
		trip.setState("false");
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
