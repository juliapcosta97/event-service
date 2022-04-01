package com.hype.eventservice.api.backoffice.event;

import com.hype.eventservice.api.backoffice.event.dto.EventRequestDTO;
import com.hype.eventservice.api.backoffice.event.dto.EventResponseDTO;
import com.hype.eventservice.api.backoffice.common.dto.RestResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventControllerTest {

    @Mock
    EventService eventService;

    @Mock
    List<EventResponseDTO> events;

    @Mock
    RestResponseDTO response;

    @Mock
    EventResponseDTO event;

    @Mock
    EventRequestDTO eventRequest;

    @InjectMocks
    EventController controller;

    @Test
    public void shouldReturnOk_WhenGetAllEvents(){
        when(eventService.findAllEvents("dateTime", "asc", 10)).thenReturn(events);

        var actual = controller.findAllEvents("dateTime", "asc", 10);

        verify(eventService).findAllEvents("dateTime", "asc", 10);
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertNotNull(actual.getBody());
    }

    @Test
    public void shouldReturnError_WhenGetAllEvents(){
        when(eventService.findAllEvents("dateTime", "asc", 10)).thenThrow(HttpServerErrorException.InternalServerError.class);

        assertThrows(HttpServerErrorException.InternalServerError.class, () -> controller.findAllEvents("dateTime", "asc", 10));

        verify(eventService).findAllEvents("dateTime", "asc", 10);
    }

    @Test
    public void shouldReturnOk_WhenGetEventById(){
        when(eventService.findEventById(BigInteger.ONE)).thenReturn(event);

        var actual = controller.findEventById(BigInteger.ONE);

        verify(eventService).findEventById(BigInteger.ONE);
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertNotNull(actual.getBody());
    }

    @Test
    public void shouldReturnError_WhenGetEventById(){
        when(eventService.findEventById(BigInteger.ONE)).thenThrow(HttpServerErrorException.InternalServerError.class);

        assertThrows(HttpServerErrorException.InternalServerError.class, () -> controller.findEventById(BigInteger.ONE));

        verify(eventService).findEventById(BigInteger.ONE);
    }

    @Test
    public void shouldReturnOk_WhenCreateEvent(){
        when(eventService.createEvent(eventRequest)).thenReturn(response);

        var actual = controller.createEvent(eventRequest);

        verify(eventService).createEvent(eventRequest);
        assertEquals(HttpStatus.CREATED , actual.getStatusCode());
        assertNotNull(actual.getBody());
    }

    @Test
    public void shouldReturnError_WhenCreateEvent(){
        when(eventService.createEvent(eventRequest)).thenThrow(HttpServerErrorException.InternalServerError.class);

        assertThrows(HttpServerErrorException.InternalServerError.class, () -> controller.createEvent(eventRequest));

        verify(eventService).createEvent(eventRequest);
    }

    @Test
    public void shouldReturnOk_WhenUpdateEvent(){
        when(eventService.updateEvent(eventRequest)).thenReturn(response);

        var actual = controller.updateEvent(eventRequest);

        verify(eventService).updateEvent(eventRequest);
        assertEquals(HttpStatus.ACCEPTED, actual.getStatusCode());
        assertNotNull(actual.getBody());
    }

    @Test
    public void shouldReturnError_WhenUpdateEvent(){
        when(eventService.updateEvent(eventRequest)).thenThrow(HttpServerErrorException.InternalServerError.class);

        assertThrows(HttpServerErrorException.InternalServerError.class, () -> controller.updateEvent(eventRequest));

        verify(eventService).updateEvent(eventRequest);
    }

    @Test
    public void shouldReturnOk_WhenDeleteEvent(){
        when(eventService.deleteEvent(BigInteger.ONE)).thenReturn(response);

        var actual = controller.deleteEvent(BigInteger.ONE);

        verify(eventService).deleteEvent(BigInteger.ONE);
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertNotNull(actual.getBody());
    }

    @Test
    public void shouldReturnError_WhenDeleteEvent(){
        when(eventService.deleteEvent(BigInteger.ONE)).thenThrow(HttpServerErrorException.InternalServerError.class);

        assertThrows(HttpServerErrorException.InternalServerError.class, () -> controller.deleteEvent(BigInteger.ONE));

        verify(eventService).deleteEvent(BigInteger.ONE);
    }
}
