package utn.frc.backend.parcialbda.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import static lombok.AccessLevel.*;

@Entity
@Table(name = "media_types")
@Getter
@FieldDefaults(level = PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class MediaType {
    @Id
    @Column(name = "MediaTypeId")
    Integer id;
    @Column(name = "Name")
    String name;
}
