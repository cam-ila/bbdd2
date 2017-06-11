package bd2.Muber.model;

import java.util.Iterator;

import bd2.Muber.model.Trip;

public class Score {
	/**
    * The Trip this score is from
    */
	private Trip trip;
	/**
    * The Passenger who made the scoring
    */
	private Passenger passenger;
	/**
    * The value of the score in numbers
    */
	private Integer	score;
	/**
    * A description of the score
    */
	private String description;
	
	private Long idScore;
	
	public Score(){
		
	}
	/**
    * Score object's constructor. It checks for a couple of things before creating it.
    * It checks for: 
    *    if the passenger creating it is singed up in the trip.
    *    if the trip is in closed state.
    *    if the passenger didn't created a score for this trip previously.
    */
	public Score(Trip trip, Passenger passenger, Integer score, String description){
		Boolean noexiste = true;
		if (trip.getPassengers().contains(passenger) && !trip.getState()){
			Iterator<Score> itr = trip.getScores().iterator();
			while(itr.hasNext()) {
				Score aScore = itr.next();
				if (aScore.passenger.equals(passenger)){
					noexiste = false;
				}
			}
			
		}
		if (noexiste) {
		this.trip = trip;
		this.passenger = passenger;
		this.score = score;
		this.description = description;
		}

	}
	
	public Trip getTrip() {
		return trip;
	}
	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	public Passenger getPassenger() {
		return passenger;
	}
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Long getIdScore() {
		return idScore;
	}
	public void setIdScore(Long idScore) {
		this.idScore = idScore;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}

