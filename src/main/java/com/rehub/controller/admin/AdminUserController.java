package com.rehub.controller.admin;

import com.rehub.model.User;
import com.rehub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class AdminUserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/users/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", User.Role.values());
        return "admin/users/form";
    }

    @PostMapping("/create")
    public String createSubmit(User user) {

        if (user.getPassword() == null || user.getPassword().isBlank()) {
            return "redirect:/admin/users/create?error=password";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        User user = userService.findById(id).orElse(null);
        if (user == null) return "redirect:/admin/users";
        model.addAttribute("user", user);
        model.addAttribute("roles", User.Role.values());
        return "admin/users/form";
    }

    @PostMapping("/update/{id}")
    public String updateSubmit(@PathVariable Long id, User updated) {
        User existing = userService.findById(id).orElse(null);
        if (existing == null) return "redirect:/admin/users";

        existing.setUsername(updated.getUsername());
        existing.setRole(updated.getRole());
        existing.setFullName(updated.getFullName());
        existing.setPhone(updated.getPhone());
        existing.setPhotoUrl(updated.getPhotoUrl());
        existing.setDescription(updated.getDescription());
        existing.setPosition(updated.getPosition());

        if (updated.getPassword() != null && !updated.getPassword().isBlank()) {
            existing.setPassword(passwordEncoder.encode(updated.getPassword()));
        }

        userService.save(existing);
        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }
}
