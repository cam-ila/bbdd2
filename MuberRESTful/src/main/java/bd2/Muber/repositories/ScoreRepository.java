package bd2.Muber.repositories;

import java.util.List;

import bd2.Muber.dto.ScoreDTO;

/**
 * The interface for the Score Repository. 
 */
public interface ScoreRepository extends BaseRepository {
	Boolean saveScore(Long idTrip, Long idPassenger,Integer score, String description);
}
