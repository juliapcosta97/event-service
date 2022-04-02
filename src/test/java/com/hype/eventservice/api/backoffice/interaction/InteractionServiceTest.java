package com.hype.eventservice.api.backoffice.interaction;

import com.hype.eventservice.api.backoffice.common.domain.User;
import com.hype.eventservice.api.backoffice.common.domain.UserRepository;
import com.hype.eventservice.api.backoffice.event.domain.Event;
import com.hype.eventservice.api.backoffice.event.domain.EventRepository;
import com.hype.eventservice.api.backoffice.interaction.domain.Interaction;
import com.hype.eventservice.api.backoffice.interaction.domain.InteractionRepository;
import com.hype.eventservice.api.backoffice.interaction.dto.InteractionDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InteractionServiceTest {

    @Mock
    InteractionRepository interactionRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    EventRepository eventRepository;

    @Mock
    InteractionDTO interactionDTO;

    @Mock
    User user;

    @Mock
    Event event;

    @Mock
    Interaction interaction;

    @InjectMocks
    InteractionService service;

//    @Test
//    public void shouldReturnOk_WhenGetById(){
//        when(interactionRepository.findById(BigInteger.ONE)).thenReturn(Optional.of(interaction));
//
//        var actual = service.findInteractionById(BigInteger.ONE);
//
//        verify(interactionRepository).findById(BigInteger.ONE);
//        assertNotNull(actual);
//    }

    @Test
    public void shouldReturnError_WhenGetById(){
        when(interactionRepository.findById(BigInteger.ONE)).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> service.findInteractionById(BigInteger.ONE));

        verify(interactionRepository).findById(BigInteger.ONE);
    }

    @Test
    public void shouldReturnOk_WhenCreate(){

        var actual = service.saveInteraction(interactionDTO);

        assertNotNull(actual);
    }

    @Test
    public void shouldReturnError_WhenCreate(){
        when(interactionRepository.save(interaction)).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> service.saveInteraction(interactionDTO));
    }

//    @Test
//    public void shouldReturnOk_WhenUpdate(){
//        when(userRepository.getById(interactionDTO.getUserIdentity())).thenReturn(user);
//        when(eventRepository.getById(interactionDTO.getEventId())).thenReturn(event);
//        var interaction = new Interaction(user, event, interactionDTO.getAction());
//
//        when(interactionRepository.findById(BigInteger.ONE)).thenReturn(Optional.of(interaction));
//
//        var actual = service.updateInteraction(interactionDTO);
//
//        assertNotNull(actual);
//    }
//
//    @Test
//    public void shouldReturnError_WhenUpdate(){
//        when(interactionRepository.findById(BigInteger.ONE)).thenThrow(RuntimeException.class);
//
//        assertThrows(RuntimeException.class, () -> service.updateInteraction(interactionDTO));
//    }
}
