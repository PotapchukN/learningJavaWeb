package by.scoring.model.dao;

import by.scoring.model.entity.Calls;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CallsDao extends JpaRepository<Calls, Long> {

    List<Calls> findAllByTopic (String topic);

}
