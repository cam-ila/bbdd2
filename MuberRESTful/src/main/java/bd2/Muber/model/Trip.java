package bd2.Muber.model;


import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

public class Trip {

	private Long idTrip;
	private Collection<Passenger> passengers;
	private Collection<Score> scores;
	private Driver driver;
	private Date date;
	private Integer maxPassenger;
	private Boolean state;
	private double price;
	private String origin;
	private String destination;
	
	public Trip(){
		this.passengers = new HashSet<Passenger>();
		this.scores = new HashSet<Score>();	
		this.state = true;
	}
	
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

	public void close() {
		this.state = false;
		double amount = this.price / this.getPassengers().size();
		Iterator<Passenger> iter = this.getPassengers().iterator();
		while (iter.hasNext()) {
		    Passenger elem = iter.next();
		    elem.lessCredit(amount);
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

