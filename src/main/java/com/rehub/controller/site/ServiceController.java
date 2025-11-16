package com.rehub.controller.site;

import com.rehub.service.ProgramServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ServiceController {

    private final ProgramServiceService serviceService;

    @GetMapping("/services")
    public String services(Model model) {
        model.addAttribute("services", serviceService.findAll());
        return "site/services";
    }
}
