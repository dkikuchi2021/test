package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("hello/*")
@Controller
public class HelloController {
  @GetMapping("world")
  public String open(Model model) {
    String str = "Hello World wwww";
    model.addAttribute("value", str);
    return "hello";
  }
  
  @GetMapping("world2")
  public String open2(Model model) {
    String str = "Hello World";
    model.addAttribute("value", str);
    return "hello";
  }
}