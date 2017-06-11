package bd2.Muber.repositories.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bd2.Muber.dto.DriverDTO;

import bd2.Muber.model.*;
import bd2.Muber.repositories.DriverRepository;
/**
 * The Driver's repository implementation class. 
 */
public class HibernateDriverRepository extends BaseHibernateRepository implements DriverRepository{
	
	/**
	 *  Asks for a Driver using an id to the DB and saves it on a DriverDTO object before returning it.
	 *  
	 *  @retun driverDTO  an object of the class DriverDTO
	 */
	public DriverDTO getDriver(Long id){
		Session session = this.getSession();	
		Transaction tx = null;
		tx = session.beginTransaction();
		
		String hql = "FROM bd2.Muber.model.Driver P WHERE P.idUser = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		Driver result =(Driver) query.uniqueResult();
		
		DriverDTO driverDTO = new DriverDTO();
		if (result != null){
			driverDTO = new DriverDTO(result);
		}
		tx.rollback(); //por lo que vi en algunos lugares, a menos que se guarden cosas en la BD se hace rollback por las dudas
		session.disconnect();
		session.close();
		return driverDTO;
	}
	
	/**
	 *  Asks for all the Drivers to the DB and saves one by one on a DriverDTO object to put it on a 
	 *  list of DriverDTO objects before returning the list.
	 *  
	 *  @retun driversDTO  a list of DriverDTO objects
	 */
	public List<DriverDTO> getDrivers(){
		Session session = this.getSession();	
		Transaction tx = null;
		tx = session.beginTransaction();
		
		String hql = "FROM bd2.Muber.model.Driver";
		Query query = session.createQuery(hql);
		List<Driver> result = query.list();
		
		List<DriverDTO> driversDTO = new ArrayList<DriverDTO>();
		for (Driver d : result) {
			DriverDTO dri = new DriverDTO(d);
			driversDTO.add(dri);
		}
		tx.rollback();
		session.disconnect();
		session.close();		
		return driversDTO;
	}
	
	
	public List<DriverDTO> getDriversTop10(){ 
		Session session = this.getSession();	
		Transaction tx = null;
		tx = session.beginTransaction();
		
		String hql = "FROM bd2.Muber.model.Driver"; // ejemplo de consulta
		
		Query query = session.createQuery(hql);
		List<Driver> result = query.list();
		
		List<DriverDTO> driversDTO = new ArrayList<DriverDTO>();
		for (Driver d : result) {
			DriverDTO dri = new DriverDTO(d);
			driversDTO.add(dri);
		}
		tx.rollback();
		session.disconnect();
		session.close();		
		return driversDTO;
	}


	
	
}
