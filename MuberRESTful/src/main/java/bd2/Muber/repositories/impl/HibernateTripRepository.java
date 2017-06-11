package bd2.Muber.repositories.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import bd2.Muber.dto.DriverDTO;
import bd2.Muber.dto.PassengerDTO;
import bd2.Muber.dto.TripDTO;
import bd2.Muber.model.*;
import bd2.Muber.repositories.TripRepository;

@Transactional
public class HibernateTripRepository extends BaseHibernateRepository implements TripRepository{

	public HibernateTripRepository(){
		super();
	}
	/**
	 *  Asks for a Trip using an id to the DB and saves it on a TripDTP object before returning it.
	 *  
	 *  @retun tripDTO  an object of the class TripDTO
	 */
	@Override
	public TripDTO getTrip(Long id){
		Session session = this.getSession();	
		Transaction tx = null;
		tx = session.beginTransaction();
		String hql = "FROM bd2.Muber.model.Trip T WHERE T.idTrip = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		Trip result = (Trip) query.uniqueResult();
		
		TripDTO tripDTO = new TripDTO();
		if (result != null){
			tripDTO = new TripDTO(result);
		}
		tx.rollback();
		session.disconnect();
		session.close();
		return tripDTO;
	}
	
	
	/**
	 *  Asks for all the Trips with the state "open" to the DB
	 *  and saves them one by one on a TripDTO object to put it on a 
	 *  list of TripDTO objects before returning the list.
	 *  
	 *  @retun tripsDTO  a list of TripDTO objects
	 */
	@Override
	public List<TripDTO> getOpenedTrips(){
		Session session = this.getSession();	
		Transaction tx = session.beginTransaction();
		String hql = "FROM bd2.Muber.model.Trip p WHERE p.state=true ";
		Query query = session.createQuery(hql);
		List<Trip> result = query.list();
		
		List<TripDTO> tripsDTO = new ArrayList<TripDTO>();
		for (Trip t : result) {
			TripDTO trip = new TripDTO(t);
			tripsDTO.add(trip);
		}
		tx.rollback();
		session.disconnect();
		session.close();
		return tripsDTO;
	}
	
	/**
	 *  Asks for all the Trips with the state "clased" to the DB
	 *  and saves them one by one on a TripDTO object to put it on a 
	 *  list of TripDTO objects before returning the list.
	 *  
	 *  @retun tripsDTO  a list of TripDTO objects
	 */
	@Override
	public List<TripDTO> getClosedTrips(){
		Session session = this.getSession();	
		Transaction tx = null;
		tx = session.beginTransaction();
		
		String hql = "FROM bd2.Muber.model.Trip p WHERE p.state=false ";
		Query query = session.createQuery(hql);
		List<Trip> result = query.list();
		
		List<TripDTO> tripsDTO = new ArrayList<TripDTO>();
		for (Trip t : result) {
			TripDTO trip = new TripDTO(t);
			tripsDTO.add(trip);
		}
		tx.rollback();
		session.disconnect();
		session.close();
		return tripsDTO;
	}
	/**
	 *  Creates a new trip linked to a Driver ID that made the request.
	 *  
	 *  @retun true or false according to success or failure
	 */
	@Override
	public Boolean saveTrip(Long idDriver,Date date, Integer maxPassenger, Double price,String origin,String destination){
		Session session = this.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			String hql = "FROM bd2.Muber.model.Driver P WHERE P.idUser = ?";
			Query query = session.createQuery(hql);
			query.setParameter(0, idDriver);
			Driver result =(Driver) query.uniqueResult();
			
			Trip aTrip = new Trip(result, date, maxPassenger, price, origin, destination);
			session.save(aTrip);
			
			tx.commit();
			session.disconnect();
			session.close();
			return true;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
				session.disconnect();
				session.close();
				return false;
		}
		
	}
	
	/**
	 *  Adds a new passenger to the list of passengers singed up for the trip.
	 *  
	 *  @retun true or false according to success or failure
	 */
	@Override
	public Boolean updateTrip(Long tripId, Long passengerId){
		try {
			Session session = this.getSession();
			Transaction tx = session.beginTransaction();
			
			String hql = "FROM bd2.Muber.model.Passenger P WHERE P.idUser = ?";
			Query query = session.createQuery(hql);
			query.setParameter(0, passengerId);
			Passenger aPassenger = (Passenger) query.uniqueResult();
			 
			String hql1 = "FROM bd2.Muber.model.Trip T WHERE T.idTrip = ?";
			Query query1 = session.createQuery(hql1);
			query1.setParameter(0, tripId);
			Trip aTrip = (Trip) query1.uniqueResult();
		
			session.saveOrUpdate(aTrip);
			if (aTrip.addPassenger(aPassenger)){
				tx.commit();
				session.disconnect();
				session.close();
				return true;
			}else{
				tx.rollback();
				session.disconnect();
				session.close();
				return false;
			}
		}
		catch(Exception e){			
			return false;
		}
	}
		
		
	
	/**
	 *  Tries to close a trip. It will only succeed if the trip was opened. 
	 *  
	 *  @retun string   indicating if the closing of the trip was successful or not.
	 */
	@Override
	public String closeTrip(Long tripId){
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		 
		String hql1 = "FROM bd2.Muber.model.Trip T WHERE T.idTrip = ?";
		Query query1 = session.createQuery(hql1);
		query1.setParameter(0, tripId);
		Trip aTrip = (Trip) query1.uniqueResult();
	
		session.saveOrUpdate(aTrip);
		if (aTrip.close()){
			tx.commit();
			session.close();
			return "Viaje Finalizado";
		}else{
			tx.rollback();
			session.close();
			return "El viaje ya se encontraba finalizado, operacion cancelada";
		}
		
	}

	@Override
	public List<TripDTO> getTrips() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
