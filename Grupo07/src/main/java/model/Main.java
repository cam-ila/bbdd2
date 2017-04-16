package model;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("----------------------- Setting up Hibernate -----------------------");
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		System.out.println("Droping schema.........");
		new SchemaExport(cfg).drop(true, true);
		System.out.println("DONE.");
		
		System.out.println("Generating schema.........");
		new SchemaExport(cfg).create(true, true);
		System.out.println("DONE.");
		
		
		
		System.out.println("Building sessions.........");
		
		SessionFactory sf = cfg.buildSessionFactory();
		
		Date aDate = new Date();
		Driver aDriver = new Driver("Roberto", "sarasa", aDate);
		
		Passenger firstPassenger = new Passenger("German", "german 123", 1500);
		Passenger secPassenger = new Passenger("Alicia", "al1c1a", 1500);
		Passenger thirdPassenger = new Passenger("Margarita", "m4rg4r1t4", 1500);
		
		Trip aTrip = new Trip();
		aTrip.setState(true);
		aTrip.setPrice(900);
		
		aTrip.setMaxPassenger(10);
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
		

		Transaction tx = session.beginTransaction();
		session.persist(aTrip);
		
	
		tx.commit();
		session.clear();
		session.close();
	}

}