package bd2.Muber.services;

import java.util.List;

import bd2.Muber.model.Passenger;

public interface PassengerService {
	
	Passenger findById(Long id);	
	
	void savePassenger(Passenger aPassenger);
	
	void updatePassenger(Passenger aPassenger);
	
	List<Passenger> findAllPassenger();
	
}
