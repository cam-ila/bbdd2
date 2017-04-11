package model;

import java.util.Collection;
import java.util.Date;

public class Trip {

	private Collection<Passenger> passengers;
	private Driver driver;
	private String from;
	private String to;
	private Date date;
	private Integer maxPassenger;
	private Boolean state;
	
	
	public Collection<Passenger> getPassengers() {
		return passengers;
	}
	public void setPassengers(Collection<Passenger> passengers) {
		this.passengers = passengers;
	}
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getMaxPassenger() {
		return maxPassenger;
	}
	public void setMaxPassenger(Integer maxPassenger) {
		this.maxPassenger = maxPassenger;
	}
	public Boolean getState() {
		return state;
	}
	public void setState(Boolean state) {
		this.state = state;
	}
	
	
	
}

