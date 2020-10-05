package com.example.springdemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "<h1>Hello from Spring!</h1>";
    }

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

    @GetMapping("/join")
    public String showJoinForm() {
        return "join";
    }

    @PostMapping("/join")
    public String joinCohort(@RequestParam(name = "cohort") String cohort, Model model) {
        model.addAttribute("cohort", "Welcome to " + cohort + "!");
        if (cohort != null && !cohort.equals("")) {
            List<String> shoppingCart = new ArrayList<>();
            shoppingCart.add("Bread");
            shoppingCart.add(("Butter"));
            shoppingCart.add("eggs");
            model.addAttribute("shoppingCart", shoppingCart);
        }

        // create error message
        String message = "Sending messages works.";
        model.addAttribute("page_has_error", message);

        return "join";
    }

    @GetMapping("/roll-dice")
    public String showDiceForm() {
        return "dice";
    }

    @PostMapping("/roll-dice")
    public String showDiceResult(@RequestParam(name = "number") Integer number, Model model) {
        int numberRolled = (int) ((Math.random() * 6) + 1);

        String message = "You selected " + number + " and the number rolled was " + numberRolled + ".";
        if (number.equals(numberRolled)) {
            message += " You Won!";
        } else {
            message += " Oh well... try again!!";
        }
        model.addAttribute("message", message);

        return "dice";
    }
}
