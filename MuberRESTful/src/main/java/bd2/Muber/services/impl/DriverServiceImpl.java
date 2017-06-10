/**
 * 
 */
package bd2.Muber.services.impl;

import java.util.List;

import bd2.Muber.dto.DriverDTO;

import bd2.Muber.services.DriverService;

/**
 * @author cami
 *
 */
public class DriverServiceImpl extends BaseService implements DriverService{

	@Override
	public DriverDTO findById(long id) {
		return driverRepository.getDriver(id);
	}

	@Override
	public List<DriverDTO> findAllDrivers() {	
		return driverRepository.getDrivers();
	}

}
