package com.hype.eventservice.api.event;

import com.hype.eventservice.api.event.dto.EventDTO;
import com.hype.eventservice.api.event.dto.ResponseDTO;
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
    List<EventDTO> events;

    @Mock
    ResponseDTO response;

    @Mock
    EventDTO event;

    @InjectMocks
    EventController controller;

    @Test
    public void shouldReturnOk_WhenGetAllEvents(){
        when(eventService.findAllEvents()).thenReturn(events);

        var actual = controller.findAllEvents();

        verify(eventService).findAllEvents();
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertNotNull(actual.getBody());
    }

    @Test
    public void shouldReturnError_WhenGetAllEvents(){
        when(eventService.findAllEvents()).thenThrow(HttpServerErrorException.InternalServerError.class);

        assertThrows(HttpServerErrorException.InternalServerError.class, () -> controller.findAllEvents());

        verify(eventService).findAllEvents();
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
        when(eventService.createEvent(event)).thenReturn(response);

        var actual = controller.createEvent(event);

        verify(eventService).createEvent(event);
        assertEquals(HttpStatus.CREATED , actual.getStatusCode());
        assertNotNull(actual.getBody());
    }

    @Test
    public void shouldReturnError_WhenCreateEvent(){
        when(eventService.createEvent(event)).thenThrow(HttpServerErrorException.InternalServerError.class);

        assertThrows(HttpServerErrorException.InternalServerError.class, () -> controller.createEvent(event));

        verify(eventService).createEvent(event);
    }

    @Test
    public void shouldReturnOk_WhenUpdateEvent(){
        when(eventService.updateEvent(event)).thenReturn(response);

        var actual = controller.updateEvent(event);

        verify(eventService).updateEvent(event);
        assertEquals(HttpStatus.ACCEPTED, actual.getStatusCode());
        assertNotNull(actual.getBody());
    }

    @Test
    public void shouldReturnError_WhenUpdateEvent(){
        when(eventService.updateEvent(event)).thenThrow(HttpServerErrorException.InternalServerError.class);

        assertThrows(HttpServerErrorException.InternalServerError.class, () -> controller.updateEvent(event));

        verify(eventService).updateEvent(event);
    }

    @Test
    public void shouldReturnOk_WhenDeleteEvent(){
        when(eventService.deleteEvent(event)).thenReturn(response);

        var actual = controller.deleteEvent(event);

        verify(eventService).deleteEvent(event);
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertNotNull(actual.getBody());
    }

    @Test
    public void shouldReturnError_WhenDeleteEvent(){
        when(eventService.deleteEvent(event)).thenThrow(HttpServerErrorException.InternalServerError.class);

        assertThrows(HttpServerErrorException.InternalServerError.class, () -> controller.deleteEvent(event));

        verify(eventService).deleteEvent(event);
    }
}
