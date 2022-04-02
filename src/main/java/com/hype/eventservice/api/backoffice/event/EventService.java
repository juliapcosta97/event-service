package com.hype.eventservice.api.backoffice.event;

import com.hype.eventservice.api.backoffice.event.domain.Event;
import com.hype.eventservice.api.backoffice.event.domain.EventRepository;
import com.hype.eventservice.api.backoffice.event.dto.EventRequestDTO;
import com.hype.eventservice.api.backoffice.event.dto.EventResponseDTO;
import com.hype.eventservice.api.backoffice.common.dto.RestResponseDTO;
import com.hype.eventservice.api.util.PageableUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import static com.hype.eventservice.api.util.MessageUtils.CREATE_MESSAGE;
import static com.hype.eventservice.api.util.MessageUtils.CREATE_STATUS;
import static com.hype.eventservice.api.util.MessageUtils.UPDATE_MESSAGE;
import static com.hype.eventservice.api.util.MessageUtils.UPDATE_STATUS;
import static com.hype.eventservice.api.util.MessageUtils.DELETE_MESSAGE;
import static com.hype.eventservice.api.util.MessageUtils.DELETE_STATUS;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EventService {

    private final EventRepository repository;

    public List<EventResponseDTO> findAllEvents(String sortValue, String sortBy, int sizeList) {
        var pageable = PageableUtils.buildPageFilter(sortValue,sortBy,sizeList);
        var events = repository.findAll(pageable);

        return events.stream().map(event -> new EventResponseDTO(event)).collect(Collectors.toList());
    }

    public EventResponseDTO findEventById(BigInteger eventId) {
        var event = repository.findById(eventId);

        return new EventResponseDTO(event.get());
    }

    public RestResponseDTO createEvent(EventRequestDTO eventDTO) {
        var event = new Event(eventDTO);
        repository.save(event);

        return new RestResponseDTO(CREATE_STATUS, CREATE_MESSAGE);
    }

    public RestResponseDTO updateEvent(EventRequestDTO eventDTO) {
        var event = new Event(eventDTO);

        var updateResponse = repository.findById(eventDTO.getId())
                .map(record -> {
                    record.setName(event.getName());
                    record.setArtist(event.getArtist());
                    record.setPhoto(event.getPhoto());
                    record.setDescription(event.getDescription());
                    record.setCity(event.getCity());
                    record.setLocation(event.getLocation());
                    record.setLink(event.getLink());
                    record.setGenre(event.getGenre());
                    record.setReference(event.getReference());
                    record.setType(event.getType());
                    record.setMaps(event.getMaps());
                    record.setDateTime(event.getDateTime());
                    repository.save(record);

                    return new RestResponseDTO(UPDATE_STATUS, UPDATE_MESSAGE);
                });

        return updateResponse.get();
    }

    public RestResponseDTO deleteEvent(BigInteger eventId) {
        var event = repository.findById(eventId);
        repository.delete(event.get());

        return new RestResponseDTO(DELETE_STATUS, DELETE_MESSAGE);
    }
}
