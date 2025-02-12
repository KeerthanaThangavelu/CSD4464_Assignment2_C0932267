package org.example;

import lombok.Getter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.List;

@Getter
@EqualsAndHashCode
@ToString
public class Blog {

    private final List<BlogPost> posts;
    private final List<Person> contributors;

    public Blog(List<BlogPost> posts, List<Person> contributors) {
        this.posts = posts;
        this.contributors = contributors;
    }
}
