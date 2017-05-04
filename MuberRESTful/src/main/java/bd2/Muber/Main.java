package bd2.Muber;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import bd2.Muber.model.Driver;
import bd2.Muber.model.Passenger;
import bd2.Muber.model.Score;
import bd2.Muber.model.Trip;

public class Main {

	public static void main(String[] args) {
		
	
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");		
	
		new SchemaExport(cfg).drop(true, true);
		
		new SchemaExport(cfg).create(true, true);
		
		SessionFactory sf = cfg.buildSessionFactory();
		
		Date aDate = new Date();
		Driver aDriver = new Driver("Roberto", "sarasa", aDate);
		
		Passenger firstPassenger = new Passenger("German", "german 123", 1500);
		Passenger secPassenger = new Passenger("Alicia", "al1c1a", 1500);
		Passenger thirdPassenger = new Passenger("Margarita", "m4rg4r1t4", 1500);
		
		Trip aTrip = new Trip();
		aTrip.setPrice(900);		
		aTrip.setDate(aDate);
		aTrip.setMaxPassenger(5);
		aTrip.setOrigin("La plata");
		aTrip.setDestination("Tres Arrollos");
		aTrip.setDriver(aDriver);
		aTrip.addPassenger(firstPassenger);
		aTrip.addPassenger(secPassenger);
		aTrip.addPassenger(thirdPassenger);
		
		Score fps = new Score(aTrip, firstPassenger, 5, "Genial");
		aTrip.addScore(fps);
		
		Score sps = new Score(aTrip, secPassenger, 4, "medio medio");
		aTrip.addScore(sps);
		
		Score tps = new Score(aTrip, thirdPassenger, 4, "aceptable");
		aTrip.addScore(tps);

		aTrip.close();
		
		
		Session session = sf.openSession();
		
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(aTrip);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			session.close();
		}		
		session.disconnect();
	}

}