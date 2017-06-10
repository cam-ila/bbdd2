package bd2.Muber.services.impl;

import bd2.Muber.dto.DTOFactory;
import bd2.Muber.repositories.impl.HibernateDriverRepository;
import bd2.Muber.repositories.impl.HibernatePassengerRepository;
import bd2.Muber.repositories.impl.HibernateScoreRepository;
import bd2.Muber.repositories.impl.HibernateTripRepository;

public class BaseService {
	public HibernatePassengerRepository passengerRepository;
	public HibernateDriverRepository driverRepository;
	public HibernateTripRepository tripRepository;
	public HibernateScoreRepository scoreRepository;
	
	public DTOFactory dtoFactory;
	
	public DTOFactory getDtoFactory() {
		return dtoFactory;
	}
		
	/**
	 * @return the passengerRepository
	 */
	public HibernatePassengerRepository getPassengerRepository() {
		return passengerRepository;
	}

	/**
	 * @param passengerRepository the passengerRepository to set
	 */
	public void setPassengerRepository(HibernatePassengerRepository passengerRepository) {
		this.passengerRepository = passengerRepository;
	}

	/**
	 * @return the driverRepository
	 */
	public HibernateDriverRepository getDriverRepository() {
		return driverRepository;
	}

	/**
	 * @param driverRepository the driverRepository to set
	 */
	public void setDriverRepository(HibernateDriverRepository driverRepository) {
		this.driverRepository = driverRepository;
	}

	/**
	 * @return the tripRepository
	 */
	public HibernateTripRepository getTripRepository() {
		return tripRepository;
	}

	/**
	 * @param tripRepository the tripRepository to set
	 */
	public void setTripRepository(HibernateTripRepository tripRepository) {
		this.tripRepository = tripRepository;
	}

	/**
	 * @return the scoreRepository
	 */
	public HibernateScoreRepository getScoreRepository() {
		return scoreRepository;
	}

	/**
	 * @param scoreRepository the scoreRepository to set
	 */
	public void setScoreRepository(HibernateScoreRepository scoreRepository) {
		this.scoreRepository = scoreRepository;
	}

	/**
	 * @param dtoFactory the dtoFactory to set
	 */
	public void setDtoFactory(DTOFactory dtoFactory) {
		this.dtoFactory = dtoFactory;
	}	
	
}

