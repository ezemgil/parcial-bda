package utn.frc.backend.parcialbda.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import static lombok.AccessLevel.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "employees")
@FieldDefaults(level = PRIVATE)
public class Employee extends Person {
    @Id
    @Column(name = "EmployeeId")
    Integer id;
    @Column(name = "Title")
    String title;
    @OneToOne
    @PrimaryKeyJoinColumn
    Employee reportsTo;
    @Column(name = "BirthDate")
    LocalDateTime birthDate;
    @Column(name = "HireDate")
    LocalDateTime hireDate;
}
