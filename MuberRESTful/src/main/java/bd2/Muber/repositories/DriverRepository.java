package bd2.Muber.repositories;

import java.util.List;

import bd2.Muber.dto.DriverDTO;
/**
 * The interface for the Driver Repository. 
 */
public interface DriverRepository extends BaseRepository {

	DriverDTO getDriver(Long userId);
	List<DriverDTO> getDrivers();
	List<DriverDTO> getDriversTop10();
	
}