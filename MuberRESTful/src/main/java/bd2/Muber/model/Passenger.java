package bd2.Muber.model;

import java.util.Collection;
import java.util.HashSet;

import bd2.Muber.model.Score;
import bd2.Muber.model.Trip;
import bd2.Muber.model.User;

/**
 * Passenger is a subclass from User that 
 * implements its own methods.
 */
public class Passenger extends User{
	
	
	/**
    * A collection of scores
    */
	private Collection<Score> scores;
	/**
    * The current amount of credit available 
    */
	private double credit;
	
	
	public Passenger(){
		this.scores = new HashSet<Score>();		
	}
	/**
    * Passenger object's constructor
    */
	public Passenger(String fullName, String password, double credit){
		super(fullName, password);
		this.credit = credit;
		this.scores = new HashSet<Score>();
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}
	
	public void addCredit(double monto) {
		this.credit += monto;
	}

	public void addScore(Trip trip, Integer intScore){
		/**trip.addScore(this, intScore);
	**/	
	}
	
	public Collection<Score> getScores(){
		return this.scores;
	}
	
	public void setScores(Collection<Score> scores) {
		this.scores = scores;
	}
	/**
    * Asks a Trip to join.
    * 
    * @return true  when was successfully added to the trip
    * @return false  when the trip could not add the passenger to the trip
    */
	public boolean singUpForTrip(Trip trip){
		if(trip.addPassenger(this)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
    * Substracts from the passenger's credit.
    * 
    * @param amount  The amount of credit to substract
    */
	public void lessCredit(Double amount){
			this.credit -= amount;
	}
	
}
