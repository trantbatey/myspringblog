package com.example.springdemo.controllers;

import com.example.springdemo.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @GetMapping("/post/{title}")
    public String showPost(@PathVariable String title, Model model) {
        Post post = new Post("A Single Post", "This is the body of a single test post.");
        model.addAttribute("post", post);
        return "posts/post";
    }

    @GetMapping("/posts")
    public String showPosts(Model model) {
        List<Post> postList = new ArrayList<>();
        postList.add(new Post("First Post", "This is the body of the first test post."));
        postList.add(new Post("Second Post", "This is the body of the second test post."));
        model.addAttribute("postList", postList);
        return "posts/list";
    }
}
