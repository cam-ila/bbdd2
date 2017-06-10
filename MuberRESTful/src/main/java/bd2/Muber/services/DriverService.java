/**
 * 
 */
package bd2.Muber.services;

import java.util.List;

import bd2.Muber.dto.DriverDTO;

/**
 * @author cami
 *
 */
public interface DriverService {
	
	DriverDTO findById(long id);
	
	List<DriverDTO> findAllDrivers();

}
