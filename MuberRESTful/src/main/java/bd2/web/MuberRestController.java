package bd2.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.gson.Gson;

import bd2.Muber.model.Driver;
import bd2.Muber.model.Passenger;
import bd2.Muber.model.Trip;

@ControllerAdvice
@RequestMapping("/services")
@ResponseBody
@EnableWebMvc
public class MuberRestController {

	
	
	protected Session getSession() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();

		return session;
	}
	private void endSession(Session session){	
	       // session.getTransaction().commit();
	        //session.close();
	    	session.disconnect();
	}
	/* Lista al pasajero id=2 y anda
	private List sarasa(){
		Session session = getSession();	
		Transaction tx = null;
		tx = session.beginTransaction();
		String hql = "SELECT P.fullName FROM bd2.Muber.model.Passenger P WHERE P.id=2";
		Query query = session.createQuery(hql);
		List result = query.list();
		tx.commit();
		endSession(session);	
		return result;
	}
	*/	

	private List<Passenger> getPassenger(){
		Session session = getSession();	
		Transaction tx = null;
		tx = session.beginTransaction();
		String hql = "FROM bd2.Muber.model.Passenger";
		Query query = session.createQuery(hql);
		List<Passenger> result = query.list();
		tx.commit();
		endSession(session);	
		return result;
	}
	
	private List<Driver> getDrivers(){
		Session session = getSession();	
		Transaction tx = null;
		tx = session.beginTransaction();
		String hql = "FROM bd2.Muber.model.Driver";
		Query query = session.createQuery(hql);
		List<Driver> result = query.list();
		tx.commit();
		endSession(session);	
		return result;
	}
	
	private List<Trip> getClosedTrips(){
		Session session = getSession();	
		Transaction tx = null;
		tx = session.beginTransaction();
		String hql = "FROM bd2.Muber.model.Trip p WHERE p.state=true ";
		Query query = session.createQuery(hql);
		List<Trip> result = query.list();
		tx.commit();
		endSession(session);	
		return result;
	}

	private List<Driver> getDriver(Long id){
		Session session = getSession();	
		Transaction tx = null;
		tx = session.beginTransaction();
		String hql = "FROM bd2.Muber.model.Driver P WHERE P.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		List<Driver> result = query.list();
		tx.commit();
		endSession(session);	
		return result;
	}
	
	//Listar todos los pasajeros registrados en Muber
	@RequestMapping(value = "/pasajeros", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String pasajeros() {
		Map<Long, Object> aMap = new HashMap<Long, Object>();		
		List<Passenger> passengerList = getPassenger();
		for (Passenger p : passengerList){ 
			aMap.put(p.getIdUser(), p.getFullName());
		}
		return new Gson().toJson(aMap);		
	}
	
	//Listar todos los conductores registrados en Muber
	@RequestMapping(value = "/conductores", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String conductores() {
		Map<Long, Object> aMap = new HashMap<Long, Object>();		
		List<Driver> driversList = getDrivers();
		for (Driver d : driversList){ 
			aMap.put(d.getIdUser(), d.getFullName());
		}
		return new Gson().toJson(aMap);		
	}
	
	//Listar todos los viajes abiertos en Muber
	//TODO:Hacer bien el JSON
	@RequestMapping(value = "/abiertos", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String abiertos() {
		Map<Long, Object> aMap = new HashMap<Long, Object>();		
		List<Trip> tripsList = getClosedTrips();
		for (Trip t : tripsList){ 
			aMap.put(t.getIdTrip(), t.getDriver().getFullName());
		}
		return new Gson().toJson(aMap);		
	}
	
	//Obtener la información de un conductor
	//(nombre de usuario, viajes realizados, puntaje promedio y fecha de licencia)
	//TODO:Crear bien la url
	@RequestMapping(value = "/conductores/detalles/{conductorId}",  method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String conductoresDetalles(@PathVariable(value="conductorId") Long conductorId) {

		Map<String, Object> aMap = new HashMap<String, Object>();		
		List<Driver> driverList = getDriver(conductorId);
		for (Driver d : driverList){ 
			aMap.put("Nombre", d.getFullName());
			//Itera por cada viaje para listar los viajer realizadob
			for (Trip t : d.getTrips()){ 
				aMap.put(t.getIdTrip().toString(), t.getDate());
			}
			aMap.put("puntaje promedio", d.averageScore());
			aMap.put("Fecha de licencia", d.getLicenseDate());
			
		}
		return new Gson().toJson(aMap);		
	}
	
	
	//Crear un viaje
	
	
	
	//Agregar un pasajero a un viaje ya creado
	//Crear una calificación de un pasajero para un viaje en particular
	//Cargar crédito a un pasajero en particular
	//Finalizar un viaje. Considerar que el viaje sólo puede finalizarse una vez.
	//Listar los 10 conductores mejor calificados que no tengan viajes abiertos registrados


}
