package bd2.Muber.repositories.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bd2.Muber.dto.PassengerDTO;
import bd2.Muber.dto.TripDTO;
import bd2.Muber.dto.ScoreDTO;
import bd2.Muber.model.*;
import bd2.Muber.repositories.ScoreRepository;
import bd2.Muber.repositories.TripRepository;

public class HibernateScoreRepository extends BaseHibernateRepository implements ScoreRepository{

	public Boolean saveScore(Long idTrip, Long idPassenger,Integer score, String description){
		Session session = this.getSession();
		
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			
			String hql = "FROM bd2.Muber.model.Passenger P WHERE P.idUser = ?";
			Query query = session.createQuery(hql);
			query.setParameter(0, idPassenger);
			Passenger aPassenger = (Passenger) query.uniqueResult();
			 
			
			String hql1 = "FROM bd2.Muber.model.Trip T WHERE T.idTrip = ?";
			Query query1 = session.createQuery(hql1);
			query1.setParameter(0, idTrip);
			Trip aTrip = (Trip) query1.uniqueResult();
			Score aScore = new Score(aTrip, aPassenger, score, description);
			
			session.save(aScore);
			tx.commit();
			session.disconnect();
			session.close();
			return true;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
				session.disconnect();
				session.close();
				return false;
		}		
		
	}
}
