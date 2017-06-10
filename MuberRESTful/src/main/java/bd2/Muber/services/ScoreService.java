package bd2.Muber.services;

import bd2.Muber.model.Score;

public interface ScoreService {
	
	Score findById (Long id);
	
	boolean saveScore(Long idTrip, Long idPassenger,Integer score, String description);

}
