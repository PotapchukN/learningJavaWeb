package by.scoring.model.repository;

import by.scoring.model.entity.CreditInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditInfoRepository extends JpaRepository<CreditInfo, Long> {
    CreditInfo findByType(String type);
}
