package com.hype.eventservice.api.event;

import com.hype.eventservice.api.action.domain.ActionRepository;
import com.hype.eventservice.api.event.domain.Event;
import com.hype.eventservice.api.event.domain.EventRepository;
import com.hype.eventservice.api.event.dto.EventRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    EventRepository eventRepository;

    @Mock
    ActionRepository actionRepository;

    @Mock
    List<Event> events;

    @Mock
    EventRequestDTO eventDTO;

    @Mock
    Event event;

    @InjectMocks
    EventService service;

    @Test
    public void shouldReturnOk_WhenGetAllEvents(){
        when(eventRepository.findAll()).thenReturn(events);

        var actual = service.findAllEvents();

        verify(eventRepository).findAll();
        assertNotNull(actual);
    }

    @Test
    public void shouldReturnError_WhenGetAllEvents(){
        when(eventRepository.findAll()).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> service.findAllEvents());

        verify(eventRepository).findAll();
    }

    @Test
    public void shouldReturnOk_WhenGetEventById(){
        when(eventRepository.findById(BigInteger.ONE)).thenReturn(Optional.of(event));

        var actual = service.findEventById(BigInteger.ONE);

        verify(eventRepository).findById(BigInteger.ONE);
        assertNotNull(actual);
    }

    @Test
    public void shouldReturnError_WhenGetEventById(){
        when(eventRepository.findById(BigInteger.ONE)).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> service.findEventById(BigInteger.ONE));

        verify(eventRepository).findById(BigInteger.ONE);
    }

    @Test
    public void shouldReturnOk_WhenCreateEvent(){

        var actual = service.createEvent(eventDTO);

        assertNotNull(actual);
    }

    @Test
    public void shouldReturnError_WhenCreateEvent(){
        when(eventRepository.save(event)).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> service.createEvent(eventDTO));
    }

    @Test
    public void shouldReturnOk_WhenUpdateEvent(){

        var actual = service.updateEvent(eventDTO);

        assertNotNull(actual);
    }

    @Test
    public void shouldReturnError_WhenUpdateEvent(){
        when(eventRepository.saveAndFlush(event)).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> service.updateEvent(eventDTO));
    }

    @Test
    public void shouldReturnOk_WhenDeleteEvent(){
        when(this.eventRepository.findById(BigInteger.ONE)).thenReturn(Optional.of(event));

        var actual = service.deleteEvent(eventDTO);

        assertNotNull(actual);
    }
}
