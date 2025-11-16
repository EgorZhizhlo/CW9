package com.rehub.controller.site;

import com.rehub.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/news")
    public String news(Model model) {
        model.addAttribute("newsList", newsService.findAll());
        return "site/news";
    }

    @GetMapping("/news/{id}")
    public String newsDetail(@PathVariable Long id, Model model) {
        newsService.findById(id).ifPresent(n -> model.addAttribute("news", n));
        return "site/news_detail";
    }
}
