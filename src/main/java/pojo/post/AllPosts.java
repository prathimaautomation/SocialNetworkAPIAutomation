package pojo.post;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * POJOs for All Posts API Response
 * http://<domain>/posts
 * e.g. http://jsonplaceholder.typicode.com/posts
 */

@Getter
@Setter
public class AllPosts {

    List<SinglePost> listOfPosts;

    @Override
    public String toString() {
        return "AllPosts [listOfPosts=" + listOfPosts + "]";
    }
}
