package com.nttdata.projeto.escola.controller;

import com.nttdata.projeto.escola.model.DisciplinaEntity;
import com.nttdata.projeto.escola.model.UserEntity;
import com.nttdata.projeto.escola.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public String showLoginPage(Model model) {
        return "login/index";
    }

    @GetMapping("/home")
    public String showHomePage(Model model) {
        return "login/home";
    }

    @RequestMapping("/index")
    public String loginPage(@ModelAttribute(name = "loginForm") UserEntity user, Model m) {
        String name = user.getUsername();
        String pass = user.getPassword();
        UserEntity verifiedUser = userService.getUser(name);
        if (verifiedUser != null) {
            if (name.equals(verifiedUser.getUsername()) && pass.equals(verifiedUser.getPassword())) {
                m.addAttribute("name", name);
                m.addAttribute("pass", pass);
                return "login/home";
            }
        }
        m.addAttribute("error", "Username & Password Incorrectos");
        return "login/index";

    }
}
