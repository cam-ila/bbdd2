/**
 * 
 */
package bd2.Muber.dto;

import java.util.Collection;
import java.util.Date;

import bd2.Muber.model.Driver;
import bd2.Muber.model.Passenger;
import bd2.Muber.model.Score;
import bd2.Muber.model.Trip;

/**
 * 
 *
 */
public class TripDTO {
	private Long idTrip;
	private Collection<Passenger> passengers;
	private Collection<Score> scores;
	private DriverDTO driver;
	private Date date;
	private Integer maxPassenger;
	private Boolean state;
	private double price;
	private String origin;
	private String destination;
	
	public TripDTO(Trip aTrip){
		this.idTrip = aTrip.getIdTrip();
		this.setPassengers(aTrip.getPassengers());
		this.setScores(aTrip.getScores());
		this.setDriver(aTrip.getDriver());
		this.setDate(aTrip.getDate());
		this.setMaxPassenger(aTrip.getMaxPassenger());
		this.setState(aTrip.getState());
		this.setPrice(aTrip.getPrice());
		this.setOrigin(aTrip.getOrigin());
		this.setDestination(aTrip.getDestination());
	}
	public TripDTO() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the idTrip
	 */
	public Long getIdTrip() {
		return idTrip;
	}
	/**
	 * @param idTrip the idTrip to set
	 */
	public void setIdTrip(Long idTrip) {
		this.idTrip = idTrip;
	}
	/**
	 * @return the passengers
	 */
	public Collection<Passenger> getPassengers() {
		return passengers;
	}
	/**
	 * @param passengers the passengers to set
	 */
	public void setPassengers(Collection<Passenger> passengers) {
		this.passengers = passengers;
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
	 * @return the driver
	 */
	public DriverDTO getDriver() {
		return driver;
	}
	/**
	 * @param driver the driver to set
	 */
	public void setDriver(Driver driver) {
		this.driver = new DriverDTO(driver);
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the maxPassenger
	 */
	public Integer getMaxPassenger() {
		return maxPassenger;
	}
	/**
	 * @param maxPassenger the maxPassenger to set
	 */
	public void setMaxPassenger(Integer maxPassenger) {
		this.maxPassenger = maxPassenger;
	}
	/**
	 * @return the state
	 */
	public Boolean getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(Boolean state) {
		this.state = state;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}
	/**
	 * @param origin the origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}
	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	
}
