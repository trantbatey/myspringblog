package com.example.springdemo.controllers;

import com.example.springdemo.models.Post;
import com.example.springdemo.models.User;
import com.example.springdemo.repositories.PostRepository;
import com.example.springdemo.repositories.UserRepository;
import com.example.springdemo.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class PostController {
    private final PostRepository postRepo;
    private final UserRepository userRepo;
    private final EmailService emailService;

    public PostController(PostRepository postRepo, UserRepository userRepo, EmailService emailService) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String showPosts(Model model) {
        List<Post> posts = postRepo.findAll();
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String showPost(@PathVariable Integer id, Model model) {
        Post post = postRepo.getPostById(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String showCreatePost (Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post) {
        if (post.getOwner() == null) {
            List<User> users = userRepo.findAll();
            if (!users.isEmpty()) post.setOwner(users.get(0));
        }
        postRepo.save(post);
        emailPost("Created Post: ", post);
        return "redirect:/posts/" + post.getId();
    }

    private void emailPost(String titlePrefix, Post post) {
        emailService.prepareAndSend(post.getOwner().getEmail(), titlePrefix + post.getTitle(),
                post.getTitle() + "\n\n" + post.getBody());
    }

    @GetMapping("/posts/delete/{id}")
    public String deleteAd(@PathVariable long id, Model model) {
        Post post = postRepo.getPostById(id);
        postRepo.delete(post);
        emailPost("Deleted Post: ", post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/edit/{id}")
    public String editAd(@PathVariable long id, Model model) {
        Post post = postRepo.getPostById(id);
        model.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping("/posts/edit")
    public String updateAd(@ModelAttribute Post post) {
        if (post.getOwner() == null) {
            List<User> users = userRepo.findAll();
            if (!users.isEmpty()) post.setOwner(users.get(0));
        }
        postRepo.save(post);
        emailPost("Edited Post: ", post);
        return "redirect:/posts/" + post.getId();
    }
}
