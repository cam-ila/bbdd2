package model;

import java.util.Collection;
import java.util.Date;

public class Muber {
	
	private Collection<Driver> drivers;
	private Collection<Passenger> passengers;
	private Date licenceDate;

	public Date getLicenceDate() {
		return licenceDate;
	}

	public void setLicenceDate(Date licenceDate) {
		this.licenceDate = licenceDate;
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
	
}

