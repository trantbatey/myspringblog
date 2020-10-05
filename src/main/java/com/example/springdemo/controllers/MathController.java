package com.example.springdemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {

    @GetMapping("/add/{parm1}/and/{parm2}")
    @ResponseBody
    public String add(@PathVariable String parm1, @PathVariable String parm2) {
        int num1 = Integer.parseInt(parm1);
        int num2 = Integer.parseInt(parm2);
        return Integer.toString(num1 + num2);
    }

    @GetMapping("/subtract/{parm1}/from/{parm2}")
    @ResponseBody
    public String subtract(@PathVariable String parm1, @PathVariable String parm2) {
        int num1 = Integer.parseInt(parm1);
        int num2 = Integer.parseInt(parm2);
        return Integer.toString(num2 - num1);
    }

    @GetMapping("/multiply/{parm1}/by/{parm2}")
    @ResponseBody
    public String multiply(@PathVariable String parm1, @PathVariable String parm2) {
        int num1 = Integer.parseInt(parm1);
        int num2 = Integer.parseInt(parm2);
        return Integer.toString(num1 * num2);
    }

    @GetMapping("/divide/{parm1}/by/{parm2}")
    @ResponseBody
    public String divide(@PathVariable String parm1, @PathVariable String parm2) {
        int num1 = Integer.parseInt(parm1);
        int num2 = Integer.parseInt(parm2);
        return Integer.toString(num1 / num2);
    }

}
