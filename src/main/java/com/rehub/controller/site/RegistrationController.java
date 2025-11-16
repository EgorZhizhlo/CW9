package com.rehub.controller.site;

import com.rehub.model.User;
import com.rehub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String registerForm(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String fullName,
                           @RequestParam String phone,
                           Model model) {

        // проверка на существующего пользователя
        if (userService.findByUsername(username).isPresent()) {
            model.addAttribute("error", "Пользователь с таким логином уже существует");
            return "register";
        }

        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .fullName(fullName)
                .phone(phone)
                .role(User.Role.USER)
                .build();

        userService.save(user);

        return "redirect:/login";
    }
}
