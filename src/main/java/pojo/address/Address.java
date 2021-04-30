package pojo.address;

import lombok.Getter;
import lombok.Setter;
import pojo.geo.Geo;

@Getter
@Setter
public class Address {

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    @Override
    public String toString() {
        return "ClassPojo [street = " + street + ", suite = " + suite + ", city = " + city + ", zipcode = " + zipcode + ", geo = " + geo + "]";
    }
}