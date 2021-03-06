package model;

import java.util.Collection;
import java.util.HashSet;

import model.Score;
import model.Trip;
import model.User;

public class Passenger extends User{
	

	private Collection<Score> scores;
	private double credit;
	

	public Passenger(){
		this.scores = new HashSet<Score>();		
	}
	
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
	
	public boolean singUpForTrip(Trip trip){
		if(trip.addPassenger(this)){
			return true;
		}else{
			return false;
		}
	}
	
	public void lessCredit(Double amount){
			this.credit -= amount;
	}
	
}
