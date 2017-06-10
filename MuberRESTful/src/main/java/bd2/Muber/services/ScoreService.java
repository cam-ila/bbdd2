package bd2.Muber.services;

import bd2.Muber.model.Score;

public interface ScoreService {
	
	Score findById (Long id);
	
	void saveScore(Score aScore);

}
