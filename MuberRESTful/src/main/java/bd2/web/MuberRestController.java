package bd2.web;

import java.util.Date;
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
import bd2.Muber.model.Score;
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
	    	session.disconnect();
	    	session.close();
	}

	private List<Passenger> getPassengers(){
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
		String hql = "FROM bd2.Muber.model.Trip p WHERE p.state=false ";
		Query query = session.createQuery(hql);
		List<Trip> result = query.list();
		tx.commit();
	//	endSession(session);	
		return result;
	}
	
	private List<Trip> getOpenedTrips(){
		Session session = getSession();	
		Transaction tx = null;
		tx = session.beginTransaction();
		String hql = "FROM bd2.Muber.model.Trip p WHERE p.state=true ";
		Query query = session.createQuery(hql);
		List<Trip> result = query.list();
		tx.commit();
	//	endSession(session);	
		return result;
	}

	private Driver getDriver(Long id){
		Session session = getSession();	
		Transaction tx = null;
		tx = session.beginTransaction();
		String hql = "FROM bd2.Muber.model.Driver P WHERE P.idUser = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		Driver result =(Driver) query.uniqueResult();
		tx.commit();
		//endSession(session);
		return result;
	}
	
	private Passenger getPassenger(Long id){
		Session session = getSession();	
		Transaction tx = null;
		tx = session.beginTransaction();
		String hql = "FROM bd2.Muber.model.Passenger P WHERE P.idUser = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		Passenger result = (Passenger) query.uniqueResult();
		tx.commit();
		endSession(session);
		return result;
	} //TODO: ver eso si esta vacio no tire error
	
	private Trip getTrip(Long id){
		Session session = getSession();	
		Transaction tx = null;
		tx = session.beginTransaction();
		String hql = "FROM bd2.Muber.model.Trip T WHERE T.idTrip = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		Trip result = (Trip) query.uniqueResult();
		tx.commit();
	//	endSession(session);
		return result;
	} //TODO: ver lo de get(0) tiene que haber una forma de que me devuelva un solo elemento, o que eso si esta vcacio no tiere error
	
	//TODO: hacer la consulta magica que haga todo lo que pide, es con consulta no?
	private List<Driver> getDriversTopTen(){
		Session session = getSession();	
		Transaction tx = null;
		tx = session.beginTransaction();
		
		String hql = "FROM bd2.Muber.model.Driver"; // ejemplo de consulta
		
		Query query = session.createQuery(hql);
		List<Driver> result = query.list();
		tx.commit();
		endSession(session);	
		return result;
	}
	
	
	private Boolean saveScore(Long idTrip, Long idPassenger,Integer score, String description){
		Session session = getSession();
		
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			
			String hql = "FROM bd2.Muber.model.Passenger P WHERE P.idUser = ?";
			Query query = session.createQuery(hql);
			query.setParameter(0, idPassenger);
			Passenger aPassenger = (Passenger) query.uniqueResult();
			 
			
			String hql1 = "FROM bd2.Muber.model.Trip T WHERE T.idTrip = ?";
			Query query1 = session.createQuery(hql1);
			query1.setParameter(0, idTrip);
			Trip aTrip = (Trip) query1.uniqueResult();
			Score aScore = new Score(aTrip, aPassenger, score, description);
			
			session.save(aScore);
			tx.commit();
			session.disconnect();
			return true;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
				session.disconnect();
				return false;
		}		
		
	}
	
	private Boolean saveTrip(Long idDriver,Date date, Integer maxPassenger, Double price,String origin,String destination){
		Session session = getSession();

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

		//private void updateTrip(Long idTrip, Passenger passenger){
			//passenger.
		//}
	
	
	//Listar todos los pasajeros registrados en Muber
	@RequestMapping(value = "/pasajeros", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String pasajeros() {
		Map<Long, Object> aMap = new HashMap<Long, Object>();		
		List<Passenger> passengerList = getPassengers();
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
	@RequestMapping(value = "/abiertos", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String abiertos() {
		Map<Long, Object> aMap = new HashMap<Long, Object>();		
		List<Trip> tripsList = getOpenedTrips();
		for (Trip t : tripsList){ 
			aMap.put(t.getIdTrip(), t.getDriver().getFullName());
		}
		return new Gson().toJson(aMap);
	}
	
	//Obtener la información de un conductor (nombre de usuario, viajes realizados, puntaje promedio y fecha de licencia)
	//  $ curl -G -d "conductorId=1" http://localhost:8080/MuberRESTful/rest/services/conductores/detalles

	
	@RequestMapping(
			value = "/conductores/detalles{conductorId}",  
			method = RequestMethod.GET, 
			produces = "application/json", 
			headers = "Accept=application/json"
			)
	
	public String conductoresDetalles(
			@RequestParam("conductorId") Long conductorId
			) {

		Map<String, Object> aMap = new HashMap<String, Object>();
		Map<String, Object> tripMap = new HashMap<String, Object>();
		Driver d = getDriver(conductorId);
		
			aMap.put("Nombre", d.getFullName());
			//Itera por cada viaje para listar los viajes realizado
			for (Trip t : d.getTrips()){ 
				tripMap.put(t.getIdTrip().toString(), t.getDate());
			}
			aMap.put("trips", tripMap);
			aMap.put("Puntaje promedio", d.averageScore());
			aMap.put("Fecha de licencia", d.getLicenseDate());
			
		
		
		return new Gson().toJson(aMap);		
	}
	
	
	//Crear un viaje ->  recibe los siguientes parámetros: origen, destino, conductorId,	costoTotal, cantidadPasajeros
	// curl -d "origen=La Plata&destino=Capital&conductorId=1&costoTotal=300&cantidadPasajeros=3" http://localhost:8080/MuberRESTful/rest/services/viajes/nuevo	
	@RequestMapping(
			value = "/viajes/nuevo", 
			method = RequestMethod.POST, 
			produces = "application/json", 
			headers = "Accept=application/json"
			)
	
	public String viajesNuevo(
			@RequestParam("origen") String origin,
			@RequestParam("destino") String destination,
			@RequestParam("conductorId") Long idDriver,
			@RequestParam("costoTotal") double price,
			@RequestParam("cantidadPasajeros") Integer maxPassenger
			) {
		
		Map<String, Object> aMap0 = new HashMap<String, Object>();
		Map<String, Object> aMap = new HashMap<String, Object>();
		
		//Verifico lo que me llega por parametro
		aMap.put("Origen", origin );
		aMap.put("Destino", destination );
		aMap.put("conductorId", idDriver );
		aMap.put("costoTotal", price );
		aMap.put("cantidadPasajeros", maxPassenger );
		
		Date date = new Date();
		
		if (saveTrip(idDriver, date, maxPassenger, price, origin, destination)) {
			aMap.put("Se Agrego correctamente el viaje con destino", destination );
		}
		else {
			aMap.put("No se pudo agregar el viaje con destino", destination );
		}
		aMap0.put("Valores del nuevo Viaje", aMap);
		
		return new Gson().toJson(aMap0);
	}
	
	//TODO: HACERLO
	//Agregar un pasajero a un viaje ya creado. 
	//Este servicio recibe los siguientes parámetros: viajeId, pasajeroId
	// curl -X PUT -d "pasajeroId=5&viajeId=2" http://localhost:8080/MuberRESTful/rest/services/viajes/agregarPasajero
	// curl --header "Content-Type: application/json" --request PUT --data '{"viajeId":"2","pasajeroId":"5"}' http://localhost:8080/MuberRESTful/rest/services/viajes/agregarPasajero
	@RequestMapping(
			value = "/viajes/agregarPasajero", 
			method = RequestMethod.POST, 
			produces = "application/json", 
			headers = "Accept=application/json"
			)
	
	public String viajesAgregarPasajeros(
		@RequestParam("viajeId") Long idTrip, 
		@RequestParam("pasajeroId") Long idPassenger
			) {
		Map<String, Object> aMap0 = new HashMap<String, Object>();		
		Map<String, Object> aMap = new HashMap<String, Object>();
		aMap.put("viajeId", idTrip);
		aMap.put("pasejeroId", idPassenger);
		Passenger aPassenger = getPassenger(idPassenger);
		Trip aTrip = getTrip(idTrip);
	//	aMap.put("six", aTrip.getPassengers().size());
		if(aPassenger.singUpForTrip(aTrip)){
			//update en base (no estoy seguro como)
			aMap0.put("pasajero agregado a viaje", aMap);			
		}else{
			aMap0.put("no hay lugar para el viaje", aMap);			
		}
		return new Gson().toJson(aMap0);		
	}
	
	
	
	
	//Crear una calificación de un pasajero para un viaje en particular, recibe los siguientes parámetros: viajeId, pasajeroId, puntaje, comentario
	@RequestMapping(
			value = "/viajes/calificar", 
			method = RequestMethod.POST, 
			produces = "application/json", 
			headers = "Accept=application/json"
			)
	
	public String viajesCalificar(
			@RequestParam("viajeId") Long idTrip,
			@RequestParam("pasajeroId") Long idPassenger,
			@RequestParam("puntaje") Integer score,
			@RequestParam("comentario") String description
			){
	
		Map<String, Object> aMap = new HashMap<String, Object>();

		if (saveScore(idTrip, idPassenger, score, description)) {
			aMap.put("Se Agrego correctamente la calificacion al viaje", idTrip );
		}
		else {
			aMap.put("No se puedo agregar la calificacion al viaje", idTrip );
		}
			
		
		return new Gson().toJson(aMap);		
	}
	// curl -d "viajeId=1&pasajeroId=5&puntaje=1&comentario='tara raea erjk'" http://localhost:8080/MuberRESTful/rest/services/viajes/calificar
	
	
	//Cargar crédito a un pasajero en particular. USA PUT
	//Finalizar un viaje. Considerar que el viaje sólo puede finalizarse una vez. USA PUT
	
	/*  Listar los 10 conductores mejor calificados que no tengan viajes abiertos registrados
		http://localhost:8080/MuberRESTful/rest/services/conductores/top10
		Metodo: GET
		Este servicio no requiere parámetros
	 */
	@RequestMapping(
			value = "/conductores/top10", 
			method = RequestMethod.GET, 
			produces = "application/json", 
			headers = "Accept=application/json"
			)
	
	public String conductoresTop10() {
		Map<Long, Object> aMap = new HashMap<Long, Object>();		
		List<Driver> driverList = getDriversTopTen(); //HACER LA CONSULTA DEL TOP TEN
		for (Driver d : driverList){ 
			aMap.put(d.getIdUser(), d.getFullName());
		}
		return new Gson().toJson(aMap);		
	}


}
