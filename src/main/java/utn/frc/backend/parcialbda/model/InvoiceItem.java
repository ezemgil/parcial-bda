package utn.frc.backend.parcialbda.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import static lombok.AccessLevel.*;

@Entity
@Table(name = "invoice_items")
@Getter
@FieldDefaults(level = PRIVATE)
public class InvoiceItem {
    @Id
    @Column(name = "InvoiceLineId")
    Integer id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TrackId")
    Track track;
    @Column(name = "UnitPrice")
    Float unitPrice;
    @Column(name = "Quantity")
    Integer quantity;
    @ManyToOne
    @JoinColumn(name = "invoiceId")
    Invoice invoice;
}
