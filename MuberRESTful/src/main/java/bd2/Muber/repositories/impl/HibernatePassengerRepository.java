package bd2.Muber.repositories.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bd2.Muber.dto.PassengerDTO;

import bd2.Muber.model.*;

import bd2.Muber.repositories.PassengerRepository;

public class HibernatePassengerRepository extends BaseHibernateRepository implements PassengerRepository{

	public HibernatePassengerRepository(){
		super();
	}
	/**
	 *  Asks for a Passenger using an id to the DB and saves it on a PassengerDTO object before returning it.
	 *  
	 *  @retun passengerDTO  an object of the class PassengerDTO
	 */
	public PassengerDTO getPassenger(Long id){
		Session session = this.getSession();	
		Transaction tx = session.beginTransaction();
		String hql = "FROM bd2.Muber.model.Passenger P WHERE P.idUser = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		Passenger result = (Passenger) query.uniqueResult();
		PassengerDTO passengerDTO = new PassengerDTO();
		if (result != null){
			passengerDTO = new PassengerDTO(result);
		}
		tx.rollback();
		session.disconnect();
		session.close();
		return passengerDTO;
	}
	/**
	 *  Asks for all the Passenger to the DB and saves one by one on a PassengerDTO object to put it on a 
	 *  list of PassengerDTO objects before returning the list.
	 *  
	 *  @retun passengersDTO  a list of PassengerDTO objects
	 */
	public List<PassengerDTO> getPassengers(){
		Session session = this.getSession();	
		Transaction tx = session.beginTransaction();
		String hql = "FROM bd2.Muber.model.Passenger";
		Query query = session.createQuery(hql);
		List<Passenger> passengers = query.list();
		List<PassengerDTO> passengerDTO = new ArrayList<PassengerDTO>();
		for (Passenger p : passengers) {
			PassengerDTO pas = new PassengerDTO(p);
			passengerDTO.add(pas);
		}
		tx.rollback();
		session.disconnect();
		session.close();		
		return passengerDTO;
	}
	
	/**
	 *  Adds credit to a specific passenger according to a passengerID
	 *  
	 *  @param monto  numerical amount of credits to be added.
	 *  
	 */
	public void updateCreditPassenger(Long passengerId, Double monto){
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		
		String hql = "FROM bd2.Muber.model.Passenger P WHERE P.idUser = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, passengerId);
		Passenger aPassenger = (Passenger) query.uniqueResult();
		
		session.saveOrUpdate(aPassenger);
		aPassenger.addCredit(monto);
		tx.commit();
		session.disconnect();
		session.close();		
	}
	
}
