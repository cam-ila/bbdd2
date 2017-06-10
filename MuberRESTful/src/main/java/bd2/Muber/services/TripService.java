/**
 * 
 */
package bd2.Muber.services;

import java.util.List;

import bd2.Muber.dto.TripDTO;


/**
 * @author cami
 *
 */
public interface TripService {
	
	TripDTO findById(Long id);
	
	List<TripDTO> findAllClosedTrips();
	
	List<TripDTO> findAllOpenedTrips();
	
	void saveTrip(TripDTO aTrip);
	
	void updateTrip(TripDTO aTrip);
	
	void closeTrip(TripDTO aTrip);

}
