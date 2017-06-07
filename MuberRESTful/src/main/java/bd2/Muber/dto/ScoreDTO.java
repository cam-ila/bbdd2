/**
 * 
 */
package bd2.Muber.dto;

import bd2.Muber.model.Passenger;
import bd2.Muber.model.Score;
import bd2.Muber.model.Trip;

/**
 * @author cami
 *
 */
public class ScoreDTO {
	private Long idScore;
	private Trip trip;
	private Passenger passenger;
	private Integer	score;
	private String description;
	
	public ScoreDTO(Score aScore){
		this.setIdScore(aScore.getIdScore());
		this.setTrip(aScore.getTrip());
		this.setPassenger(aScore.getPassenger());
		this.setScore(aScore.getScore());
		this.setDescription(aScore.getDescription());
	}

	/**
	 * @return the trip
	 */
	public Trip getTrip() {
		return trip;
	}

	/**
	 * @param trip the trip to set
	 */
	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	/**
	 * @return the passenger
	 */
	public Passenger getPassenger() {
		return passenger;
	}

	/**
	 * @param passenger the passenger to set
	 */
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	/**
	 * @return the score
	 */
	public Integer getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(Integer score) {
		this.score = score;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the idScore
	 */
	public Long getIdScore() {
		return idScore;
	}

	/**
	 * @param idScore the idScore to set
	 */
	public void setIdScore(Long idScore) {
		this.idScore = idScore;
	}
}
