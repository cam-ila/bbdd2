package bd2.Muber.services;

import java.util.List;

import bd2.Muber.dto.PassengerDTO;

public interface PassengerService {
	
	PassengerDTO findById(Long id);	
	
	void savePassenger(PassengerDTO aPassenger);
	
	void updatePassenger(PassengerDTO aPassenger);
	
	List<PassengerDTO> findAllPassenger();
	
}
