package model;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

public class Trip {

	private Collection<Passenger> passengers;
	private Collection<Score> scores;
	private Driver driver;
	private String from;
	private String to;
	private Date date;
	private Integer maxPassenger;
	private Boolean state;
	private Long idTrip;

	
	public Trip(){
		
	}
	
	public Trip(Driver d, String f, String t, Date da,Integer max){
		driver= d;
		from= f;
		to= t;
		date= da;
		maxPassenger= max;
		state= true;
	}
	
	public Collection<Passenger> getPassengers() {
		return passengers;
	}
	public void setPassengers(Collection<Passenger> passengers) {
		this.passengers = passengers;
	}
	public boolean addPassenger(Passenger passenger){
		if(this.passengers.size() < this.maxPassenger){
			this.passengers.add(passenger);
			return true;
		}else{
			return false;
		}
	}
	public Collection<Score> getScores() {
		return scores;
	}
	public void setScores(Collection<Score> scores) {
		this.scores = scores;
	}
	public void addScore(Score score){
		this.scores.add(score);
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
	public Long getIdTrip() {
		return idTrip;
	}
	public void setIdTrip(Long idTrip) {
		this.idTrip = idTrip;
	}
	public float getAverageScore(){
		float average = 0;
		if(scores.size() > 0){
			for (Iterator<Score> iterator = scores.iterator(); iterator.hasNext();) {
				average = average + iterator.next().getScore();
	        }
			average = average / scores.size(); 
		}
		return average;
	}
	
	
}

