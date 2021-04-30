package pojo.geo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Geo {

    private String lat;
    private String lng;

    @Override
    public String toString() {
        return "ClassPojo [lat = " + lat + ", lng = " + lng + "]";
    }
}
