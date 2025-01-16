package com.crowdcollective.spring_backend.repository.produce;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crowdcollective.spring_backend.dao.Produce;

public interface ProduceRepository extends JpaRepository<Produce, Integer>, ProduceRepositoryWrapper  {

}
