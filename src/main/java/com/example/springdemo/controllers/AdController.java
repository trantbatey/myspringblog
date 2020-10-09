package com.example.springdemo.controllers;

import com.example.springdemo.models.Ad;
import com.example.springdemo.models.User;
import com.example.springdemo.repositories.AdRepository;
import com.example.springdemo.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdController {
    private final AdRepository adRepo;
    private final UserRepository userRepo;

    public AdController(AdRepository adRepo, UserRepository userRepo) {
        this.adRepo = adRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/ads")
    public String index(Model model) {
        model.addAttribute("ads", adRepo.findAll());
        return "ads/index";
    }

    @GetMapping("/ads/{id}")
    public String showAd(@PathVariable long id, Model model) {
        Ad ad = adRepo.getAdById(id);
        model.addAttribute("ad", ad);
        return "ads/show";
    }

    @GetMapping("/ads/create")
    public String showCreateView() {
        return "ads/create";
    }

    @PostMapping("/ads/create")
    public String createAd(@RequestParam(name = "title") String title,
                           @RequestParam(name = "description") String description,
                           @RequestParam(name = "username") String username,
                           Model model) {
        User user = userRepo.findByUsername(username);
        Ad ad = new Ad(title, description);
        ad.setOwner(user);
        adRepo.save(ad);
        return "redirect:/ads/" + ad.getId();
    }

    @GetMapping("/ads/delete/{id}")
    public String deleteAd(@PathVariable long id, Model model) {
        Ad ad = adRepo.getAdById(id);
        adRepo.delete(ad);
        return "redirect:/ads";
    }

    @GetMapping("/ads/edit/{id}")
    public String editAd(@PathVariable long id, Model model) {
        Ad ad = adRepo.getAdById(id);
        model.addAttribute("ad", ad);
        return "ads/edit";
    }

    @PostMapping("/ads/edit")
    public String updateAd(@RequestParam(name = "id") long id,
                           @RequestParam(name = "title") String title,
                           @RequestParam(name = "description") String description) {
        Ad ad = new Ad();
        ad.setId(id);
        ad.setTitle(title);
        ad.setDescription(description);
        adRepo.save(ad);
        return "redirect:/ads/" + ad.getId();
    }
}
