package com.hype.eventservice.api.backoffice.interaction;

import com.hype.eventservice.api.backoffice.common.domain.User;
import com.hype.eventservice.api.backoffice.common.domain.UserRepository;
import com.hype.eventservice.api.backoffice.common.dto.RestResponseDTO;
import com.hype.eventservice.api.backoffice.event.domain.EventRepository;
import com.hype.eventservice.api.backoffice.interaction.domain.Interaction;
import com.hype.eventservice.api.backoffice.interaction.domain.InteractionRepository;
import com.hype.eventservice.api.backoffice.interaction.dto.InteractionDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

import static com.hype.eventservice.api.util.MessageUtils.CREATE_MESSAGE;
import static com.hype.eventservice.api.util.MessageUtils.CREATE_STATUS;
import static com.hype.eventservice.api.util.MessageUtils.UPDATE_MESSAGE;
import static com.hype.eventservice.api.util.MessageUtils.UPDATE_STATUS;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class InteractionService {

    private final InteractionRepository interactionRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public RestResponseDTO saveInteraction(InteractionDTO interactionDTO) {
        var user = userRepository.save(new User(interactionDTO.getUserIdentity()));
        var event = eventRepository.getById(interactionDTO.getEventId());
        var interaction = new Interaction(user, event, interactionDTO.getAction());
        interactionRepository.save(interaction);

        return new RestResponseDTO(CREATE_STATUS, CREATE_MESSAGE);
    }

    public RestResponseDTO updateInteraction(InteractionDTO interactionDTO) {
        var user = userRepository.getById(interactionDTO.getUserIdentity());
        var event = eventRepository.getById(interactionDTO.getEventId());
        var interaction = new Interaction(user, event, interactionDTO.getAction());

        var updateResponse = interactionRepository.findById(interaction.getId())
                .map(record -> {
                    record.setAction(interaction.getAction());
                    interactionRepository.save(record);

                    return new RestResponseDTO(UPDATE_STATUS, UPDATE_MESSAGE);
                });

        return updateResponse.get();
    }

    public InteractionDTO findInteractionById(BigInteger interactionId) {
        var interactionOptional = interactionRepository.findById(interactionId);
        var interaction = interactionOptional.get();

        return new InteractionDTO(interaction.getUser().getId(), interaction.getEvent().getId(), interaction.getAction());
    }
}
