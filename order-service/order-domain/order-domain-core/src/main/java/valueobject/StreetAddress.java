package valueobject;

import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class StreetAddress {
    private final UUID id;
    private final String street;
    private final String city;
    private final String postalCode;

    public StreetAddress(UUID id, String street, String city, String postalCode) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StreetAddress that = (StreetAddress) o;
        return Objects.equals(street, that.street) && Objects.equals(city, that.city) && Objects.equals(postalCode, that.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, city, postalCode);
    }
}
