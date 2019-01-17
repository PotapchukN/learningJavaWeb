package by.scoring.model.service.impl;

import by.scoring.model.dao.GeneralScoreDao;
import by.scoring.model.entity.Answers;
import by.scoring.model.entity.GeneralScore;
import by.scoring.model.service.IGeneralScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneralScoreServiceImpl implements IGeneralScoreService {

    @Autowired
    private GeneralScoreDao generalScoreDao;
    @Override
    public void addGeneralScore(GeneralScore generalScore) {generalScoreDao.save(generalScore);}

    @Override
    public void updateGeneralScore(GeneralScore generalScore) {generalScoreDao.save(generalScore);}

    @Override
    public void removeGeneralScore(long id) {generalScoreDao.delete(id);}

    @Override
    public GeneralScore getGeneralScoreById(long id) {return generalScoreDao.findOne(id);}

    @Override
    public List<GeneralScore> listGeneralScore() {return generalScoreDao.findAll();}

    @Override
    public GeneralScore findByMaxScore(int max) {return generalScoreDao.findByMaxScore(max);}

    @Override
    public GeneralScore findByMinScore(int min) {return generalScoreDao.findByMinScore(min);}
}
