package com.rehub.controller.admin;

import com.rehub.service.NewsService;
import com.rehub.service.ProgramServiceService;
import com.rehub.service.RequestService;
import com.rehub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminDashboardController {

    private final RequestService requestService;
    private final ProgramServiceService programServiceService;
    private final NewsService newsService;
    private final UserService userService;

    @GetMapping("/admin")
    public String dashboard(Model model) {
        model.addAttribute("requestsCount", requestService.findAll().size());
        model.addAttribute("servicesCount", programServiceService.findAll().size());
        model.addAttribute("newsCount", newsService.findAll().size());
        model.addAttribute("usersCount", userService.findAll().size());
        return "admin/dashboard";
    }
}
