package net.abit.springboot.controller;

import net.abit.springboot.dto.UserRegistrationDto;
import net.abit.springboot.model.User;
import net.abit.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
    //Home page
    @GetMapping("/home")
    public String homePage(Model model) {
        return "home";
    }
    
    // Registration form view
    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "registration";
    }
    
    // Handle user registration
    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") UserRegistrationDto userRegistrationDto, Model model) {
        User user = userService.save(userRegistrationDto);
        model.addAttribute("success", true);
        return "registration";
    }


    // Login form view
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
 
 
}
