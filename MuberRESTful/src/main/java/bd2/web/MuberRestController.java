package bd2.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.gson.Gson;

import bd2.Muber.dto.DriverDTO;
import bd2.Muber.dto.PassengerDTO;
import bd2.Muber.dto.TripDTO;
import bd2.Muber.model.Trip;
import bd2.Muber.services.DriverService;
import bd2.Muber.services.PassengerService;
import bd2.Muber.services.ScoreService;
import bd2.Muber.services.ServiceLocator;
import bd2.Muber.services.TripService;



@ControllerAdvice
@RequestMapping("/services")
@ResponseBody
@EnableWebMvc
public class MuberRestController {

	//Listar todos los pasajeros registrados en Muber
	@RequestMapping(value = "/pasajeros", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String pasajeros() {
		Map<Long, Object> aMap = new HashMap<Long, Object>();	
		PassengerService service = ServiceLocator.getPassengerService();
		List<PassengerDTO> passengerList = service.findAllPassenger();

		for (PassengerDTO p : passengerList){ 
			aMap.put(p.getIdUser(), p.getFullName());
		}
		return new Gson().toJson(aMap);		
	}

	//Listar todos los conductores registrados en Muber
	@RequestMapping(value = "/conductores", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String conductores() {
		Map<Long, Object> aMap = new HashMap<Long, Object>();		
		DriverService service = ServiceLocator.getDriverService();
		List<DriverDTO> driversList = service.findAllDrivers();
		for (DriverDTO d : driversList){ 
			aMap.put(d.getIdUser(), d.getFullName());
		}
		return new Gson().toJson(aMap);		
	}
	
	//TODO: ARREGLAR, ERROR DE LA SESION
	//Listar todos los viajes abiertos en Muber
	@RequestMapping(value = "/abiertos", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String abiertos() {
		Map<Long, Object> aMap = new HashMap<Long, Object>();		
		TripService service = ServiceLocator.getTripService();
		List<TripDTO> tripsList = service.findAllOpenedTrips();

		for (TripDTO t : tripsList){ 
			aMap.put(t.getIdTrip(), t.getDriver().getFullName());
		}
		return new Gson().toJson(aMap);
	}

	/*
	 * Obtener la información de un conductor (nombre de usuario, viajes realizados, puntaje promedio y fecha de licencia)
	 * curl -G -d "conductorId=1" http://localhost:8080/MuberRESTful/rest/services/conductores/detalles
	 */
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
		DriverService service = ServiceLocator.getDriverService();
		DriverDTO driver = service.findById(conductorId);


		aMap.put("Nombre", driver.getFullName());
		//Itera por cada viaje para listar los viajes realizado
		for (Trip t : driver.getTrips()){ 
			tripMap.put(t.getIdTrip().toString(), t.getDate());
		}
		aMap.put("trips", tripMap);
		aMap.put("Puntaje promedio", driver.getAverageScore());
		aMap.put("Fecha de licencia", driver.getLicenseDate());

		return new Gson().toJson(aMap);		
	}

	/*
	 * Crear un viaje: parámetros: origen, destino, conductorId,	costoTotal, cantidadPasajeros
	 * curl -d "origen=La Plata&destino=Capital&conductorId=1&costoTotal=300&cantidadPasajeros=3" http://localhost:8080/MuberRESTful/rest/services/viajes/nuevo	
	 */
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

		TripService service = ServiceLocator.getTripService();			

		if (service.saveTrip(idDriver, date, maxPassenger, price, origin, destination)) {
			aMap0.put("Se Agrego correctamente el viaje", aMap );
		}
		else {
			aMap0.put("No se pudo agregar el viaje", aMap );
		}

		return new Gson().toJson(aMap0);
	}

	/*
	 * Agregar un pasajero a un viaje ya creado.Parámetros: viajeId, pasajeroId
	 * curl -X PUT -d "pasajeroId=5&viajeId=2" http://localhost:8080/MuberRESTful/rest/services/viajes/agregarPasajero -G
	 */
	@RequestMapping(
			value = "/viajes/agregarPasajero", 
			method = RequestMethod.PUT, 
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

		TripService service = ServiceLocator.getTripService();

		if(service.updateTrip(idTrip,idPassenger)){
			aMap0.put("pasajero agregado a viaje", aMap);			
		}else{
			aMap0.put("no hay lugar para el viaje", aMap);			
		}
		return new Gson().toJson(aMap0);		
	}


	/*
	 * Crear una calificación de un pasajero para un viaje en particular, Parámetros: viajeId, pasajeroId, puntaje, comentario
	 * curl -d "viajeId=1&pasajeroId=5&puntaje=1&comentario='un comentario'" http://localhost:8080/MuberRESTful/rest/services/viajes/calificar
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
		Map<String, Object> aMap0 = new HashMap<String, Object>();

		aMap.put("viaje Id", idTrip );
		aMap.put("pasajeroId", idPassenger );
		aMap.put("puntaje", score );
		aMap.put("comentario", description );


		ScoreService service = ServiceLocator.getScoreService();

		if (service.saveScore(idTrip, idPassenger, score, description)) {
			aMap0.put("Se Agrego correctamente la calificacion al viaje", aMap );
		}
		else {
			aMap0.put("No se puedo agregar la calificacion al viaje", aMap );
		}

		return new Gson().toJson(aMap0);		
	}


	/*
	 * Cargar crédito a un pasajero en particular. USA PUT. Parámetros: pasajeroId, monto
	 * curl -X PUT -d "pasajeroId=2&monto=4000" http://localhost:8080/MuberRESTful/rest/services/pasajeros/cargarCredito -G
	 */
	@RequestMapping(
			value = "/pasajeros/cargarCredito", 
			method = RequestMethod.PUT, 
			produces = "application/json", 
			headers = "Accept=application/json"
			)

	public String pasajerosCargarCredito(
			@RequestParam("pasajeroId") Long idPassenger, 
			@RequestParam("monto") Double monto
			) {
		Map<String, Object> aMap0 = new HashMap<String, Object>();		
		Map<String, Object> aMap = new HashMap<String, Object>();

		aMap.put("pasejeroId", idPassenger);
		aMap.put("monto", monto);

		PassengerService service = ServiceLocator.getPassengerService();

		service.updateCreditPassenger(idPassenger,monto);

		aMap0.put("Monto agregado al credito", aMap);			
		return new Gson().toJson(aMap0);	
	}

	/*
	 * Finalizar un viaje. Considerar que el viaje sólo puede finalizarse una vez. USA PUT. Parámetros: viajeId
	 * curl -X PUT -d "viajeId=1" http://localhost:8080/MuberRESTful/rest/services/viajes/finalizar -G
	 */	
	@RequestMapping(
			value = "/viajes/finalizar", 
			method = RequestMethod.PUT, 
			produces = "application/json", 
			headers = "Accept=application/json"
			)

	public String viajesFinalizar(
			@RequestParam("viajeId") Long viajeId
			) {
		Map<String, Object> aMap = new HashMap<String, Object>();
		
		TripService service = ServiceLocator.getTripService();	
		
		
		String answer = service.closeTrip(viajeId);		
		aMap.put(answer, viajeId);
		return new Gson().toJson(aMap);	
	}
	
	
	/*
	 * Listar los 10 conductores mejor calificados que no tengan viajes abiertos registrados
	 * curl http://localhost:8080/MuberRESTful/rest/services/conductores/top10
	 */
	@RequestMapping(
			value = "/conductores/top10", 
			method = RequestMethod.GET, 
			produces = "application/json", 
			headers = "Accept=application/json"
			)
	
	public String conductoresTop10() {
		Map<String, Object> aMap = new HashMap<String, Object>();
		
		DriverService service = ServiceLocator.getDriverService();
		List<DriverDTO> driversList = service.getDriversTop10();

		
		Collection<DriverDTO> withAllTripClosed = new ArrayList<DriverDTO>();
		
		for (DriverDTO d : driversList){ 
			
			if (!d.isHaveOpenTrip()){
				withAllTripClosed.add(d);			
			}
		}
		List<DriverDTO> list = new ArrayList<DriverDTO >( withAllTripClosed );
		Collections.sort( list, new Comparator<DriverDTO>( ){
			@Override
			public int compare(DriverDTO d1, DriverDTO d2) {
				if (d1.getAverageScore() < d2.getAverageScore()){
					return 1;
				} else if (d1.getAverageScore() > d2.getAverageScore()){
					return -1;
				} else {
					return 0;
				}
			}		
		} );
		
		Iterator<DriverDTO> l = list.iterator();
		Integer i = 1;
		while (l.hasNext() && i<=10) {
			DriverDTO d = l.next();
			aMap.put(d.getFullName(), d.getAverageScore());
			i++;
			
			
		}
		return new Gson().toJson(aMap);		
	}
}