package com.crowdcollective.spring_backend.repository.produce;

import java.util.List;

public interface ProduceRepositoryWrapper {
    void delete(Integer id);
    void delete(List<Integer> id);
}
