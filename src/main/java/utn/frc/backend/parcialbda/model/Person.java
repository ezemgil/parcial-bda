package utn.frc.backend.parcialbda.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.*;

@MappedSuperclass
@Getter
@FieldDefaults(level = PACKAGE)
public class Person {
    @Column(name = "FirstName")
    String firstName;
    @Column(name = "LastName")
    String lastName;
    @Column(name = "Address")
    String address;
    @Column(name = "City")
    String city;
    @Column(name = "State")
    String state;
    @Column(name = "Country")
    String country;
    @Column(name = "PostalCode")
    String postalCode;
    @Column(name = "Fax")
    String fax;
    @Column(name = "Email")
    String email;
    @Column(name = "Phone")
    Integer phone;
}
