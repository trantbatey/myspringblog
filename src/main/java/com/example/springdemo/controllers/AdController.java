package com.example.springdemo.controllers;

import com.example.springdemo.models.Ad;
import com.example.springdemo.models.User;
import com.example.springdemo.repositories.AdRepository;
import com.example.springdemo.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String showCreateView(Model model) {
        model.addAttribute("ad", new Ad());
        return "ads/create";
    }

    @PostMapping("/ads/create")
    public String createAd(@ModelAttribute Ad ad) {
        adRepo.save(ad);
        User user = userRepo.findAll().get(0);
        ad.setOwner(user);
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
