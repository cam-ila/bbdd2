/**
 * 
 */
package bd2.Muber.dto;

import java.util.Collection;

import bd2.Muber.model.Passenger;
import bd2.Muber.model.Score;


/**
 * @author cami
 *
 */
public class PassengerDTO extends UserDTO{
	private Collection<Score> scores;
	private double credit;
	
	
	public PassengerDTO(Passenger aPassenger) {
		super(aPassenger);
		this.setCredit(aPassenger.getCredit());
		this.setScores(aPassenger.getScores());		
	}


	public PassengerDTO() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the scores
	 */
	public Collection<Score> getScores() {
		return scores;
	}


	/**
	 * @param scores the scores to set
	 */
	public void setScores(Collection<Score> scores) {
		this.scores = scores;
	}


	/**
	 * @return the credit
	 */
	public double getCredit() {
		return credit;
	}


	/**
	 * @param credit the credit to set
	 */
	public void setCredit(double credit) {
		this.credit = credit;
	}
	

}
