/**
 * 
 */
package bd2.Muber.services.impl;

import java.util.List;

import bd2.Muber.model.Trip;

/**
 * @author cami
 *
 */
public interface TripService {
	
	Trip findById(Long id);
	
	List<Trip> findAllClosedTrips();
	
	List<Trip> findAllOpenedTrips();
	
	void saveTrip(Trip aTrip);
	
	void updateTrip(Trip aTrip);
	
	void closeTrip(Trip aTrip);

}
