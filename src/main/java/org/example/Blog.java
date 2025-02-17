package org.example;

import lombok.Getter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<String> getPostsByAuthorAge(Integer age) {

        Map<String, Integer> authorAgeMap = contributors.stream()
                .collect(Collectors.toMap(Person::getId, Person::getAge));

        return posts.stream()
                .filter(post -> authorAgeMap.getOrDefault(post.getAuthorId(), -1).equals(age))
                .map(BlogPost::getId)
                .collect(Collectors.toList());
    }
}
