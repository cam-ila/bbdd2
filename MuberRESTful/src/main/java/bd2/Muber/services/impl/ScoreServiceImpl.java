package bd2.Muber.services.impl;

import bd2.Muber.model.Score;
import bd2.Muber.services.ScoreService;

public class ScoreServiceImpl extends BaseService implements ScoreService {

	@Override
	public Score findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveScore(Long idTrip, Long idPassenger,Integer score, String description) {
		return scoreRepository.saveScore(idTrip, idPassenger, score, description);		
	}

}
