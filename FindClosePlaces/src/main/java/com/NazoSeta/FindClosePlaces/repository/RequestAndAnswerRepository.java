package com.NazoSeta.FindClosePlaces.repository;

import com.NazoSeta.FindClosePlaces.model.RequestAndAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestAndAnswerRepository extends JpaRepository<RequestAndAnswer, Integer> {
}
