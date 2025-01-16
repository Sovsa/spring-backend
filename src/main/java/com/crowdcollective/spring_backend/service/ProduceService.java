package com.crowdcollective.spring_backend.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crowdcollective.spring_backend.dao.Diet;
import com.crowdcollective.spring_backend.dao.Produce;
import com.crowdcollective.spring_backend.dto.request.ProduceRequestDTO;
import com.crowdcollective.spring_backend.dto.response.ProduceResponseDTO;
import com.crowdcollective.spring_backend.repository.produce.ProduceRepository;

@Service
public class ProduceService {

    @Autowired
    private ProduceRepository produceRepository;

    public List<ProduceResponseDTO> getAllProduce() {
        return produceRepository.findAll().stream().map(e -> convertProduceToProduceResponseDTO(e)).collect(Collectors.toList());
    }

    public ProduceResponseDTO saveProduce(ProduceRequestDTO produceDto) {
        Produce produce = new Produce();
        produce.setName(produceDto.name());
        produce.setDescription(produceDto.description());
        produce.setAllergen(produceDto.allergen());
        produce.setDiets(Diet.getDiets(produceDto.diets()));
        for (Diet diet : produce.getDiets()) {
            System.out.println("Found " + diet.getPrettyName());
        }
        produce = produceRepository.save(produce);

        return convertProduceToProduceResponseDTO(produce);
    }

    private ProduceResponseDTO convertProduceToProduceResponseDTO(Produce produce) {
        List<String> diets = produce.getDiets().stream().map(Diet::getValue).collect(Collectors.toList());
        return new ProduceResponseDTO(produce.getProduceId(), produce.getName(), produce.getDescription(), produce.isAllergen(), diets);
    }
}
