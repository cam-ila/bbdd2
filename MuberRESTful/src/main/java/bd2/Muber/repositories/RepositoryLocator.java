package bd2.Muber.repositories;

import bd2.Muber.repositories.impl.HibernateDriverRepository;
import bd2.Muber.repositories.impl.HibernatePassengerRepository;
import bd2.Muber.repositories.impl.HibernateScoreRepository;
import bd2.Muber.repositories.impl.HibernateTripRepository;

public class RepositoryLocator {
	
	static PassengerRepository passengerRepository;
	static DriverRepository driverRepository;
	static TripRepository tripRepository;
	static ScoreRepository scoreRepository;
	
	public static RepositoryLocator getInstance() {
        return new RepositoryLocator();
    }
	
	 public static void setPassengerRepository(PassengerRepository repository){
	    passengerRepository = repository;
	 }
	 
	 public static PassengerRepository getPassengerRepository(){
    	return passengerRepository;
    }
	 
	 
	public static void setDriverRepository(DriverRepository repository){
    	driverRepository = repository;
	}
	 
	public static DriverRepository getDriverRepository(){
    	return driverRepository;
	}
	
	public static void setTripRepository(TripRepository repository){
    	tripRepository = repository;
	}
 
	public static TripRepository getTripRepository(){
    	return tripRepository;
	}
	
	public static void setScoreRepository(ScoreRepository repository){
    	scoreRepository = repository;
	}
 
	public static ScoreRepository getScoreRepository(){
    	return scoreRepository;
	}

}
