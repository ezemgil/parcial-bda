package utn.frc.backend.parcialbda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.frc.backend.parcialbda.model.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
}
