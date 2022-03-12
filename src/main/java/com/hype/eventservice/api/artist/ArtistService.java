package com.hype.eventservice.api.artist;

import com.hype.eventservice.api.artist.domain.Artist;
import com.hype.eventservice.api.artist.domain.ArtistRepository;
import com.hype.eventservice.api.artist.dto.ArtistDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ArtistService {

    private final ArtistRepository repository;

    public List<ArtistDTO> findAllArtists() {
        final var artists = repository.findAll();

        return artists.stream().map(artist -> new ArtistDTO(artist))
                .collect(Collectors.toList());
    }

    public ArtistDTO findArtistById(BigInteger eventId) {
        var artist = repository.findById(eventId);

        if(artist.isPresent()){
            return new ArtistDTO(artist.get());
        }

        throw new RuntimeException("Artist not found");
    }

    public ArtistDTO saveArtist(ArtistDTO artistDTO) {
        final var artist = new Artist(artistDTO);

        repository.save(artist);
        return artistDTO;
    }

    public ArtistDTO deleteArtist(ArtistDTO artistDTO) {
        final var artist = new Artist(artistDTO);

        repository.delete(artist);
        return artistDTO;
    }
}
