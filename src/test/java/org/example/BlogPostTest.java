package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BlogPostTest {

    @Test
    void testBlogPostConstructor_validData() {
        BlogPost blogPost = BlogPost.builder()
                .id("1")
                .authorId("1")
                .postContent("This is a blog post.")
                .build();

        assertNotNull(blogPost);
        assertEquals("1", blogPost.getId());
        assertEquals("1", blogPost.getAuthorId());
        assertEquals("This is a blog post.", blogPost.getPostContent());
    }

    @Test
    void testBlogPostConstructor_invalidData() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                BlogPost.builder().id(null).authorId("1").postContent("This is a blog post.").build()
        );
        assertEquals("ID cannot be null", thrown.getMessage());

        thrown = assertThrows(IllegalArgumentException.class, () ->
                BlogPost.builder().id("1").authorId(null).postContent("This is a blog post.").build()
        );
        assertEquals("Author ID cannot be null", thrown.getMessage());
    }
}

