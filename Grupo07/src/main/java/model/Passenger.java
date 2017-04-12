package model;

import java.util.Collection;

public class Passenger extends User{
	
	private long idPassenger;
	private Collection<Score> scores;
	
	public Passenger(){
	}
	
	public long getIdPassenger(){
		return this.idPassenger;
	}
	public void setIdPassenger(long id){
		this.idPassenger = id;
	}
	public void addScore(Trip trip, Integer intScore){
		Score score = new Score(trip,this,intScore);
		trip.addScore(score);
		this.scores.add(score);
	}
	public Collection<Score> getScores(){
		return this.scores;
	}
	public boolean singUpForTrip(Trip trip){
		if(trip.addPassenger(this)){
			return true;
		}else{
			return false;
		}
	}
	
}
