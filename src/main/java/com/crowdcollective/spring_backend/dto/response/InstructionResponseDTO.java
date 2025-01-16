package com.crowdcollective.spring_backend.dto.response;

import com.crowdcollective.spring_backend.dao.Instruction;

public record InstructionResponseDTO(Integer id,
                                     String instructionText,
                                     Integer index) {
                                    
    public InstructionResponseDTO(Instruction instruction) {
        this(instruction.getInstructionId(), 
        instruction.getInstructionText(), 
        instruction.getOrderIndex());
    }

}
