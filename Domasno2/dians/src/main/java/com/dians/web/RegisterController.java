package com.dians.web;

import com.dians.model.exceptions.InvalidArgumentExceptions;
import com.dians.model.exceptions.PasswordDoNotMatchException;
import com.dians.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;
    public RegisterController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String getLoginPage(@RequestParam(required = false)String error, Model model){
        if (error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent", "register");
        return "master-template";
    }

    @PostMapping
    public String register( @RequestParam String username,
                            @RequestParam String password,
                            @RequestParam String repeatPassword,
                            @RequestParam String name,
                            @RequestParam String surname){
        try {
            this.authService.register(username, password, repeatPassword,
                                      name, surname);
            return "redirect:/login";
        } catch (InvalidArgumentExceptions | PasswordDoNotMatchException e) {
            return "redirect:/register?error=" + e.getMessage();
        }
    }

}
