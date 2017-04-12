package model;

public class Score {
	private Trip trip;
	private Passenger passenger;
	private Integer	score;
	private Long idScore;
	
	public Score(){
		
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
	
}

