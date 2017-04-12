package model;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;

import java.util.List;

class ScoreDAO {
	private Session session;
	private Transaction tx;

	private void initiateOperation() throws HibernateException
	{
	    session = HibernateUtil.getSessionFactory().openSession();
	    tx = session.beginTransaction();
	}
	
	private void handleException(HibernateException he) throws HibernateException
	{
	    tx.rollback();
	    throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
	}

	public long saveScore(Score score)
	{ 
	    long idScore = 0;  

	    try 
	    { 
	        initiateOperation(); 
	        idScore = (Long)session.save(score); 
	        tx.commit(); 
	    }catch(HibernateException he) 
	    { 
	        handleException(he);
	        throw he; 
	    }finally 
	    { 
	        session.close(); 
	    }  
	    return idScore; 
	}
	
	public void updateScore(Score score) throws HibernateException 
	{ 
	    try 
	    { 
	        initiateOperation(); 
	        session.update(score); 
	        tx.commit(); 
	    }catch (HibernateException he) 
	    { 
	        handleException(he); 
	        throw he; 
	    }finally 
	    { 
	        session.close(); 
	    } 
	}
	
	public void deleteScore(Score score) throws HibernateException 
	{ 
	    try 
	    { 
	        initiateOperation(); 
	        session.delete(score); 
	        tx.commit(); 
	    } catch (HibernateException he) 
	    { 
	        handleException(he); 
	        throw he; 
	    }finally 
	    { 
	        session.close(); 
	    } 
	}
	
	public Score getScore(long idScore) throws HibernateException
	{ 
	    Score score = null;  

	    try 
	    { 
	        initiateOperation(); 
	        score = (Score) session.get(Score.class, idScore); 
	    } finally 
	    { 
	        session.close(); 
	    }  
	    return score; 
	}
	
	 public List<Score> getScoreList() throws HibernateException 
	    { 
	        List<Score> scoreList = null;  

	        try 
	        { 
	            initiateOperation(); 
	            scoreList = session.createQuery("from Score").list(); 
	        } finally 
	        { 
	            session.close(); 
	        }  

	        return scoreList; 
	    }  
}