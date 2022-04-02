package com.hype.eventservice.api.backoffice.interaction;

import com.hype.eventservice.api.backoffice.common.dto.RestResponseDTO;
import com.hype.eventservice.api.backoffice.interaction.dto.InteractionDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class InteractionControllerTest {

    @Mock
    InteractionService interactionService;

    @Mock
    RestResponseDTO response;

    @Mock
    InteractionDTO interaction;

    @InjectMocks
    InteractionController controller;

    @Test
    public void shouldReturnOk_WhenGetById(){
        when(interactionService.findInteractionById(BigInteger.ONE)).thenReturn(interaction);

        var actual = controller.findInteractionById(BigInteger.ONE);

        verify(interactionService).findInteractionById(BigInteger.ONE);
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertNotNull(actual.getBody());
    }

    @Test
    public void shouldReturnError_WhenGetById(){
        when(interactionService.findInteractionById(BigInteger.ONE)).thenThrow(HttpServerErrorException.InternalServerError.class);

        assertThrows(HttpServerErrorException.InternalServerError.class, () -> controller.findInteractionById(BigInteger.ONE));

        verify(interactionService).findInteractionById(BigInteger.ONE);
    }

    @Test
    public void shouldReturnOk_WhenCreate(){
        when(interactionService.saveInteraction(interaction)).thenReturn(response);

        var actual = controller.saveInteraction(interaction);

        verify(interactionService).saveInteraction(interaction);
        assertEquals(HttpStatus.CREATED , actual.getStatusCode());
        assertNotNull(actual.getBody());
    }

    @Test
    public void shouldReturnError_WhenCreate(){
        when(interactionService.saveInteraction(interaction)).thenThrow(HttpServerErrorException.InternalServerError.class);

        assertThrows(HttpServerErrorException.InternalServerError.class, () -> controller.saveInteraction(interaction));

        verify(interactionService).saveInteraction(interaction);
    }

    @Test
    public void shouldReturnOk_WhenUpdateEvent(){
        when(interactionService.updateInteraction(interaction)).thenReturn(response);

        var actual = controller.updateInteraction(interaction);

        verify(interactionService).updateInteraction(interaction);
        assertEquals(HttpStatus.ACCEPTED, actual.getStatusCode());
        assertNotNull(actual.getBody());
    }

    @Test
    public void shouldReturnError_WhenUpdateEvent(){
        when(interactionService.updateInteraction(interaction)).thenThrow(HttpServerErrorException.InternalServerError.class);

        assertThrows(HttpServerErrorException.InternalServerError.class, () -> controller.updateInteraction(interaction));

        verify(interactionService).updateInteraction(interaction);
    }
}
