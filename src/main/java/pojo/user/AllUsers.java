package pojo.user;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * POJOs for All Users API Response
 * http://<domain>/users
 * e.g. http://jsonplaceholder.typicode.com/users
 */
@Getter
@Setter
public class AllUsers {

    List<SingleUser> listOfUsers;

    @Override
    public String toString() {
        return "AllUsers [listOfUsers=" + listOfUsers + "]";
    }
}
