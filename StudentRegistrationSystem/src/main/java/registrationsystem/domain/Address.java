package registrationsystem.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;
    private Long postalCode;
    private String stateProvince;
    private String countryOrigin;

    public Address(String street, String city, Long postalCode, String stateProvince, String countryOrigin) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.stateProvince = stateProvince;
        this.countryOrigin = countryOrigin;
    }
}
