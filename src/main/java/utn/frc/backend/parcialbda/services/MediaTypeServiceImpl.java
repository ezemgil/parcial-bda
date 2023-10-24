package utn.frc.backend.parcialbda.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import utn.frc.backend.parcialbda.model.MediaType;
import utn.frc.backend.parcialbda.repositories.MediaTypeRepository;
import utn.frc.backend.parcialbda.services.interfaces.MediaTypeService;

import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class MediaTypeServiceImpl implements MediaTypeService {
    MediaTypeRepository mediaTypeRepository;

    @Override
    public Optional<MediaType> findById(Integer id) {
        return mediaTypeRepository.findById(id);
    }
}
