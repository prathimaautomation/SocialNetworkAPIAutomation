package pojo.company;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Company {

    private String name;
    private String catchPhrase;
    private String bs;

    @Override
    public String toString() {
        return "ClassPojo [name = " + name + ", catchPhrase = " + catchPhrase + ", bs = " + bs + "]";
    }
}
