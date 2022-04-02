package com.hype.eventservice.api.backoffice.artist;

import com.hype.eventservice.api.backoffice.artist.dto.ArtistDTO;
import com.hype.eventservice.api.backoffice.common.dto.RestResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/artists")
public class ArtistController {

    private final ArtistService service;

    @Cacheable("artists")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ArtistDTO>> findAllArtists(
            @RequestParam("value") String sortValue,
            @RequestParam("sort_by") String sortBy,
            @RequestParam("size") int sizeList) {
        final var response = service.findAllArtists(sortValue,sortBy,sizeList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Cacheable("artists")
    @GetMapping("/{artistId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ArtistDTO> findArtistById(@PathVariable BigInteger artistId) {
        final var response = service.findArtistById(artistId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CacheEvict("artists")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RestResponseDTO> createArtist(@RequestBody ArtistDTO artistDTO) {
        final var response = service.createArtist(artistDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @CacheEvict("artists")
    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<RestResponseDTO> updateArtist(@RequestBody ArtistDTO artistDTO) {
        final var response = service.updateArtist(artistDTO);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @CacheEvict("artists")
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RestResponseDTO> deleteArtist(@RequestBody BigInteger artistId) {
        final var response = service.deleteArtist(artistId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
