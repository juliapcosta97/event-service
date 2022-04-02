package com.hype.eventservice.api.backoffice.artist;

import com.hype.eventservice.api.backoffice.artist.domain.Artist;
import com.hype.eventservice.api.backoffice.artist.domain.ArtistRepository;
import com.hype.eventservice.api.backoffice.artist.dto.ArtistDTO;
import com.hype.eventservice.api.backoffice.common.dto.RestResponseDTO;
import com.hype.eventservice.api.util.PageableUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import static com.hype.eventservice.api.util.MessageUtils.*;
import static com.hype.eventservice.api.util.MessageUtils.DELETE_MESSAGE;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ArtistService {

    private final ArtistRepository repository;

    public List<ArtistDTO> findAllArtists(String sortValue, String sortBy, int sizeList) {
        var pageable = PageableUtils.buildPageFilter(sortValue,sortBy,sizeList);
        var artists = repository.findAll(pageable);

        return artists.stream().map(artist ->
                new ArtistDTO(artist.getId(), artist.getName(), artist.getPhoto(), artist.getDescription()))
                .collect(Collectors.toList());
    }

    public ArtistDTO findArtistById(BigInteger artistId) {
        var artistOptional = repository.findById(artistId);
        var artist = artistOptional.get();

        return new ArtistDTO(artist.getId(), artist.getName(), artist.getPhoto(), artist.getDescription());
    }

    public RestResponseDTO createArtist(ArtistDTO artistDTO) {
        var artist = new Artist(artistDTO);
        repository.save(artist);

        return new RestResponseDTO(CREATE_STATUS, CREATE_MESSAGE);
    }

    public RestResponseDTO updateArtist(ArtistDTO artistDTO) {
        var artist = new Artist(artistDTO);

        var updateResponse = repository.findById(artist.getId())
                .map(record -> {
                    record.setPhoto(artist.getPhoto());
                    record.setName(artist.getName());
                    record.setDescription(artist.getDescription());

                    repository.save(record);
                    return new RestResponseDTO(UPDATE_STATUS, UPDATE_MESSAGE);
                });

        return updateResponse.get();
    }

    public RestResponseDTO deleteArtist(BigInteger artistId) {
        var artist = repository.findById(artistId);
        repository.delete(artist.get());

        return new RestResponseDTO(DELETE_STATUS, DELETE_MESSAGE);
    }
}
