package com.example.springdemo.controllers;

import com.example.springdemo.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @GetMapping("/post")
    public String showPosts(Model model) {
        List<Post> postList = new ArrayList<>();
        postList.add(new Post("First Post", "This is the body of the first test post."));
        postList.add(new Post("Second Post", "This is the body of the second test post."));
        model.addAttribute("postList", postList);
        return "posts/list";
    }

    @GetMapping("/post/{id}")
    public String showPost(@PathVariable Integer id, Model model) {
        Post post = new Post("A Single Post", "This is the body of a single test post.");
        model.addAttribute("post", post);
        return "posts/post";
    }

    @GetMapping("/post/create")
    @ResponseBody
    public String showCreatePost () {
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost() {
        return "create a new post";
    }
}
