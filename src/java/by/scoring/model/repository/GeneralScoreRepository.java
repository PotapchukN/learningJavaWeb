package by.scoring.model.repository;

import by.scoring.model.entity.GeneralScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralScoreRepository extends JpaRepository<GeneralScore, Long> {
    GeneralScore findByMaxScore(int max);
    GeneralScore findByMinScore(int min);
}
