package bd2.Muber.model;

import java.util.Iterator;

import bd2.Muber.model.Trip;

public class Score {
	private Trip trip;
	private Passenger passenger;
	private Integer	score;
	private String description;
	
	private Long idScore;
	
	public Score(){
		
	}
	
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

