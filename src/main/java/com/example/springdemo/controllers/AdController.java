package com.example.springdemo.controllers;

import com.example.springdemo.models.Ad;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdController {

    @GetMapping("/ad/{id}")
    public String showAdByTitle(@PathVariable long id, Model model) {
        Ad ad = new Ad();
        ad.setTitle("title");
        ad.setDescription("This is a demo of creating a view in Spring.");
        model.addAttribute("ad", ad);
        return "ads/show";
    }
}
