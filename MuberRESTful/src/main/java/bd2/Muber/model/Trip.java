package bd2.Muber.model;


import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

public class Trip {

	private Long idTrip;
	/**
    * A collection of the passengers singed up for the trip
    */
	private Collection<Passenger> passengers;
	/**
    * A collection of scores to filled up once the state of the trips goes to closed.
    */
	private Collection<Score> scores;
	/**
    * The Driver who created the trip.
    */
	private Driver driver;
	/**
    * Date of creation
    */
	private Date date;
	/**
    * Max quantity of passengers allowed to sing up for the trip.
    */
	private Integer maxPassenger;
	/**
    * The state of the trip, open or closed. It can only be closed once. 
    */
	private Boolean state;
	/**
    * The price of the fuul trip
    */
	private double price;
	/**
    * Places of origin an destination.
    */
	private String origin;
	private String destination;
	
	public Trip(){
		this.passengers = new HashSet<Passenger>();
		this.scores = new HashSet<Score>();	
		this.state = true;
	}
	/**
    * Trip object's constructor.
    */
	public Trip(Driver driver, Date date,Integer maxPassenger, double price, String origin, String destination){
		this.driver = driver;
		this.date = date;
		this.maxPassenger = maxPassenger;
		this.state = true;
		this.price = price;
		this.origin = origin;
		this.destination = destination;
		this.passengers = new HashSet<Passenger>();
		this.scores = new HashSet<Score>();
	}
		
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}	
	
	public Collection<Passenger> getPassengers() {
		return passengers;
	}
	
	public void setPassengers(Collection<Passenger> passengers) {
		this.passengers = passengers;
	}
	/**
    * Adds a passenger to the collection of passengers singed up only if there is space available.
    * 
    * @param size  the current amount of passengers singed up
    * @param maxPassenger  the top amount of passenger allowed to sing up.
    * 
    * returns true or false according to success or failure.
    */
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
		
	public Long getIdTrip() {
		return idTrip;
	}
	
	public void setIdTrip(Long idTrip) {
		this.idTrip = idTrip;
	}
	/**
    * Iterates through the score collection and calculates the average score. Adding all the values and dividing
    * for the amount of scores.
    * 
    * @param average  variable where the average is calculated and saved
    * @param score.size  the amount of scores in the collection
    * 
    * @return average  the average score of the trip
    */
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

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	/**
    * Closes the Trip. It will only close once, if the trip was already clased this will retun false.
    * Closing the Trip: 
    *   set it's state to false, 
    *   divide the total prize of the trip according to the amount of passengers
    *   iterates through all of the passengers subtracting the corresponding amount.
    *   
    * @param price   Total amount of credits the Trips is worth
    * @param amount  The price divided by the size of the passenger collection.
    * 
    * @return true or false according to success or failure
    * 
    */
	public boolean close() {
		if (this.state == true){			
			this.state = false;
			double amount = this.price / this.getPassengers().size();
			Iterator<Passenger> iter = this.getPassengers().iterator();
			while (iter.hasNext()) {
				Passenger elem = iter.next();
				elem.lessCredit(amount);
			}
			return true;
		}else{
			return false;
		}
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	
}

