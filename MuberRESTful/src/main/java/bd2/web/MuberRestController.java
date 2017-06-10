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
		
	
	
	
}