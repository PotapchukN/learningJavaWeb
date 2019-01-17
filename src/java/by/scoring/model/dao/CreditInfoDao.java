package by.scoring.model.dao;

import by.scoring.model.entity.CreditInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditInfoDao extends JpaRepository<CreditInfo, Long> {
    CreditInfo findByType(String type);
}
