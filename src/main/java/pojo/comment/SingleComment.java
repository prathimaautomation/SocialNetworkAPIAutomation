package pojo.comment;

import lombok.Getter;
import lombok.Setter;

/**
 * POJOs for Single Comment by id API Response
 * https://<domain>/comments/<id>
 * e.g. https://jsonplaceholder.typicode.com/comments/1
 */
@Getter
@Setter
public class SingleComment {

    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;

    @Override
    public String toString() {
        return "ClassPojo [postId = " + postId + ", id = " + id + ", name = " + name + ", email = " + email + ", body = " + body + "]";
    }
}
