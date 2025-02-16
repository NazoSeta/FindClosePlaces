package com.NazoSeta.FindClosePlaces.repository;

import com.NazoSeta.FindClosePlaces.model.RequestAndAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestAndAnswerRepository extends JpaRepository<RequestAndAnswer, Long> {

    @Query("SELECT COUNT(r) > 0 FROM RequestAndAnswer r WHERE r.longitude = :longitude AND r.latitude = :latitude AND r.radius = :radius")
    boolean existsByCoordinatesAndRadius(String longitude, String latitude, String radius);

}
