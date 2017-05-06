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
	       // session.close();
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
		String hql = "FROM bd2.Muber.model.Driver P WHERE P.idUser = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		List<Driver> result = query.list();
		tx.commit();
		endSession(session);	
		return result;
	}
	
	private Passenger getPassenger(Long id){
		Session session = getSession();	
		Transaction tx = null;
		tx = session.beginTransaction();
		String hql = "FROM bd2.Muber.model.Passenger P WHERE P.idUser = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		List<Passenger> result = query.list();
		tx.commit();
		endSession(session);
		return result.get(0);
	} //TODO: ver lo de get(0) tiene que haber una forma de que me devuelva un solo elemento, o que eso si esta vcacio no tiere error
	
	private Trip getTrip(Long id){
		Session session = getSession();	
		Transaction tx = null;
		tx = session.beginTransaction();
		String hql = "FROM bd2.Muber.model.Trip T WHERE T.idTrip = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		List<Trip> result = query.list();
		tx.commit();
		endSession(session);
		return result.get(0);
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
	
	//TODO: todo a la mitad. esto no anda porque dice que esta duplicada la session. Entra por la exception por eso no tiera error
	private void saveScore(Score aScore){
		Session session = getSession();
		
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(aScore);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			}		
		session.disconnect();
		
	}
	
	
	
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
	
	//Obtener la información de un conductor (nombre de usuario, viajes realizados, puntaje promedio y fecha de licencia)
	//TODO:Crear bien la url, tiene que ser con "/detalles?conductorId=1" creo... preguntarlo
	//TODO:Cuando creemos bien el json de los viajes, usarlo aca
	@RequestMapping(value = "/conductores/detalles/{conductorId}",  method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String conductoresDetalles(@PathVariable(value="conductorId") Long conductorId) {

		Map<String, Object> aMap = new HashMap<String, Object>();		
		List<Driver> driverList = getDriver(conductorId);
		for (Driver d : driverList){ 
			aMap.put("Nombre", d.getFullName());
			//Itera por cada viaje para listar los viajes realizado
			for (Trip t : d.getTrips()){ 
				aMap.put(t.getIdTrip().toString(), t.getDate());
			}
			aMap.put("Puntaje promedio", d.averageScore());
			aMap.put("Fecha de licencia", d.getLicenseDate());
			
		}
		return new Gson().toJson(aMap);		
	}
	
	
	//Crear un viaje
	//Este servicio recibe los siguientes parámetros: origen, destino, conductorId,	costoTotal, cantidadPasajeros
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
		
		Map<String, Object> aMap = new HashMap<String, Object>();	
		
		//Verifico lo que me llega por parametro
		aMap.put("Origen", origin );
		aMap.put("Destino", destination );
		aMap.put("conductorId", idDriver );
		aMap.put("costoTotal", price );
		aMap.put("cantidadPasajeros", maxPassenger );
		
		
		Date date = new Date();
		
		//TODO: Arreglar, Si el driver con ese id no existe, new trip, va a qeurer guardar un null y Error!!!!!!!!!!!!
		//uso el metodo de /conductores. 
		Driver driver = getDriver(idDriver).get(0);
		Trip aTrip = new Trip(driver, date, maxPassenger, price, origin, destination);
		//Imprimo giladas, 
		aMap.put("v Origen", aTrip.getOrigin());
		aMap.put("v Destino", aTrip.getDestination());
		aMap.put("v conductorId", aTrip.getDriver().getFullName());
		aMap.put("v costoTotal", aTrip.getPrice());
		aMap.put("v cantidadPasajeros", aTrip.getMaxPassenger());
		aMap.put("v date", aTrip.getDate());
		return new Gson().toJson(aMap);	
		//TODO:Crear un viaje implica guardarlo, no?, hacer un session.save en algun lugar. no aca :)
	}
	// curl -d "costoTotal=10&cantidadPasajeros=5&origen=pepe&destino=sarasa&conductorId=1" http://localhost:8080/MuberRESTful/rest/services/viajes/nuevo

	
	
	//Agregar un pasajero a un viaje ya creado
	//Este servicio recibe los siguientes parámetros: viajeId, pasajeroId
	@RequestMapping(
			value = "/viajes/agregarPasajero", 
			method = RequestMethod.PUT, 
			produces = "application/json", 
			headers = "Accept=application/json"
			)
	
	public String viajesAgregarPasajeros(
		//	@RequestParam("viajeId") Integer idTrip, //put no usa @RequestParam
			//@RequestParam("pasajeroId") Integer idPassenger
			) {
		
		Map<String, Object> aMap = new HashMap<String, Object>();
		aMap.put("viajeId", "hola");
	//	aMap.put("viajeId", idTrip);
	//	aMap.put("pasejeroId", idPassenger);
		return new Gson().toJson(aMap);		
	}
	// http://localhost:8080/MuberRESTful/rest/services/viajes/agregarPasajero
	//Usa put y es otro mambo aparte
	
	
	
	/*
	Crear una calificación de un pasajero para un viaje en particular
	http://localhost:8080/MuberRESTful/rest/services/viajes/calificar
	Este servicio recibe los siguientes parámetros: viajeId, pasajeroId, puntaje, comentario
	TODO: HACER QUE GUARDE A aScore EN LA BBDD. 
	*/
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

		Passenger passenger = getPassenger(idPassenger);
		Trip trip = getTrip(idTrip);
		Score aScore = new Score(trip, passenger, score, description);
		
		aMap.put("viajeId", aScore.getIdScore());
		aMap.put("pasajeroId", aScore.getPassenger().getIdUser());
		aMap.put("puntaje", aScore.getScore());
		aMap.put("comentario", aScore.getDescription());
		
		saveScore(aScore); //no funca, no guarda. 
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
		List<Driver> driverList = getDriversTopTen();
		for (Driver d : driverList){ 
			aMap.put(d.getIdUser(), d.getFullName());
		}
		return new Gson().toJson(aMap);		
	}


}
