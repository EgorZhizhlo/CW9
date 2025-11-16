package com.rehub.controller.site;

import com.rehub.model.User;
import com.rehub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SpecialistController {

    private final UserService userService;

    @GetMapping("/specialists")
    public String specialists(Model model) {
        List<User> doctors = userService.findByRole(User.Role.DOCTOR);
        model.addAttribute("specialists", doctors);
        return "site/specialists";
    }
}
