/**
 * 
 */
package bd2.Muber.dto;

import java.util.Collection;
import java.util.Date;

import bd2.Muber.model.Driver;
import bd2.Muber.model.Trip;

/**
 * @author cami
 *
 */
public class DriverDTO extends UserDTO {
	private Collection<Trip> trips;
	private Date licenseDate;
	private boolean haveOpenTrip;
	private float averageScore;
	
	public DriverDTO(){
		
	}

	public DriverDTO(Driver aDriver){
		super(aDriver);
		this.setLicenseDate(aDriver.getLicenseDate());
		this.setHaveOpenTrip(aDriver.haveOpenTrip());
		this.setAverageScore(aDriver.averageScore());	
		this.setTrips(aDriver.getTrips());
	}

	
	/**
	 * @return the trips
	 */
	public Collection<Trip> getTrips() {
		return trips;
	}

	/**
	 * @param trips  the trips to set
	 */
	public void setTrips(Collection<Trip> trips) {
		this.trips = trips;
	}

	/**
	 * @return the licenseDate
	 */
	public Date getLicenseDate() {
		return licenseDate;
	}

	/**
	 * @param licenseDate the licenseDate to set
	 */
	public void setLicenseDate(Date licenseDate) {
		this.licenseDate = licenseDate;
	}

	/**
	 * @return the haveOpenTrip
	 */
	public boolean isHaveOpenTrip() {
		return haveOpenTrip;
	}

	/**
	 * @param haveOpenTrip the haveOpenTrip to set
	 */
	public void setHaveOpenTrip(boolean haveOpenTrip) {
		this.haveOpenTrip = haveOpenTrip;
	}

	/**
	 * @return the averageScore
	 */
	public float getAverageScore() {
		return averageScore;
	}

	/**
	 * @param averageScore the averageScore to set
	 */
	public void setAverageScore(float averageScore) {
		this.averageScore = averageScore;
	}

}
