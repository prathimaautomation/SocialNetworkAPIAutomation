package pojo.post;

import lombok.Getter;
import lombok.Setter;

/**
 * POJOs for Single Post by id API Response
 * https://<domain>/posts/<id>
 * e.g. https://jsonplaceholder.typicode.com/posts/1
 */

@Getter
@Setter
public class SinglePost {

    private int userId;
    private int id;
    private String title;
    private String body;

    @Override
    public String toString() {
        return "ClassPojo [userId = " + userId + ", id = " + id + ", title = " + title + ", body = " + body + "]";
    }
}
