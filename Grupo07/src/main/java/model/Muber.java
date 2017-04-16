package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Muber {
	
	private Collection<Driver> drivers;
	private Collection<Passenger> passengers;
	
	public Muber(){
		
	}

	public Collection<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(Collection<Passenger> passengers) {
		this.passengers = passengers;
	}

	public Collection<Driver> getDrivers() {
		return drivers;
	}

	public void setDrivers(Collection<Driver> drivers) {
		this.drivers = drivers;
	}
	
	public Collection<Trip> getOpenTrips(){
		Collection<Trip> openTrips = new ArrayList<Trip>();
		if(drivers.size() > 0){
			for (Iterator<Driver> iterator = drivers.iterator(); iterator.hasNext();) {
				Collection<Trip> trips = iterator.next().getTrips();
				for(Iterator<Trip> iterator2 = trips.iterator(); iterator2.hasNext(); ){
					Trip trip = iterator2.next();
					if(trip.getState() == true){
						openTrips.add(trip);
					}
				}
	        }
		}
		return openTrips;
	}
	
}

