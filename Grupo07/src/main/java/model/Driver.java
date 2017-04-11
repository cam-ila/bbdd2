package model;

import java.util.Collection;

public class Driver extends User {
	
	private Collection<Trip> trips;

	public Collection<Trip> getTrips() {
		return trips;
	}

	public void setTrips(Collection<Trip> trips) {
		this.trips = trips;
	}
	
}
