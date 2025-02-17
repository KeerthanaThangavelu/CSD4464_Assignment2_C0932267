package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ObjectMapper objectMapper = new ObjectMapper();
        List<Person> persons;
        List<BlogPost> blogPosts;

        try {
            persons = objectMapper.readValue(new File("person.json"), new TypeReference<List<Person>>() {});
            blogPosts = objectMapper.readValue(new File("blogPosts.json"), new TypeReference<List<BlogPost>>() {});
        } catch (IOException e) {
            System.err.println("Error reading JSON files: " + e.getMessage());
            return;
        }

        List<String> knownAuthorIds = persons.stream()
                .map(Person::getId)
                .toList();

        List<String> missingAuthorIds = blogPosts.stream()
                .map(BlogPost::getAuthorId)
                .filter(authorId -> !knownAuthorIds.contains(authorId))
                .distinct()
                .toList();

        if (!missingAuthorIds.isEmpty()) {
            System.out.println("Warning: The following author IDs in blogPosts.json do not exist in person.json: " + missingAuthorIds);
        }

        Blog blog = new Blog(blogPosts, persons);

        int targetAge;
        if (args.length > 0) {
            try {
                targetAge = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid age input. Please enter a valid integer.");
                return;
            }
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter target age: ");
            try {
                targetAge = scanner.nextInt();
            } catch (Exception e) {
                System.err.println("Invalid age input. Please enter a valid integer.");
                return;
            } finally {
                scanner.close();
            }
        }

        List<String> postIds = blog.getPostsByAuthorAge(targetAge);
        System.out.println("Blog posts by authors aged " + targetAge + ": " + postIds);

        System.out.println("Total Blog Posts: " + blogPosts.size());
        System.out.println("Total Contributors: " + persons.size());

    }
}