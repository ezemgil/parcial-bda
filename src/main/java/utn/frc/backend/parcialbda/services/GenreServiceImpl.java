package utn.frc.backend.parcialbda.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import utn.frc.backend.parcialbda.model.Genre;
import utn.frc.backend.parcialbda.repositories.GenreRepository;
import utn.frc.backend.parcialbda.services.interfaces.GenreService;

import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    GenreRepository genreRepository;

    @Override
    public Optional<Genre> findById(Integer id) {
        return genreRepository.findById(id);
    }
}
