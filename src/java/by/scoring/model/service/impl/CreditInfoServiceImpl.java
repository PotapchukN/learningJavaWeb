package by.scoring.model.service.impl;

import by.scoring.model.dao.CreditInfoDao;
import by.scoring.model.entity.Answers;
import by.scoring.model.entity.CreditInfo;
import by.scoring.model.service.ICreditInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditInfoServiceImpl implements ICreditInfoService {

    @Autowired
    private CreditInfoDao creditInfoDao;

    @Override
    public void addCreditInfo(CreditInfo creditInfo) {creditInfoDao.save(creditInfo);}

    @Override
    public void updateCreditInfo(CreditInfo creditInfo) {creditInfoDao.save(creditInfo);}

    @Override
    public void removeCreditInfo(long id) {creditInfoDao.delete(id);}

    @Override
    public CreditInfo getCreditInfoById(long id) {return creditInfoDao.findOne(id);}

    @Override
    public List<CreditInfo> listCreditInfo() {return creditInfoDao.findAll();}

    @Override
    public CreditInfo findByType(String type) {return creditInfoDao.findByType(type);}
}
