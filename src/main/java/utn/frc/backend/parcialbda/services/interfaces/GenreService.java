package utn.frc.backend.parcialbda.services.interfaces;

import utn.frc.backend.parcialbda.model.Genre;

import java.util.Optional;

public interface GenreService {
    Optional<Genre> findById(Integer id);
}
