package com.crowdcollective.spring_backend.repository.instruction;

import java.util.List;

public interface InstructionRepositoryWrapper {
    void delete(Integer id);
    void delete(List<Integer> ids);
}
