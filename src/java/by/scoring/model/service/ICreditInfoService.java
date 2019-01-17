package by.scoring.model.service;

import by.scoring.model.entity.Answers;
import by.scoring.model.entity.CreditInfo;

import java.util.List;

public interface ICreditInfoService {

    void addCreditInfo(CreditInfo creditInfo);

    void updateCreditInfo(CreditInfo creditInfo);

    void removeCreditInfo(long id);

    CreditInfo getCreditInfoById(long id);

    List<CreditInfo> listCreditInfo();

    CreditInfo findByType(String type);

}
