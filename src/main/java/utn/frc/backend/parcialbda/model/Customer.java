package utn.frc.backend.parcialbda.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import static lombok.AccessLevel.*;

import java.util.List;

@Entity
@Table(name = "columns")
@Getter
@FieldDefaults(level = PRIVATE)
public class Customer extends Person {
    @Id
    @Column(name = "CustomerId")
    Integer id;
    @Column(name = "Company")
    String company;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SupportRepId")
    Employee supportRep;
    @OneToMany(mappedBy = "customer")
    List<Invoice> invoices;
}
