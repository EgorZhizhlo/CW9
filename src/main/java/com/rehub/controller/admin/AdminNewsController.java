package com.rehub.controller.admin;

import com.rehub.model.News;
import com.rehub.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/news")
public class AdminNewsController {

    private final NewsService newsService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("newsList", newsService.findAll());
        return "admin/news/list";
    }

    // -------- CREATE --------

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("news", new News());
        return "admin/news/form";
    }

    @PostMapping("/create")
    public String createSubmit(News news) {
        news.setCreatedAt(LocalDateTime.now());
        newsService.save(news);
        return "redirect:/admin/news";
    }

    // -------- UPDATE --------

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        return newsService.findById(id)
                .map(news -> {
                    model.addAttribute("news", news);
                    return "admin/news/form";
                })
                .orElse("redirect:/admin/news");
    }

    @PostMapping("/update/{id}")
    public String updateSubmit(@PathVariable Long id, News updated) {
        return newsService.findById(id)
                .map(existing -> {
                    existing.setTitle(updated.getTitle());
                    existing.setContent(updated.getContent());
                    // createdAt НЕ меняем
                    newsService.save(existing);
                    return "redirect:/admin/news";
                })
                .orElse("redirect:/admin/news");
    }

    // -------- DELETE --------

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        newsService.delete(id);
        return "redirect:/admin/news";
    }
}
