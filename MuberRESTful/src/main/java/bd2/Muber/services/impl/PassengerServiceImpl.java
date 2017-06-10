/**
 * 
 */
package bd2.Muber.services.impl;

import java.util.List;

import bd2.Muber.dto.PassengerDTO;

import bd2.Muber.services.PassengerService;

/**
 * @author cami
 * Dentro de esot llama al repository
 * https://examples.javacodegeeks.com/enterprise-java/spring/mvc/spring-mvc-hibernate-tutorial/#subsection_5
 */
public class PassengerServiceImpl extends BaseService implements PassengerService {

	@Override
	public PassengerDTO findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePassenger(PassengerDTO aPassenger) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePassenger(PassengerDTO aPassenger) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PassengerDTO> findAllPassenger() {
		return passengerRepository.getPassengers();
	}

	
}
