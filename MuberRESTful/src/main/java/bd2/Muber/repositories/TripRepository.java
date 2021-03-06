package bd2.Muber.repositories;

import java.util.Date;
import java.util.List;

import bd2.Muber.dto.TripDTO;

/**
 * The interface for the Trip Repository. 
 */
public interface TripRepository extends BaseRepository {

	List<TripDTO> getTrips();
	TripDTO getTrip(Long id);
	List<TripDTO> getClosedTrips();
	List<TripDTO> getOpenedTrips();
	Boolean saveTrip (Long idDriver,Date date, Integer maxPassenger, Double price,String origin,String destination);
	Boolean updateTrip (Long tripId, Long passengerId);
	String closeTrip (Long tripId);
	
}
