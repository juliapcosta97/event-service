package com.hype.eventservice.api.backoffice.interaction;

import com.hype.eventservice.api.backoffice.common.dto.RestResponseDTO;
import com.hype.eventservice.api.backoffice.interaction.dto.InteractionDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/interactions")
public class InteractionController {

    private final InteractionService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RestResponseDTO> saveInteraction(@RequestBody InteractionDTO interactionDTO) {
        final var response = service.saveInteraction(interactionDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<RestResponseDTO> updateInteraction(@RequestBody InteractionDTO interactionDTO) {
        final var response = service.updateInteraction(interactionDTO);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{interactionId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<InteractionDTO> findInteractionById(@PathVariable BigInteger interactionId) {
        final var response = service.findInteractionById(interactionId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
