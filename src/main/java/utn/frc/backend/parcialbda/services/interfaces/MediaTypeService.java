package utn.frc.backend.parcialbda.services.interfaces;

import utn.frc.backend.parcialbda.model.MediaType;

import java.util.Optional;

public interface MediaTypeService {
    Optional<MediaType> findById(Integer id);
}
