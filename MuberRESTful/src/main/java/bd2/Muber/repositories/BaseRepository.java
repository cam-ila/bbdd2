package bd2.Muber.repositories;

import org.hibernate.Session;
/**
 * Base interface. All the other interface will extend this one.
 */
public interface BaseRepository {
	
	public Session getSession();

}
