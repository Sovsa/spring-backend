package com.crowdcollective.spring_backend.repository.instruction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crowdcollective.spring_backend.dao.Instruction;

@Repository
public interface InstructionRepository extends JpaRepository<Instruction, Integer>, InstructionRepositoryWrapper {

}
