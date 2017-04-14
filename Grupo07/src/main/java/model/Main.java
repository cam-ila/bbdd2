package model;

import org.hibernate.SessionFactory;
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
		
	}

}
