package by.scoring.model.dao;

import by.scoring.model.entity.GeneralScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralScoreDao extends JpaRepository<GeneralScore, Long> {
    GeneralScore findByMaxScore(int max);
    GeneralScore findByMinScore(int min);
}
