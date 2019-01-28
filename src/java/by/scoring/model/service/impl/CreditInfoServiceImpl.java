package by.scoring.model.service.impl;

import by.scoring.model.repository.CreditInfoRepository;
import by.scoring.model.entity.CreditInfo;
import by.scoring.model.service.ICreditInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditInfoServiceImpl implements ICreditInfoService {

    @Autowired
    private CreditInfoRepository creditInfoRepository;

    @Override
    public void addCreditInfo(CreditInfo creditInfo) {
        creditInfoRepository.save(creditInfo);}

    @Override
    public void updateCreditInfo(CreditInfo creditInfo) {
        creditInfoRepository.save(creditInfo);}

    @Override
    public void removeCreditInfo(long id) {
        creditInfoRepository.delete(id);}

    @Override
    public CreditInfo getCreditInfoById(long id) {return creditInfoRepository.findOne(id);}

    @Override
    public List<CreditInfo> listCreditInfo() {return creditInfoRepository.findAll();}

    @Override
    public CreditInfo findByType(String type) {return creditInfoRepository.findByType(type);}
}
