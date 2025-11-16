package com.rehub.controller.admin;

import com.rehub.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/requests")
public class AdminRequestController {

    private final RequestService requestService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("requests", requestService.findAll());
        return "admin/requests/list";
    }

    @PostMapping("/process/{id}")
    public String process(@PathVariable Long id) {
        requestService.markProcessed(id);
        return "redirect:/admin/requests";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        requestService.delete(id);
        return "redirect:/admin/requests";
    }
}
