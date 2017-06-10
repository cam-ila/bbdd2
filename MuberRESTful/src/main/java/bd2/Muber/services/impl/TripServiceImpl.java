/**
 * 
 */
package bd2.Muber.services.impl;

import java.util.List;

import bd2.Muber.dto.TripDTO;
import bd2.Muber.model.Trip;
import bd2.Muber.services.TripService;

/**
 * @author cami
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
	public void saveTrip(TripDTO aTrip) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTrip(TripDTO aTrip) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeTrip(TripDTO aTrip) {
		// TODO Auto-generated method stub
		
	}
	

}
