package bd2.Muber.services;

import bd2.Muber.dto.DTOFactory;

public class ServiceLocator {

	static PassengerService passengerService;
	static DriverService driverService;
	static TripService tripService;
	static ScoreService scoreService;
	
	public DTOFactory dtoFactory;
	

	public static ServiceLocator getInstance() {
		return new ServiceLocator();
	}

	public DTOFactory getDtoFactory() {
		return dtoFactory;
	}
	
	/**
	 * @param dtoFactory the dtoFactory to set
	 */
	public void setDtoFactory(DTOFactory dtoFactory) {
		this.dtoFactory = dtoFactory;
	}	
	/**
	 * @return the passengerService
	 */
	public static PassengerService getPassengerService() {
		return passengerService;
	}

	/**
	 * @param passengerService the passengerService to set
	 */
	public static void setPassengerService(PassengerService passengerService) {
		ServiceLocator.passengerService = passengerService;
	}

	/**
	 * @return the driverService
	 */
	public static DriverService getDriverService() {
		return driverService;
	}

	/**
	 * @param driverService the driverService to set
	 */
	public static void setDriverService(DriverService driverService) {
		ServiceLocator.driverService = driverService;
	}

	/**
	 * @return the tripService
	 */
	public static TripService getTripService() {
		return tripService;
	}

	/**
	 * @param tripService the tripService to set
	 */
	public static void setTripService(TripService tripService) {
		ServiceLocator.tripService = tripService;
	}

	/**
	 * @return the scoreService
	 */
	public static ScoreService getScoreService() {
		return scoreService;
	}

	/**
	 * @param scoreService the scoreService to set
	 */
	public static void setScoreService(ScoreService scoreService) {
		ServiceLocator.scoreService = scoreService;
	}
}