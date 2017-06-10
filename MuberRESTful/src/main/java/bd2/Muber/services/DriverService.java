/**
 * 
 */
package bd2.Muber.services;

import java.util.List;

import bd2.Muber.model.Driver;

/**
 * @author cami
 *
 */
public interface DriverService {
	
	Driver findById(long id);
	
	List<Driver> findAllDrivers();

}
