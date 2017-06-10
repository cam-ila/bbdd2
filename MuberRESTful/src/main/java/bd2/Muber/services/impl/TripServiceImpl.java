/**
 * 
 */
package bd2.Muber.services.impl;

import java.util.Date;
import java.util.List;

import bd2.Muber.dto.TripDTO;
import bd2.Muber.model.Trip;
import bd2.Muber.services.TripService;

/**
 * 
 *
 */
public class TripServiceImpl extends BaseService implements TripService{

	@Override
	public TripDTO findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TripDTO> findAllClosedTrips() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TripDTO> findAllOpenedTrips() {
		return tripRepository.getOpenedTrips();
	}

	@Override
	public boolean saveTrip(Long idDriver,Date date, Integer maxPassenger, Double price,String origin,String destination) {		
		return tripRepository.saveTrip(idDriver, date, maxPassenger, price, origin, destination);
	}

	@Override
	public boolean updateTrip(Long tripId, Long passengerId) {
		return tripRepository.updateTrip(tripId, passengerId);
		
	}

	@Override
	public String closeTrip(Long tripId) {
		return tripRepository.closeTrip(tripId);
	}
	

}
