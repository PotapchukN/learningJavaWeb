package by.scoring.model.repository;

import by.scoring.model.entity.Calls;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CallsRepository extends JpaRepository<Calls, Long> {

    List<Calls> findAllByTopic (String topic);

}
