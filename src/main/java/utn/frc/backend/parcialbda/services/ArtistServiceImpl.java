package utn.frc.backend.parcialbda.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.stereotype.Service;
import utn.frc.backend.parcialbda.model.Artist;
import utn.frc.backend.parcialbda.repositories.ArtistRepository;
import utn.frc.backend.parcialbda.repositories.IdentifierRepository;
import utn.frc.backend.parcialbda.services.interfaces.ArtistService;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {
    ArtistRepository artistRepository;
    IdentifierRepository identifierRepository;

    @Override
    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    @Override
    public Artist create(String name) {
        val artistId = identifierRepository.nextValue(Artist.TABLE_NAME);
        val newArtist = new Artist(artistId, name);
        return artistRepository.save(new Artist());
    }

    @Override
    public void update(Integer id, String name) {
        val existingArtist = artistRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Artist not found"));
        existingArtist.update(name);
        artistRepository.save(existingArtist);
    }

    @Override
    public void delete(Integer id) {
        artistRepository.deleteById(id);
    }

    @Override
    public Optional<Artist> findById(Integer id) {
        return artistRepository.findById(id);
    }
}
