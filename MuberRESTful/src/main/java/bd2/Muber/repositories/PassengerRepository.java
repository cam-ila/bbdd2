package bd2.Muber.repositories;

import java.util.List;

import bd2.Muber.dto.PassengerDTO;
/**
 * The interface for the Passenger Repository. 
 */
public interface PassengerRepository extends BaseRepository {
	
	PassengerDTO getPassenger(Long id);
	List<PassengerDTO> getPassengers();
	void updateCreditPassenger(Long passengerId, Double monto);
}