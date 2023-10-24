package utn.frc.backend.parcialbda.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import static lombok.AccessLevel.*;

import java.util.List;

@Entity
@Table(name = "invoices")
@Getter
@FieldDefaults(level = PRIVATE)
public class Invoice {
    @Id
    @Column(name = "InvoiceId")
    Integer id;
    @ManyToOne
    @JoinColumn(name = "CustomerId")
    Customer customer;
    @OneToMany(mappedBy = "invoice")
    List<InvoiceItem> invoiceItems;
}
