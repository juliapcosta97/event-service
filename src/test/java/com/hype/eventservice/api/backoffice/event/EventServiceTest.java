package com.hype.eventservice.api.backoffice.event;

import com.hype.eventservice.api.backoffice.event.domain.Event;
import com.hype.eventservice.api.backoffice.event.domain.EventRepository;
import com.hype.eventservice.api.backoffice.event.dto.EventRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigInteger;
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
    Page<Event> events;

    @Mock
    EventRequestDTO eventDTO;

    @Mock
    Event event;

    @InjectMocks
    EventService service;

    @Test
    public void shouldReturnOk_WhenGetAllEventsOrderByAsc(){
        var pageable = buildPageFilter("dateTime", "asc", 10);

        when(eventRepository.findAll(pageable)).thenReturn(events);

        var actual = service.findAllEvents("dateTime", "asc", 10);

        verify(eventRepository).findAll(pageable);
        assertNotNull(actual);
    }

    @Test
    public void shouldReturnOk_WhenGetAllEventsOrderByDesc(){
        var pageable = buildPageFilter("dateTime", "desc", 10);

        when(eventRepository.findAll(pageable)).thenReturn(events);

        var actual = service.findAllEvents("dateTime", "desc", 10);

        verify(eventRepository).findAll(pageable);
        assertNotNull(actual);
    }

    @Test
    public void shouldReturnError_WhenGetAllEvents(){
        var pageable = buildPageFilter("dateTime", "asc", 10);

        when(eventRepository.findAll(pageable)).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> service.findAllEvents("dateTime", "asc", 10));

        verify(eventRepository).findAll(pageable);
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
        when(eventRepository.findById(eventDTO.getId())).thenReturn(Optional.of(event));

        var actual = service.updateEvent(eventDTO);

        assertNotNull(actual);
    }

    @Test
    public void shouldReturnError_WhenUpdateEvent(){
        when(eventRepository.findById(eventDTO.getId())).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> service.updateEvent(eventDTO));
    }

    @Test
    public void shouldReturnOk_WhenDeleteEvent(){
        when(eventRepository.findById(BigInteger.ONE)).thenReturn(Optional.of(event));

        var actual = service.deleteEvent(BigInteger.ONE);

        assertNotNull(actual);
    }

    public Pageable buildPageFilter(String sortValue, String sortBy, int sizeList) {
        Sort sort = Sort.by(sortValue).ascending();

        if(sortBy.equalsIgnoreCase("desc")){
            sort = Sort.by(sortValue).descending();
        }

        return PageRequest.of(0,sizeList, sort);
    }
}
