package by.scoring.model.service.impl;

import by.scoring.model.repository.GeneralScoreRepository;
import by.scoring.model.entity.GeneralScore;
import by.scoring.model.service.IGeneralScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneralScoreServiceImpl implements IGeneralScoreService {

    @Autowired
    private GeneralScoreRepository generalScoreRepository;
    @Override
    public void addGeneralScore(GeneralScore generalScore) {
        generalScoreRepository.save(generalScore);}

    @Override
    public void updateGeneralScore(GeneralScore generalScore) {
        generalScoreRepository.save(generalScore);}

    @Override
    public void removeGeneralScore(long id) {
        generalScoreRepository.delete(id);}

    @Override
    public GeneralScore getGeneralScoreById(long id) {return generalScoreRepository.findOne(id);}

    @Override
    public List<GeneralScore> listGeneralScore() {return generalScoreRepository.findAll();}

    @Override
    public GeneralScore findByMaxScore(int max) {return generalScoreRepository.findByMaxScore(max);}

    @Override
    public GeneralScore findByMinScore(int min) {return generalScoreRepository.findByMinScore(min);}
}
