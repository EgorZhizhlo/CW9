package com.rehub.controller.site;

import com.rehub.service.NewsService;
import com.rehub.service.ProgramServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final NewsService newsService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("news", newsService.findAll());
        return "site/index";
    }
}
