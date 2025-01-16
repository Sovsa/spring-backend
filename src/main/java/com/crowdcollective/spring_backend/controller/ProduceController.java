package com.crowdcollective.spring_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crowdcollective.spring_backend.dto.ResponseHandler;
import com.crowdcollective.spring_backend.dto.request.ProduceRequestDTO;
import com.crowdcollective.spring_backend.dto.response.ProduceResponseDTO;
import com.crowdcollective.spring_backend.service.ProduceService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "/produce")
public class ProduceController {
    
    @Autowired
    private ProduceService produceService;

    @GetMapping()
    public ResponseEntity<Object> getAllProduce() {
        List<ProduceResponseDTO> allProduce = produceService.getAllProduce();

        return ResponseHandler.generateResponse("Getting all produce", HttpStatus.OK, allProduce);
    }

    @PostMapping()
    public ResponseEntity<Object> saveProduce(@RequestBody ProduceRequestDTO requestDTO) {
        ProduceResponseDTO produce = produceService.saveProduce(requestDTO);

        return ResponseHandler.generateResponse("Successfully created produce", HttpStatus.OK, produce);
    }
    
}
