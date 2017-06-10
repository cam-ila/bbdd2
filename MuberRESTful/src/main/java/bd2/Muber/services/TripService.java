/**
 * 
 */
package bd2.Muber.services;

import java.util.Date;
import java.util.List;

import bd2.Muber.dto.TripDTO;


/**
 * 
 *
 */
public interface TripService {
	
	TripDTO findById(Long id);
	
	List<TripDTO> findAllClosedTrips();
	
	List<TripDTO> findAllOpenedTrips();
	
	boolean updateTrip(Long tripId, Long passengerId);
	
	String closeTrip (Long tripId);

	boolean saveTrip(Long idDriver, Date date, Integer maxPassenger, Double price, String origin, String destination);

}
