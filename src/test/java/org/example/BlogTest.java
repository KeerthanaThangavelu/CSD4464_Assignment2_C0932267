package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class BlogTest {

    @Test
    void testGetPostsByAuthorAge_validAge() {
        Person person1 = Person.builder().id("1").firstName("Geroge").lastName("Malley").age(30).gender("Male").build();
        Person person2 = Person.builder().id("2").firstName("Meredith").lastName("Grey").age(25).gender("Female").build();

        BlogPost post1 = BlogPost.builder().id("B001").authorId("1").postContent("Post by Malley").build();
        BlogPost post2 = BlogPost.builder().id("B002").authorId("2").postContent("Post by Grey").build();
        BlogPost post3 = BlogPost.builder().id("B003").authorId("1").postContent("Another post by Malley").build();

        Blog blog = new Blog(List.of(post1, post2, post3), List.of(person1, person2));

        List<String> result = blog.getPostsByAuthorAge(30);
        assertEquals(List.of("B001", "B003"), result);
    }

    @Test
    void testGetPostsByAuthorAge_noMatches() {
        Person person1 = Person.builder().id("1").firstName("Meredith").lastName("Grey").age(25).gender("Female").build();

        BlogPost post1 = BlogPost.builder().id("B002").authorId("1").postContent("Post by Grey").build();

        Blog blog = new Blog(List.of(post1), List.of(person1));

        List<String> result = blog.getPostsByAuthorAge(40);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetPostsByAuthorAge_missingAuthorId() {

        Person person1 = Person.builder().id("C001").firstName("Geroge").lastName("Malley").age(30).gender("Male").build();

        BlogPost post1 = BlogPost.builder().id("1").authorId("1").postContent("Unknown author post").build();

        Blog blog = new Blog(List.of(post1), List.of(person1));

        List<String> result = blog.getPostsByAuthorAge(30);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetPostsByAuthorAge_emptyData() {
        Blog blog = new Blog(List.of(), List.of());
        List<String> result = blog.getPostsByAuthorAge(30);
        assertTrue(result.isEmpty());
    }
}

