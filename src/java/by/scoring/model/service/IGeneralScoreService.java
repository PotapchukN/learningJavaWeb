package by.scoring.model.service;

import by.scoring.model.entity.Answers;
import by.scoring.model.entity.GeneralScore;

import java.util.List;

public interface IGeneralScoreService {

    void addGeneralScore(GeneralScore generalScore);

    void updateGeneralScore(GeneralScore generalScore);

    void removeGeneralScore(long id);

    GeneralScore getGeneralScoreById(long id);

    List<GeneralScore> listGeneralScore();

    GeneralScore findByMaxScore(int max);
    GeneralScore findByMinScore(int min);
}
